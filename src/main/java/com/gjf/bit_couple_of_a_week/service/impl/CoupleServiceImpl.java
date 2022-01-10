package com.gjf.bit_couple_of_a_week.service.impl;

import com.gjf.bit_couple_of_a_week.domain.Couple;
import com.gjf.bit_couple_of_a_week.exception.CoupleDaoException;
import com.gjf.bit_couple_of_a_week.myenum.CoupleStatus;
import com.gjf.bit_couple_of_a_week.repository.CoupleRepository;
import com.gjf.bit_couple_of_a_week.repository.UserRepository;
import com.gjf.bit_couple_of_a_week.service.CoupleService;
import com.gjf.bit_couple_of_a_week.vo.CoupleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CoupleServiceImpl implements CoupleService {
    @Autowired
    CoupleRepository coupleRepository;
    @Autowired
    UserRepository userRepository;

    private static Random random = new Random();

    @Override
    public Couple addNewCouple(Couple couple) {
        Integer launcherId = couple.getLauncher();
        Couple myCouple = coupleRepository.findNewAndOngoingCoupleByLauncher(launcherId, CoupleStatus.NEW.getStatus(), CoupleStatus.ONGOING.getStatus());
        if (myCouple != null)
            throw new CoupleDaoException("您当前有新发起的或正在进行的CP活动，无法发起新的CP请求");

        // 对方用户的id
        int id;
        if (couple.getLauncher() == couple.getMaleId()) {
            id = couple.getFemaleId();
        } else {
            id = couple.getMaleId();
        }
        // 检查对方是否有正在进行的CP活动
        Couple otherNewCouple = coupleRepository.findOngoingCoupleByLauncher(id, CoupleStatus.ONGOING.getStatus());
        if (otherNewCouple != null)
            throw new CoupleDaoException("对方有正在进行的CP活动，无法发起该CP请求");
        couple.setStatus(CoupleStatus.NEW.getStatus());
        coupleRepository.save(couple);
        return couple;
    }

    @Override
    public Couple randomAddNewCoupleForMale(Integer maleId) {
        List<Couple> couples = coupleRepository.findNewCoupleAppliedByFemale();
        // 没有发起CP请求的女用户，该男用户发起请求
        if (couples == null || couples.size() == 0) {
            Couple couple = new Couple();
            couple.setLauncher(maleId);
            couple.setMaleId(maleId);
            couple.setDuration(2);
            couple.setStatus(CoupleStatus.NEW.getStatus());
            coupleRepository.save(couple);
            return null;
        }
        // 有发起CP请求的女用户，则随机选择一个女用户组成CP
        int index = random.nextInt(couples.size());
        Couple couple = couples.get(index);
        couple.setMaleId(maleId);
        couple.setStatus(CoupleStatus.ONGOING.getStatus());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        couple.setStartTime(simpleDateFormat.format(date));
        coupleRepository.save(couple);
        return couple;
    }

    @Override
    public Couple randomAddNewCoupleForFemale(Integer femaleId) {
        List<Couple> couples = coupleRepository.findNewCoupleAppliedByMale();
        // 没有发起CP请求的男用户，该女用户发起请求
        if (couples == null || couples.size() == 0) {
            Couple couple = new Couple();
            couple.setLauncher(femaleId);
            couple.setFemaleId(femaleId);
            couple.setDuration(2);
            couple.setStatus(CoupleStatus.NEW.getStatus());
//            couple.setMaleId(null);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            couple.setStartTime(simpleDateFormat.format(date));
            coupleRepository.save(couple);
            return null;
        }
        // 有发起CP请求的男用户，则随机选择一个男用户组成CP
        int index = random.nextInt(couples.size());
        Couple couple = couples.get(index);
        couple.setFemaleId(femaleId);
        couple.setStatus(CoupleStatus.NEW.getStatus());
        coupleRepository.save(couple);
        return couple;
    }


    @Override
    public Couple getNewCoupleByLauncher(Integer launcher) {
        return coupleRepository.findNewCoupleByLauncher(launcher, CoupleStatus.NEW.getStatus());
    }

    @Override
    public List<Couple> getAllCoupleAppliedBySelf(Integer launcher) {
        return coupleRepository.findCoupleByLauncher(launcher);
    }

    @Override
    public List<Couple> getNewCoupleAppliedByOther(Integer id) {
        return coupleRepository.getNewCoupleAppliedByOther(id, CoupleStatus.NEW.getStatus());
    }

    @Override
    public Couple acceptCouple(CoupleVo coupleVo, Integer id) {
        Couple couple = CoupleVo.convertToPo(coupleVo);
        // CP组成成功，设置开始时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        couple.setStartTime(simpleDateFormat.format(date));
        if (couple.getLauncher() == couple.getMaleId()) {
            couple.setMaleId(id);
        } else {
            couple.setFemaleId(id);
        }
        couple.setStatus(CoupleStatus.ONGOING.getStatus());
        coupleRepository.save(couple);
        List<Couple> couples = coupleRepository.findNewCoupleRelated(id, CoupleStatus.NEW.getStatus());
        couples.forEach(cp -> cp.setStatus(CoupleStatus.REJECT.getStatus()));
        coupleRepository.saveAll(couples);
        return couple;
    }

    @Override
    public Couple acceptCouple(Integer coupleId, Integer id) {
        Couple couple = coupleRepository.findCoupleById(coupleId);
        if (couple == null)
            throw new CoupleDaoException("不存在该cp");
        // CP组成成功，设置开始时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        couple.setStartTime(simpleDateFormat.format(date));
        couple.setStatus(CoupleStatus.ONGOING.getStatus());
        coupleRepository.save(couple);
        List<Couple> couples = coupleRepository.findNewCoupleRelated(id, CoupleStatus.NEW.getStatus());
        couples.forEach(cp -> cp.setStatus(CoupleStatus.REJECT.getStatus()));
        coupleRepository.saveAll(couples);
        return couple;
    }

    @Override
    public Couple rejectCouple(Integer id) {
        Couple couple = coupleRepository.findCoupleById(id);
        if (couple == null)
            throw new CoupleDaoException("不存在该cp");
        couple.setStatus(CoupleStatus.REJECT.getStatus());
        coupleRepository.save(couple);
        return couple;
    }

    @Override
    public Couple getOngoingCoupleByMaleId(Integer maleId) {
        Couple couple = coupleRepository.findOngoingCoupleByMaleId(maleId, CoupleStatus.ONGOING.getStatus());
        if (couple == null)
            throw new CoupleDaoException("您当前没有正在进行的CP活动");
        return couple;
    }

    @Override
    public Couple getOngoingCoupleByFemaleId(Integer femaleId) {
        Couple couple = coupleRepository.findOngoingCoupleByFemaleId(femaleId, CoupleStatus.ONGOING.getStatus());
        if (couple == null)
            throw new CoupleDaoException("您当前没有正在进行的CP活动");
        return couple;
    }
}
