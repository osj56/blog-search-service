package com.internet.blogsearchcommon.enums;

import java.util.Arrays;

public enum CompanyCode {
    KAKAO("kakao"), NAVER("naver");

    private String companyName;

    private CompanyCode(String companyName){
        this.companyName = companyName;
    }

    public static CompanyCode fromCompanyName(String companyName){
        return Arrays.stream(values())
                .filter(companyCode -> companyCode.companyName.equals(companyName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("WRONG COMPANY NAME"));
    }
}
