package com.gjf.bit_couple_of_a_week.service.impl;

import com.gjf.bit_couple_of_a_week.domain.Post;
import com.gjf.bit_couple_of_a_week.exception.PostDaoException;
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
        String today = simpleDateFormat.format(date);
//        post.setTime(simpleDateFormat.format(date));
        Post todayPost = postRepository.findPostByCoupleIdAndTime(post.getCoupleId(), today);
        if (todayPost == null)
            throw new PostDaoException("您今天已经发过帖子，不可重复发送");
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

    @Override
    public List<Post> getAllPostByUserId(Integer id) {
        return postRepository.findAllByUserId(id);
    }

    @Override
    public Post getTodayPostByCoupleId(Integer id) {
        // 设置发帖日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = simpleDateFormat.format(date);
        return postRepository.findPostByCoupleIdAndTime(id, today);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
