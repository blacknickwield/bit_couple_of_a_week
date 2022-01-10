package com.gjf.bit_couple_of_a_week.repository;

import com.gjf.bit_couple_of_a_week.domain.Couple;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoupleRepository extends CrudRepository<Couple, Integer>, JpaSpecificationExecutor<Couple> {
    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `id` = :id")
    Couple findCoupleById(@Param("id") Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `launcher` = :otherId AND ((`male` = :other AND `female` = :id) OR (`female` = :otherId AND `male` = :id))")
    List<Couple> getCoupleAppliedByOther(@Param("id") Integer id, @Param("otherId") Integer otherId);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `launcher` != :id AND `female` = :id OR `male` = :id AND `status` = :newCP")
    List<Couple> getNewCoupleAppliedByOther(@Param("id") Integer id, @Param("newCP") String newCP);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `launcher` = :id")
    Couple getCoupleAppliedBySelf(@Param("id") Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `launcher` = :launcherId AND `status` = :newCP")
    Couple findNewCoupleByLauncher(@Param("launcherId") Integer launcherId, @Param("newCP") String newCP);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `launcher` = :launcherId AND `status` = :ongoing")
    Couple findOngoingCoupleByLauncher(@Param("launcherId") Integer launcherId, @Param("ongoing") String ongoing);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `launcher` = :launcherId AND (`status` = :ongoing OR `status` = :newCP)")
    Couple findNewAndOngoingCoupleByLauncher(@Param("launcherId") Integer launcherId, @Param("newCP") String newCP, @Param("ongoing") String ongoing);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `launcher` = :launcherId")
    List<Couple> findCoupleByLauncher(@Param("launcherId") Integer launcherId);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `female` IS NULL ")
    List<Couple> findNewCoupleAppliedByMale();

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `male` IS NULL ")
    List<Couple> findNewCoupleAppliedByFemale();

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE (`launcher` = :id OR `male` = :id OR `female` = :id) AND `status` = :newCP")
    List<Couple> findNewCoupleRelated(@Param("id") Integer id, @Param("newCP") String newCP);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `male` = :maleId AND `status` = :ongoing")
    Couple findOngoingCoupleByMaleId(@Param("maleId") Integer maleId, @Param("ongoing") String ongoing);

    @Query(nativeQuery = true, value = "SELECT * FROM `couple` WHERE `female` = :femaleId AND `status` = :ongoing")
    Couple findOngoingCoupleByFemaleId(@Param("femaleId") Integer femaleId, @Param("ongoing") String ongoing);
}
