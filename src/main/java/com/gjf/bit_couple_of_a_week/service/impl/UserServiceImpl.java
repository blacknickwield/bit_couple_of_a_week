package com.gjf.bit_couple_of_a_week.service.impl;

import com.gjf.bit_couple_of_a_week.domain.Test;
import com.gjf.bit_couple_of_a_week.domain.User;
import com.gjf.bit_couple_of_a_week.exception.UserDaoException;
import com.gjf.bit_couple_of_a_week.repository.TestRepository;
import com.gjf.bit_couple_of_a_week.repository.UserRepository;
import com.gjf.bit_couple_of_a_week.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserRepository userRepository;
    public Test getTestById(Integer id) {
        return testRepository.findTestById(id);
    }

    public User getUserBySchoolIdAndPassword(String schoolId, String password) {
        return userRepository.findUserBySchoolIdAndPassword(schoolId, password);
    }

    @Transactional(readOnly = true)
    @Override
    public User login(String schoolId, String password) {
        if (schoolId == null || schoolId.equals(""))
            throw new UserDaoException("用户学号不能为空！");
        if (password == null || password.equals(""))
            throw new UserDaoException("用户学号不能为空！");
        User user =  userRepository.findUserBySchoolIdAndPassword(schoolId, password);
        if (user == null)
            throw new UserDaoException("不存在该用户，请重新检查学号和密码！");
        return user;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    @Override
    public User register(User user) {
        if (user.getSchoolId() == null)
            throw new UserDaoException("用户学号不能为空！");
        if (user.getPassword() == null)
            throw new UserDaoException("用户密码不能为空！");

        if (userRepository.findUserBySchoolId(user.getSchoolId()) != null)
            throw new UserDaoException("该学号的用户已经存在，请重新注册！");
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


}
