package com.gjf.bit_couple_of_a_week.service.impl;

import com.gjf.bit_couple_of_a_week.domain.Post;
import com.gjf.bit_couple_of_a_week.repository.PostRepository;
import com.gjf.bit_couple_of_a_week.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post addNewPost(Post post) {
        // 设置发帖日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        post.setTime(simpleDateFormat.format(date));
        postRepository.save(post);
        return post;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAllPost();
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findPostById(id);
    }
}
