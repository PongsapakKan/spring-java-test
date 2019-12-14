package com.userinfo.models.entities;

public enum MemberClassification {
    Platinum,
    Gold,
    Silver;

    public static MemberClassification valueOf(int salary) {
        if (salary > 50000) {
            return Platinum;
        } else if (salary >= 30000) {
            return Gold;
        } else {
            return Silver;
        }
    }
}