package com.gjf.bit_couple_of_a_week.controller;

import com.gjf.bit_couple_of_a_week.domain.User;
import com.gjf.bit_couple_of_a_week.service.UserService;
import com.gjf.bit_couple_of_a_week.util.ResponseResult;
import com.gjf.bit_couple_of_a_week.util.TokenUtil;
import com.gjf.bit_couple_of_a_week.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    /***
     * 登录
     * @param schoolId
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestParam(required = true) String schoolId, @RequestParam(required = true) String password) {
//        User user = userService.getUserBySchoolIdAndPassword(schoolId, password);
        User user = userService.login(schoolId, password);
        UserVo userVo = UserVo.convertToVo(user);
        userVo.setPassword(null);
        String token = TokenUtil.createToken(user.getSchoolId());

        return new ResponseResult()
                .code(200)
                .data("userInfo", UserVo.convertToVo(user))
                .data("token", token)
                .message("登录成功");
    }


    /***
     * 注册
     * @param userVo
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody() UserVo userVo) {
        User user = UserVo.convertToPo(userVo);
        userService.register(user);
        user.setPassword(null);
        return new ResponseResult()
                .code(200)
                .data("userInfo", UserVo.convertToVo(user))
                .message("注册成功!");
    }

    @GetMapping("/test")
    public ResponseResult test() {
        return new ResponseResult()
                .code(200)
                .data("userInfo", userService.getAllUser().stream().map(UserVo::convertToVo).collect(Collectors.toList()))
                .message("测试");
    }

    /***
     * 查询所有用户
     * @return
     */
    @GetMapping("/all")
    public ResponseResult getAllUser() {
        return new ResponseResult()
                .code(200)
                .data("userInfos", userService.getAllUser()
                        .stream()
                        .map(user -> {
                            user.setPassword(null);
                            return UserVo.convertToVo(user);
                        })
                        .collect(Collectors.toList()))
                .message("查询成功");
    }

    /***
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("/query")
    public ResponseResult getUserById(@RequestParam("id") Integer id) {
        User user = userService.getUserById(id);
        user.setPassword(null);
        return new ResponseResult()
                .code(200)
                .data("userInfo", UserVo.convertToVo(user))
                .message("查询成功");
    }
}
