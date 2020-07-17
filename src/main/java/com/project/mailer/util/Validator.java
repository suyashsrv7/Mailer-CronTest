package com.project.mailer.util;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

public class Validator {

    public static boolean csvValidator(MultipartFile file) {
        if(file.isEmpty() || !file.getContentType().equals("text/csv")) {
            return false;
        }
        return true;
    }
}
