package com.blj.file;

import java.io.*;
import java.util.List;
import java.util.Map;


public class Copyfile {
    public static void main(String[] args) {
//        String sourceFile = "E:/config/application.yml";
//        String targetFile = "E:/file/test/config/application.yml";
//        copy01(sourceFile, targetFile);

//        copyFileAndUpdate();

        copyFileAndUpdate2();
    }

    private static void copyFileAndUpdate() {
        String targetFile = "E:/file/test/config";
        try {
            Map<String, Object> map = ReadFile.getFilePath("E:/config");
            for (String key : map.keySet()) {
//                System.out.println("key = " + key);
//                System.out.println("map.get(key) = " + map.get(key));
                String saveTargetFilePath = targetFile + "/" + map.get(key);
                copy01(key, saveTargetFilePath);
                if (saveTargetFilePath.contains("properties")) {
                    PropertiesUtil util = new PropertiesUtil(key);
                    Map<Object, Object> propertiesMap = util.map;
                    for (Object propertiesKey : propertiesMap.keySet()) {
                        // 查询数据库获取正确的配置数据
                        if (propertiesKey.toString().contains("redis") && propertiesKey.toString().contains("port")) {
                        //更新数据
                            util.upDate(propertiesKey.toString(), "6379", key,saveTargetFilePath);
                        }
                        if (propertiesKey.toString().contains("redis") && propertiesKey.toString().contains("host")) {
                            //更新数据
                            util.upDate(propertiesKey.toString(), "127.0.0.1", key,saveTargetFilePath);

                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void copyFileAndUpdate2() {
        // 目标目录地址
        String targetFile = "D:/其他/io/copyTemplete";
        try {
            // 查询此目录下所有配置文件 (key:文件路径，value:文件名称)
            Map<String, Object> map = ReadFile.getFilePath("D:/其他/io/config");
            for (String key : map.keySet()) {
                String saveTargetFilePath = targetFile + "/" + map.get(key);
                if (saveTargetFilePath.contains("properties")) {
                    PropertiesUtil util = new PropertiesUtil(key);
                    Map<Object, Object> propertiesMap = util.map;
                    for (Object propertiesKey : propertiesMap.keySet()) {
                        // todo 查询数据库获取正确的配置数据
                        if (propertiesKey.toString().equals("spring.redis.host")) {
                            System.out.println("propertiesKey="+propertiesKey);
                            //更新数据
                            util.upDate(propertiesKey.toString(), "127.0.0.1", key,saveTargetFilePath);
                        }
                    }
                }else {
                    try {
                        boolean b = FileControl.copyFile(key, targetFile + "/" + map.get(key));
                        if (b){
                            System.out.println("复制文件成功 ！" );
                        }
                        // todo FileTranferUtils 把yml转成properties
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy01(String sourceFile, String targetFile) {
//	创建对象
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(sourceFile);
            fw = new FileWriter(targetFile);
//		循环读和循环写
            int len = 0;
            while ((len = fr.read()) != -1) {
                fw.write((char) len);
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }


}


