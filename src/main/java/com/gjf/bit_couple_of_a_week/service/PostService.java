package com.gjf.bit_couple_of_a_week.service;

import com.gjf.bit_couple_of_a_week.domain.Post;


import java.util.List;


public interface PostService {
    Post addNewPost(Post post);

    List<Post> getAllPost();

    Post getPostById(Integer id);
}
