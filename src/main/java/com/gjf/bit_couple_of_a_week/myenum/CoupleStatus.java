package com.gjf.bit_couple_of_a_week.myenum;

public enum CoupleStatus {
    NEW("new"),
    ONGOING("ongoing"),
    REJECT("reject"),
    FINISH("finish");

    private String status;

    CoupleStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
