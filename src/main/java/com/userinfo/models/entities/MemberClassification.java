package com.userinfo.models.entities;

import com.userinfo.exceptions.SalaryNotMatchClassifyException;

public enum MemberClassification {
    Platinum,
    Gold,
    Silver;

    public static MemberClassification valueOf(int salary) {
        if (salary > 50000) {
            return Platinum;
        } else if (salary >= 30000) {
            return Gold;
        } else if (salary >= 15000) {
            return Silver;
        } else {
            throw new SalaryNotMatchClassifyException();
        }
    }
}