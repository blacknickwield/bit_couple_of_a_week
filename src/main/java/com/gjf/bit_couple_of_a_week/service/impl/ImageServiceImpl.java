package com.gjf.bit_couple_of_a_week.service.impl;

import com.gjf.bit_couple_of_a_week.exception.ImageDaoException;
import com.gjf.bit_couple_of_a_week.service.ImageService;
import com.gjf.bit_couple_of_a_week.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private IdWorker idWorker;

    @Override
    public String upload(MultipartFile file) {
        String type = file.getContentType();
        assert type != null;
        if (!(type.equals("image/jpeg") || type.equals("image/png"))) {
            throw new ImageDaoException("图片格式不符");
        }
        long id = idWorker.nextId();
        String fileName = Long.toString(id) + (type.equals("image/jpeg") ? ".jpeg" : ".png");
        File dest= new File(fileName);

        try {
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ImageDaoException("图片上传失败");
        }
        return fileName;
    }
}
