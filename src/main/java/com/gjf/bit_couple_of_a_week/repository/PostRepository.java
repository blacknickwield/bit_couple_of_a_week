package com.gjf.bit_couple_of_a_week.repository;

import com.gjf.bit_couple_of_a_week.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

}
