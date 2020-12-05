package com.cdu.questionnaire.utils;


import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class UpLoadUtils {

    private static String path;

    static {
        try {
            path = (ResourceUtils.getURL("classpath:").getPath()+ "static/photo");
            path= path.replace('/' , '\\').substring(1 , path.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String uploadPhoto(MultipartFile photo){
        // 文件名称 = UUID + 原名称
        String filename = UUID.randomUUID() + "-" + photo.getOriginalFilename();
        File file = new File(path, filename);
        try {
            photo.transferTo(file);
            return "/photo/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
