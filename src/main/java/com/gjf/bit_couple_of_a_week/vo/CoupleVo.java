package com.gjf.bit_couple_of_a_week.vo;

import com.gjf.bit_couple_of_a_week.domain.Couple;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ToString
public class CoupleVo {

    private int id;
    private int launcher;
    private int maleId;
    private int femaleId;
    private String status;
    private String note;
    private String startTime;
    private int duration;

    public CoupleVo(int id, int launcher, int maleId, int femaleId, String status, String note, String startTime, int duration) {
        this.id = id;
        this.launcher = launcher;
        this.maleId = maleId;
        this.femaleId = femaleId;
        this.status = status;
        this.note = note;
        this.startTime = startTime;
        this.duration = duration;
    }

    public CoupleVo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLauncher() {
        return launcher;
    }

    public void setLauncher(int launcher) {
        this.launcher = launcher;
    }

    public int getMaleId() {
        return maleId;
    }

    public void setMaleId(int maleId) {
        this.maleId = maleId;
    }

    public int getFemaleId() {
        return femaleId;
    }

    public void setFemaleId(int femaleId) {
        this.femaleId = femaleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static Couple convertToPo(CoupleVo coupleVo) {
        if (coupleVo == null)
            return null;
        Couple couple = new Couple();
        couple.setId(coupleVo.getId());
        couple.setLauncher(coupleVo.getLauncher());
        couple.setMaleId(coupleVo.getMaleId());
        couple.setFemaleId(coupleVo.getFemaleId());
        couple.setStatus(coupleVo.getStatus());
        couple.setDuration(coupleVo.getDuration());
        couple.setStartTime(coupleVo.getStartTime());
        couple.setNote(coupleVo.getNote());
        return couple;
    }

    public static CoupleVo convertToVo(Couple couple) {
        if (couple == null)
            return null;
        CoupleVo coupleVo = new CoupleVo();
        coupleVo.setId(couple.getId());
        coupleVo.setLauncher(couple.getLauncher());
        if (couple.getMaleId() != null)
            coupleVo.setMaleId(couple.getMaleId());
        if (couple.getFemaleId() != null)
            coupleVo.setFemaleId(couple.getFemaleId());
        coupleVo.setStatus(couple.getStatus());
        coupleVo.setStartTime(couple.getStartTime());
        coupleVo.setDuration(couple.getDuration());
        coupleVo.setNote(couple.getNote());
        return coupleVo;
    }
}
