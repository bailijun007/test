package com.blj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;

/**
 * 文件上传与下载
 *
 * @author BaiLiJun on 2020/8/20 9:58 上午
 */
@RestController
@Slf4j
@RequestMapping("/api/file")
public class FileController {

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile file) {

        // 判断文件是否为空
        if (file.isEmpty()) {
            return "文件为空，上传失败!";
        }

        // 获取源文件名称
        String fileRealName = file.getOriginalFilename();

        // 获取源文件大小
        int size = (int) file.getSize();
        System.out.println(fileRealName + "-->" + size);

        // 定义文件的存储路径
        String path = FileController.class.getResource("/").getPath() + fileRealName;


        // 新建本地文件
        File dest = new File(path);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            // 创建父目录
            dest.getParentFile().mkdir();
        }

        // 定义时间格式
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 资源信息存数据库
//            recourcesMapper.save(fileName,groupName,owner,fileMessage,path,df.format(new Date()),0);
            //保存文件
            file.transferTo(dest);
            log.info("资源上传成功,上传资源的路径地址在：{}", path);
            return "上传成功";
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            log.info("资源上传失败");
        }

        return "上传失败";
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public String download(HttpServletResponse response
                           //   , @RequestParam(value = "id") Integer id
    ) {

        // 从数据库读取文件路径
        //  String path = recourcesMapper.findResourcePath(id);
        String path = FileController.class.getResource("/").getPath() + "step1.png";

        // 切割字符串
        String[] str = path.split("/");
        // 获取文件名包括拓展名
        String filename = str[str.length - 1];

        // 根据实际的文件创建文件对象
        File file = new File(path);

        //判断文件父目录是否存在
        if (file.exists()) {
            // 设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
            response.setContentType("application/force-download");
            // 通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            byte[] buffer = new byte[1024];
            //文件输入流
            FileInputStream fis = null;
            // 输入流缓存
            BufferedInputStream bis = null;
            // 输出流
            OutputStream os = null;
            try {
                // 获取响应对象的输出流
                os = response.getOutputStream();
                // 文件输入流
                fis = new FileInputStream(file);
                // 缓存输入流
                bis = new BufferedInputStream(fis);

                // 数组从输入流缓存读取数据,返回数据是读取的byte个数
                int i = bis.read(buffer);
                while (i != -1) {
                    // 数据写到输出流
                    os.write(buffer);
                    // 数组从输入流缓存读取数据
                    i = bis.read(buffer);
                }
                log.info("文件下载成功");
                return "文件下载成功";
            } catch (Exception e) {
                log.info("文件下载失败");
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        // 关闭缓存流
                        bis.close();
                    }
                    if (fis != null) {
                        // 关闭文件流
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        log.info("文件下载失败");
        return "文件下载失败";
    }


}
