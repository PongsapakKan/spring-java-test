package com.userinfo.models.entities;

public class MemberType {
    enum Classification {
        Platinum,
        Gold,
        Silver;
    }

    public static Classification applyMemberType(int salary) {
        if (salary > 50000) {
            return Classification.Platinum;
        } else if (salary >= 30000) {
            return Classification.Gold;
        } else {
            return Classification.Silver;
        }
    }
}
