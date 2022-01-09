package com.gjf.bit_couple_of_a_week.repository;

import com.gjf.bit_couple_of_a_week.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TestRepository extends JpaRepository<Test, Integer>, JpaSpecificationExecutor<Test> {
    Test findTestById(Integer id);

}
