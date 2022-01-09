package com.gjf.bit_couple_of_a_week.service;

import com.gjf.bit_couple_of_a_week.domain.Couple;
import com.gjf.bit_couple_of_a_week.vo.CoupleVo;

import java.util.List;

public interface CoupleService {
    Couple addNewCouple(Couple couple);

    Couple randomAddNewCoupleForMale(Integer maleId);
    Couple randomAddNewCoupleForFemale(Integer femaleId);

    Couple getNewCoupleByLauncher(Integer launcher);

    List<Couple> getAllCoupleAppliedBySelf(Integer launcher);

    List<Couple> getNewCoupleAppliedByOther(Integer id);

    Couple acceptCouple(CoupleVo coupleVo, Integer id);

    Couple acceptCouple(Integer coupleId, Integer id);
    Couple rejectCouple(Integer id);
}
