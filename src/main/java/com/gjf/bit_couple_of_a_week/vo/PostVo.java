package com.gjf.bit_couple_of_a_week.vo;

import com.gjf.bit_couple_of_a_week.domain.Post;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ToString
public class PostVo {
    private int id;
    private int coupleId;
    private int maleId;
    private int femaleId;
    private String maleContent;
    private String maleUrl;
    private String femaleContent;
    private String femaleUrl;

    public PostVo() {
    }

    public PostVo(int id, int coupleId, int maleId, int femaleId, String maleContent, String maleUrl, String femaleContent, String femaleUrl) {
        this.id = id;
        this.coupleId = coupleId;
        this.maleId = maleId;
        this.femaleId = femaleId;
        this.maleContent = maleContent;
        this.maleUrl = maleUrl;
        this.femaleContent = femaleContent;
        this.femaleUrl = femaleUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoupleId() {
        return coupleId;
    }

    public void setCoupleId(int coupleId) {
        this.coupleId = coupleId;
    }

    public int getMaleId() {
        return maleId;
    }

    public void setMaleId(int maleId) {
        this.maleId = maleId;
    }

    public int getFemaleId() {
        return femaleId;
    }

    public void setFemaleId(int femaleId) {
        this.femaleId = femaleId;
    }

    public String getMaleContent() {
        return maleContent;
    }

    public void setMaleContent(String maleContent) {
        this.maleContent = maleContent;
    }

    public String getMaleUrl() {
        return maleUrl;
    }

    public void setMaleUrl(String maleUrl) {
        this.maleUrl = maleUrl;
    }

    public String getFemaleContent() {
        return femaleContent;
    }

    public void setFemaleContent(String femaleContent) {
        this.femaleContent = femaleContent;
    }

    public String getFemaleUrl() {
        return femaleUrl;
    }

    public void setFemaleUrl(String femaleUrl) {
        this.femaleUrl = femaleUrl;
    }

    public static Post convertToPo(PostVo postVo) {
        if (postVo == null)
            return null;
        Post post = new Post();
        post.setId(postVo.getId());
        post.setCoupleId(postVo.getCoupleId());
        post.setMaleId(postVo.getMaleId());
        post.setFemaleId(postVo.getFemaleId());
        post.setMaleContent(postVo.getMaleContent());
        post.setMaleUrl(postVo.getMaleUrl());
        post.setFemaleContent(postVo.getFemaleContent());
        post.setFemaleUrl(postVo.getFemaleUrl());
        return post;
    }

    public static PostVo convertToVo(Post post) {
        if (post == null)
            return null;
        PostVo postVo = new PostVo();
        postVo.setId(post.getId());
        postVo.setCoupleId(post.getCoupleId());
        postVo.setFemaleId(post.getFemaleId());
        postVo.setMaleId(post.getMaleId());
        postVo.setFemaleContent(post.getFemaleContent());
        postVo.setFemaleUrl(post.getFemaleUrl());
        postVo.setMaleContent(post.getMaleContent());
        postVo.setMaleUrl(post.getMaleUrl());
        return postVo;
    }
}

