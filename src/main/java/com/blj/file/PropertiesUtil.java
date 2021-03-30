package com.blj.file;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: KL-Skeleton
 * @Description: 只要是文件都行， setting.ini 或 setting.properties 都是可以使用这个类的，格式一定要key=value 。注释要用#
 * @Date: Created in 15:44 2020/6/29
 */

public class PropertiesUtil {

//    public String url = "E:/config/config.properties";//设置默认的读取文件

//    public String url = null;

    public Map<Object, Object> map = new HashMap<>();


//    PropertiesUtil() {
//        read();
//
//    }

    public PropertiesUtil(String sourceFilePath) {
//        this.url = url;
        read(sourceFilePath);


    }

    //更新数据
    public void upDate(String key, String valuse, String sourceFilePath, String targeFilePath) {

        String data = setReadFileListener(new ReadFileListener() {
            @Override
            String setLine(String line) {
                if (!line.equals("")) {
                    String data = "";
                    String string[] = line.replaceFirst("=", "#=#").split("#=#");
                    if (string.length != 0) {
                        String keystr = string[0].replaceAll("\\s*", "");
                        if (keystr.equals(key) && !line.contains("#")) {
                            data += key + "=" + valuse + "\n";
                        } else {
                            data += line + "\n";
                        }
                    }
                    return data;
                } else {
                    return "";
                }
            }
        }, sourceFilePath);
        saveData(data, sourceFilePath, targeFilePath);

    }


    //读取默认的url配置文件
    public void read(String sourceFilePath) {
        setReadFileListener(new ReadFileListener() {
            @Override
            String setLine(String line) {
                if (line.contains("#")) line = line.split("#")[0];//不读取注释
                if (!line.equals("")) {
                    String string[] = line.replaceFirst("=", "#=#").split("#=#");//替换等号为特殊字符串#=#，然后分割（这里任意都行，只要不存在歧义）
                    if (string != null) {
                        map.put(string[0], string[1]);
                    }
                }
                return "";
            }
        }, sourceFilePath);

    }

    //删除数据
    public void delete(String key, String sourcePath, String targePath) {
        String data = setReadFileListener(new ReadFileListener() {
            @Override
            String setLine(String line) {
                if (!line.equals("")) {
                    String data = "";
                    String string[] = line.replaceFirst("=", "#=#").split("#=#");
                    if (string.length != 0) {
                        String keystr = string[0].replaceAll("\\s*", "");
                        if (keystr.equals(key)) ;
                        else {
                            data += line + "\n";
                        }
                    }
                    return data;
                } else return "";
            }
        }, sourcePath);
        saveData(data, sourcePath, targePath);
    }

    //添加数据
    public void add(String key, String value, String targeFilePath) {
        File file = new File(targeFilePath);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n" + key + "=" + value);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //设置文件读取的监听器，每读取一行数据，返回一次回调函数
    private String setReadFileListener(ReadFileListener readFileListener, String sourceFilePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(sourceFilePath)));
            String tempstr = "";
            String data = "";
            while ((tempstr = reader.readLine()) != null) {
                data += readFileListener.setLine(tempstr);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void saveData(String data, String sourcePath, String targeFilePath) {
        try {
            FileWriter writer = null;
            if (StringUtils.isEmpty(targeFilePath)) {
                writer = new FileWriter(sourcePath);
            } else {
                writer = new FileWriter(targeFilePath);
            }

            // 向文件写入内容
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改完成后，重新读取，刷新数据
        read(targeFilePath);
    }


    public String getString(String key) {
        return String.valueOf(map.get(key));
    }

    public int getInt(String key) {
        return Integer.valueOf(String.valueOf(map.get(key)).replaceAll("\\s*", ""));
    }

    public double getDouble(String key) {
        return Double.valueOf(String.valueOf(map.get(key)).replaceAll("\\s*", ""));
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(String.valueOf(map.get(key)).replaceAll("\\s*", ""));
    }

    public Object get(Object key) {
        return map.get(key);
    }

    public String[] getStrings(String key) {
        return getString(key).split(",");
    }

    public int[] getInts(String key) {
        return StringToInt(getString(key).split(","));
    }

    public Object[] getObjects(String key) {
        return StringToObject(getString(key).split(","));
    }

    public int[] StringToInt(String[] arrs) {

        int[] ints = new int[arrs.length];

        for (int i = 0; i < arrs.length; i++) {

            ints[i] = Integer.parseInt(arrs[i]);

        }

        return ints;

    }

    public Object[] StringToObject(String[] arrs) {

        Object[] ints = new Object[arrs.length];

        for (int i = 0; i < arrs.length; i++) {

            ints[i] = (Object) arrs[i];

        }

        return ints;

    }


}

abstract class ReadFileListener {
    String setLine(String line) {
        return "";
    }
}

 