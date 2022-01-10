package com.gjf.bit_couple_of_a_week.controller;

import com.gjf.bit_couple_of_a_week.domain.Couple;
import com.gjf.bit_couple_of_a_week.domain.Post;
import com.gjf.bit_couple_of_a_week.domain.User;
import com.gjf.bit_couple_of_a_week.service.CoupleService;
import com.gjf.bit_couple_of_a_week.service.PostService;
import com.gjf.bit_couple_of_a_week.service.UserService;
import com.gjf.bit_couple_of_a_week.util.ResponseResult;
import com.gjf.bit_couple_of_a_week.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequestMapping("/post")
@RestController
@CrossOrigin
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoupleService coupleService;

    @GetMapping("/query")
    public ResponseResult getPostById(@Param("id") Integer id) {
        return new ResponseResult()
                .code(200)
                .data("postInfo", PostVo.convertToVo(postService.getPostById(id)))
                .message("查询成功");
    }

    /***
     * 发送帖子
     * @param id 用户id
     * @return
     */
    @PostMapping("/post")
    public ResponseResult post(@Param("id") Integer id, @RequestBody() PostVo postVo) {
        User user = userService.getUserById(id);
        Post post = PostVo.convertToPo(postVo);
        Couple couple;
        if (user.getMale()) {
            couple = coupleService.getOngoingCoupleByMaleId(id);
        } else {
            couple = coupleService.getOngoingCoupleByFemaleId(id);
        }
        post.setCoupleId(couple.getId());
        postService.addNewPost(post);
        return new ResponseResult()
                .code(200)
                .data("postInfo", post)
                .message("您的帖子已发送，请等待CP填写");
    }

    /***
     * 查看所有帖子
     * 只有男女双方都编辑过的帖子才算是成功发布
     * @return
     */
    @GetMapping("/all")
    public ResponseResult getAllPost() {
        return new ResponseResult()
                .code(200)
                .data("posts", postService.getAllPost()
                        .stream()
                        .map(post -> PostVo.convertToVo(post))
                        .collect(Collectors.toList()))
                .message("查询成功");
    }
}
