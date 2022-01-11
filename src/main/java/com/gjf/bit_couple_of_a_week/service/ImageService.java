package com.gjf.bit_couple_of_a_week.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String upload(MultipartFile file);
//    String downLoad(String url);
}
