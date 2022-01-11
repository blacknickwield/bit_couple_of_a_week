package com.gjf.bit_couple_of_a_week.controller;

import com.gjf.bit_couple_of_a_week.domain.Couple;
import com.gjf.bit_couple_of_a_week.domain.Post;
import com.gjf.bit_couple_of_a_week.domain.User;
import com.gjf.bit_couple_of_a_week.service.CoupleService;
import com.gjf.bit_couple_of_a_week.service.ImageService;
import com.gjf.bit_couple_of_a_week.service.PostService;
import com.gjf.bit_couple_of_a_week.service.UserService;
import com.gjf.bit_couple_of_a_week.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RequestMapping("/image")
@RestController
@CrossOrigin
public class ImageController {
    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PostService postService;

    @Autowired
    private CoupleService coupleService;

    @Value("${spring.servlet.multipart.location}")
    private String rootUrl;

    /***
     * 图片下载
     * @param fileName
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
        String filepath = rootUrl + "/" + fileName;
        System.out.println(filepath);
        FileInputStream inputStream = new FileInputStream(filepath);
        byte[] buf = new byte[4*1024];
        int len = 0;
        System.out.println(fileName.indexOf('.'));
        String suffix = fileName.substring(fileName.indexOf('.') + 1);
        System.out.println(suffix);
        response.setContentType("image/" + suffix);
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }

        inputStream.close();
    }

    // 上传用户头像
    @PostMapping("/upload/user")
    public ResponseResult uploadUserImage(@RequestBody MultipartFile file, @RequestParam(name = "id") Integer id) {
        User user = userService.getUserById(id);
        String fileName = imageService.upload(file);
        user.setImgUrl(fileName);
        userService.save(user);
        return new ResponseResult()
                .code(200)
                .message("头像上传成功");
    }



    // 上传帖子中男性图片
    @PostMapping("/upload/post/male")
    public ResponseResult uploadPostMaleImage(@RequestBody MultipartFile file, @RequestParam("maleId") Integer maleId) {
        User user = userService.getUserById(maleId);
        Couple couple = coupleService.getOngoingCoupleByMaleId(maleId);
        if (couple == null) {

            //TODO
            return null;
        }
        Post post = postService.getTodayPostByCoupleId(couple.getId());
        String fileName = imageService.upload(file);
        post.setMaleUrl(fileName);
        postService.save(post);
        return new ResponseResult()
                .code(200)
                .message("图片上传成功");
    }



    // 上传帖子中女方的图片
    @PostMapping("/upload/post/female")
    public ResponseResult uploadPostFemaleImage(@RequestBody MultipartFile file, @RequestParam("femaleId") Integer femaleId) {
        User user = userService.getUserById(femaleId);
        Couple couple = coupleService.getOngoingCoupleByFemaleId(femaleId);

        if (couple == null) {

            //TODO
            return null;
        }
        Post post = postService.getTodayPostByCoupleId(couple.getId());
        String fileName = imageService.upload(file);
        post.setFemaleUrl(fileName);
        postService.save(post);
        return new ResponseResult()
                .code(200)
                .message("图片上传成功");
    }

//    // 下载帖子中男方的图片
//    @GetMapping("/download/post/male")
//    public ResponseResult downloadPostMaleImage() {
//
//    }
//    // 下载用户头像
//    @GetMapping("/download/user")
//    public ResponseResult downloadUserImage() {
//
//    }
//    // 下载帖子中女方的图片
//    @GetMapping("/download/post/female")
//    public ResponseResult downloadPostFemaleImage() {
//
//    }

//    /***
//     * 上传文件的测试
//     * @param file
//     * @return
//     */
//    @PostMapping("test")
//    public ResponseResult test(@RequestBody MultipartFile file) {
//        String type = file.getContentType();
//        assert type != null;
//        if (!(type.equals("image/jpeg") || type.equals("image/png"))) {
//            return new ResponseResult()
//                    .code(500)
//                    .message("图片格式不符");
//        }
//        System.out.println(type);
////        String fileName = file.getName();
//        String fileName;
//        if (type.equals("image/jpeg"))
//            fileName = file.getName() + ".jpeg";
//        else
//            fileName = file.getName() + ".png";
//        System.out.println(fileName);
//        File dest= new File(fileName);
//        try {
//            file.transferTo(dest);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseResult()
//                    .code(500)
//                    .message("存储图片时出错");
//        }
//        return new ResponseResult()
//                .code(200)
//                .message("上传成功");
//    }
}
