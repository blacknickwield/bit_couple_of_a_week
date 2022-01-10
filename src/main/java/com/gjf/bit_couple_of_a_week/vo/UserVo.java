package com.gjf.bit_couple_of_a_week.vo;

import com.gjf.bit_couple_of_a_week.domain.User;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserVo {
    private int id;
    private String username;
    private String password;
    private String name;
    private String schoolId;
    private String imgUrl;
    private Boolean male;
    private String description;

    public UserVo(int id, String username, String password, String name, String imgUrl, Boolean male, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.imgUrl = imgUrl;
        this.male = male;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserVo() {
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public static User convertToPo(UserVo userVo) {
        if (userVo == null)
            return null;
        User user = new User();
        user.setId(userVo.getId());
        user.setUsername(userVo.getUsername());
        user.setImgUrl(userVo.getImgUrl());
        user.setMale(userVo.getMale());
        user.setSchoolId(userVo.schoolId);
        user.setDescription(userVo.getDescription());
        return user;
    }


    public static UserVo convertToVo(User user) {
        if (user == null)
            return null;
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        userVo.setImgUrl(user.getImgUrl());
        userVo.setMale(user.getMale());
        userVo.setSchoolId(user.getSchoolId());
        userVo.setDescription(user.getDescription());
        return userVo;
    }
}
