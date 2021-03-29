package com.blj.file;

import com.blj.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFile {
    private static final Logger logger = LoggerFactory.getLogger(ReadFile.class);


    public ReadFile() {
    }

    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());

            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "/" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());

                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "/" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }


    public static Map<String, Object> getFilePath(String filepath) throws FileNotFoundException, IOException {
        //List<String> list=new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {

            File file = new File(filepath);
            if (file.isDirectory()) {
                // System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "/" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        logger.info("文件全路径:{}", filepath + "/" + filelist[i]);
                        map.put(filepath + "/" + filelist[i], filelist[i]);
                    } else if (readfile.isDirectory()) {
                        logger.info("文件夹下有子文件夹");
                        Map<String, Object> filePath = getFilePath(filepath + "/" + filelist[i]);
                        map.putAll(filePath);
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return map;
    }


    /**
     * 删除某个文件夹下的所有文件夹和文件
     */


        /*public static boolean deletefile(String delpath)
                        throws FileNotFoundException, IOException {
                try {

                        File file = new File(delpath);
                        if (!file.isDirectory()) {
                                System.out.println("1");
                                file.delete();
                        } else if (file.isDirectory()) {
                                System.out.println("2");
                                String[] filelist = file.list();
                                for (int i = 0; i < filelist.length; i++) {
                                        File delfile = new File(delpath + "\\" + filelist[i]);
                                        if (!delfile.isDirectory()) {
                                                System.out.println("path=" + delfile.getPath());
                                                System.out.println("absolutepath="
                                                                + delfile.getAbsolutePath());
                                                System.out.println("name=" + delfile.getName());
                                                delfile.delete();
                                                System.out.println("删除文件成功");
                                        } else if (delfile.isDirectory()) {
                                                deletefile(delpath + "\\" + filelist[i]);
                                        }
                                }
                                file.delete();

                        }

                } catch (FileNotFoundException e) {
                        System.out.println("deletefile()   Exception:" + e.getMessage());
                }
                return true;
        }*/
    public static void main(String[] args) {
        try {
            readfile("E:/config");
            // deletefile("D:/file");
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        System.out.println("ok");
    }

}
