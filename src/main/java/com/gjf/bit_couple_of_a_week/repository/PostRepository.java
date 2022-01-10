package com.gjf.bit_couple_of_a_week.repository;

import com.gjf.bit_couple_of_a_week.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
    @Query(nativeQuery = true, value = "SELECT * FROM `post` WHERE `male_id` IS NOT NULL AND `male_content` IS NOT NULL AND `female_id` IS NOT NULL AND `female_content` IS NOT NULL ")
    List<Post> findAllPost();

    @Query(nativeQuery = true, value = "SELECT * FROM `post` WHERE `id` = :id")
    Post findPostById(@Param("id") Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM `post` WHERE `couple_id` = :coupleId")
    List<Post> findPostByCoupleId(@Param("coupleId") Integer coupleId);

    @Query(nativeQuery = true, value = "SELECT * FROM `post` WHERE `couple_id` = :coupleId AND `time` = :time")
    Post findPostByCoupleIdAndTime(@Param("coupleId") Integer coupleId, @Param("time") String time);
}
