package com.gjf.bit_couple_of_a_week.service;

import com.gjf.bit_couple_of_a_week.domain.Test;
import com.gjf.bit_couple_of_a_week.domain.User;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface UserService {
    Test getTestById(Integer id);
    User getUserBySchoolIdAndPassword(String schoolId, String password);
    User login(String schoolId, String password);
    User register(User user);
    User getUserById(Integer id);
    List<User> getAllUser();
}
