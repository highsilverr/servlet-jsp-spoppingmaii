package com.nhnacademy.shoppingmall.user.service;

import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    User getUser(String userId);

    void saveUser(User user);

    void updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(String userId);

    User doLogin(String userId, String userPassword);

    List<User> getAllUsers();
}
