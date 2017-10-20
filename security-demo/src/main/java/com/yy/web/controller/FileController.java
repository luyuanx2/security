package com.yy.web.controller;

import com.yy.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by luyuanyuan on 2017/10/20.
 */
@RequestMapping("/file")
@RestController
public class FileController {

    private final String fileBasePath = "F:\\idea_workspace\\security\\security-demo\\src\\main\\java\\com\\yy\\web\\controller";

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getSize());

        File localFile = new File(fileBasePath, new Date().getTime() + ".txt");
        FileInfo fileInfo = new FileInfo(localFile.getAbsolutePath());

        file.transferTo(localFile);
        return fileInfo;
    }

    /**
     * 文件下载
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){

        File file = new File(fileBasePath,id + ".txt");
        try(
                FileInputStream fileInputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream();
                ){

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(fileInputStream,outputStream);
            outputStream.flush();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
