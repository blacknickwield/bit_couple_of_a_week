package com.gjf.bit_couple_of_a_week.controller;

import com.gjf.bit_couple_of_a_week.domain.User;
import com.gjf.bit_couple_of_a_week.service.ImageService;
import com.gjf.bit_couple_of_a_week.service.UserService;
import com.gjf.bit_couple_of_a_week.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/image")
@RestController
@CrossOrigin
public class ImageController {
    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;
    /***
    // 上传用户头像
    @PostMapping("/upload/user")
    public ResponseResult uploadUserImage(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "id") Integer id) {
        User user = userService.getUserById(id);



    }

    // 下载用户头像
    @GetMapping("/download/user")
    public ResponseResult downloadUserImage() {

    }

    // 上传帖子中男性图片
    @PostMapping("/upload/post/male")
    public ResponseResult uploadPostMaleImage() {

    }

    // 下载帖子中男方的图片
    @GetMapping("/download/post/male")
    public ResponseResult downloadPostMaleImage() {

    }

    // 上传帖子中女方的图片
    @PostMapping("/upload/post/female")
    public ResponseResult uploadPostFemaleImage() {

    }

    // 下载帖子中女方的图片
    @GetMapping("/download/post/female")
    public ResponseResult downloadPostFemaleImage() {

    }
    ***/
}
