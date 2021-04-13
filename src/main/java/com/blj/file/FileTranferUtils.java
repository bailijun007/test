package com.blj.file;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStreamReader;


/**
 * @author zhongyuanzhao000
 * @date 2020/08/27
 */
public class FileTranferUtils {

    /**
     * tranfer yaml file to properties
     *
     * @param path the path of yaml file
     */
    public static void yaml2Prop(String path) {
        List<String> lines = new LinkedList<>();

        // DOT用于隔开key中不同的键
        final String DOT = "*";

        //layerOfArray表示当前数组是第几层数组，默认为0即没有数组；每遍历到"["就增加1
        int layerOfArray = 0;

        // inArrays的索引表示yml文件中遍历到的token位于第几层数组，而元素内容表示当前遍历到的token仍在数组内部，元素默认值为false
        boolean[] inArrays = new boolean[4];
        // arrayIndexs的索引表示yml文件中遍历到的token位于第几层数组，而元素内容表示其对应的索引，元素默认值为0
        int[] arrayIndexs = new int[4];
        // arrayCuteds的索引表示yml文件中遍历到的token位于第几层数组，而元素内容表示 含有中括号的键是否已被切去，元素默认值为false
        boolean[] arrayCuteds = new boolean[4];
        // 注意：上面3个数组，目前均初始化了4个元素值，对应0、1、2、3，表示可以解析最多3层数组嵌套；
        // 若要更多层，修改该初始值即可

        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLParser parser = yamlFactory.createParser(new InputStreamReader(new FileInputStream(path), Charset.forName("utf-8")));

            String key = ""; //这里的key是最终转换出的properties文件中key，并不是yml文件中的键值对中的键
            String value = null;
            // 先获取到基于json格式的第一个token，便于后面的遍历
            JsonToken token = parser.nextToken();
            while (token != null) {

                // 基于json格式，如果是一个对象开始(即遇到了左花括号"{"时)
                if (JsonToken.START_OBJECT.equals(token)) {
                    // do nothing
                } else if (JsonToken.FIELD_NAME.equals(token)) {   // 基于json格式，如果遇到键值对的键时

                    // 使用点"."分割每层的key
                    if (key.length() > 0) {
                        // 如果该对象在数组中，并且key包含中括号的数量不等于 当前所在数组的层次时，则添加上数组索引
                        if (inArrays[layerOfArray] == true && containNumbers(key, "[") != layerOfArray) {
                            key = key + "[" + arrayIndexs[layerOfArray] + "]";
                        }
                        key = key + DOT;
                    }
                    key = key + parser.getCurrentName();

                    // 继续遍历下一个token
                    token = parser.nextToken();
                    /******************************************************************************************/
                    //如果遇到左中括号"["，表示数组的开始
                    if (JsonToken.START_ARRAY.equals(token)) {
                        // 进入第一层数组
                        layerOfArray++;
                        inArrays[layerOfArray] = true;

                        token = parser.nextToken();
                    }
                    /******************************************************************************************/

                    // 如果遇到子对象的开始(即"{")，则跳入下一个循环
                    if (JsonToken.START_OBJECT.equals(token)) {
                        continue;
                    }

                    /******************************************************************************************/
                    // 此时，当前token遍历到了一个键值对的值时(即到了一个分支尽头)，需要将其放入string数组中
                    value = parser.getText();
                    //如果这个值是单独被包含在中括号数组内(中括号内没有键值对 对应的键)，则key肯定还没有在相应的键上添加索引，所以这里要补上索引
                    if (inArrays[layerOfArray] == true && containNumbers(key, "[") != layerOfArray) {
                        key = key + "[" + arrayIndexs[layerOfArray] + "]";
                    }
                    lines.add(key + "=" + value);

                    /******************************************************************************************/
                    // 每当遍历完一个分支，需要将 key截断到倒数第二个键
                    int dotOffset = key.lastIndexOf(DOT);
                    if (key.length() - 1 == key.lastIndexOf("]")) {
                        arrayCuteds[layerOfArray] = true;
                    }
                    if (dotOffset > 0) {
                        key = key.substring(0, dotOffset);
                    } else {
                        // 若原key中没有"."，则key直接置为""
                        key = "";
                    }


                    // 若截断后剩下的key的最后一个键含有中括号，也就是该键的索引即将更新，则去除掉该中括号子串以便于后面添加新的索引
                    if (key.length() > 0 && key.length() - 1 == key.lastIndexOf("]")) {
                        key = key.substring(0, key.lastIndexOf("["));
                    }

                } else if (JsonToken.END_OBJECT.equals(token)) {    // 基于json格式，如果是一个对象结束(即遇到了右花括号"}"时)

                    // 如果当前token在数组内部，则不需要截断
                    if (inArrays[layerOfArray]) {
                        arrayIndexs[layerOfArray]++;
                    } else {
                        int dotOffset = key.lastIndexOf(DOT);
                        if (dotOffset > 0) {
                            // 若原key中还有"."，则截断到倒数第二个键
                            key = key.substring(0, dotOffset);
                        } else {
                            // 若原key中没有"."，则key直接置为""
                            key = "";
                        }
                    }

                } else if (JsonToken.END_ARRAY.equals(token)) {  //如果遇到右中括号"]"，表示数组的结束
                    // 若当前层次中 含有中括号的键未被切去
                    if (!arrayCuteds[layerOfArray]) {
                        int dotOffset = key.lastIndexOf(DOT);
                        if (dotOffset > 0) {
                            // 若原key中还有"."，则截断到倒数第二个键
                            key = key.substring(0, dotOffset);
                        } else {
                            // 若原key中没有"."，则key直接置为""
                            key = "";
                        }
                    }

                    // 重置该层的变量
                    inArrays[layerOfArray] = false;
                    arrayIndexs[layerOfArray] = 0;
                    arrayCuteds[layerOfArray] = false;

                    // 回退到上一层
                    layerOfArray--;

                    // 若截断后剩下的key的最后一个键含有中括号，也就是上一层中 该键的索引即将更新，则去除掉该中括号子串 以便于后面添加新的索引
                    if (key.length() > 0 && key.length() - 1 == key.lastIndexOf("]")) {
                        key = key.substring(0, key.lastIndexOf("["));
                    }
                }
                token = parser.nextToken();
            }
            parser.close();

            // 将String字符串数组中的内容打印出来
            for (String line : lines) {
                line = line.replace(DOT, ".");
                System.out.println(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算 字符串str中包含多少个 模式串s
     *
     * @param str 主字符串
     * @param s   模式串
     * @return
     */
    private static int containNumbers(String str, String s) {
        int count = 0;
        for (int i = 0; i < str.length(); ) {
            int c = -1;
            c = str.indexOf(s);
            if (c != -1) {
                str = str.substring(c + 1);
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String path = "D:/其他/io/copyTemplete/application.yml";

        yaml2Prop(path);
    }

}
