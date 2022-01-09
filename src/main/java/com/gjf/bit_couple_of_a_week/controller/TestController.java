package com.gjf.bit_couple_of_a_week.controller;

import com.gjf.bit_couple_of_a_week.domain.Test;
import com.gjf.bit_couple_of_a_week.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;
    @GetMapping("/get")
    public Test getTest(@RequestParam("id") Integer id) {
        return userService.getTestById(id);
    }
}
