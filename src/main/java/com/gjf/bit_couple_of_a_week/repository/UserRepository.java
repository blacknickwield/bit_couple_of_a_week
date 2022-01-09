package com.gjf.bit_couple_of_a_week.repository;

import com.gjf.bit_couple_of_a_week.domain.User;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE `id` = :id")
    User findUserById(Integer id);
    User findUserBySchoolId(String schoolId);
    List<User> findAll();

    @Query(nativeQuery = true, value = "SELECT * from `user` WHERE `school_id` = :schoolId AND `password` = :password")
    User findUserBySchoolIdAndPassword(String schoolId, String password);

}
