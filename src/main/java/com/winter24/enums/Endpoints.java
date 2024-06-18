package com.winter24.enums;

import lombok.Getter;

@Getter
public enum Endpoints {

    ADMIN("Admin"),
    PIM("PIM"),
    LEAVE("Leave"),
    TIME("Time"),
    RECRUITMENT("Recruitment"),
    MYINFO("My Info"),
    PERFORMANCE("Performance"),
    DASHBOARD("Dashboard"),
    DIRECTORY("Directory"),
    MAINTENANCE("Maintenance"),
    CLAIM("Claim"),
    BUZZ("Buzz");

    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }
}