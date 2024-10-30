package com.nhnacademy.shoppingmall.user.dto;


public class UserUpdateDto {
    private final String userId;
    private final String userName;
    private final String userPassword;
    private final String userBirth;

    public UserUpdateDto(String userId, String userName, String userPassword, String userBirth) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserBirth() {
        return userBirth;
    }
}
