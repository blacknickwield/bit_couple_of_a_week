package com.gjf.bit_couple_of_a_week.service.impl;

import com.gjf.bit_couple_of_a_week.service.ImageService;
import com.gjf.bit_couple_of_a_week.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private IdWorker idWorker;


}
