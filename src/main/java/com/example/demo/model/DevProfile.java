package com.example.demo.model;

public class DevProfile implements SystemProfile {

    public static final String msg = "Current profile is dev";

    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}