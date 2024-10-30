package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.dto.UserUpdateDto;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId){
        //todo#4-1 회원조회
        // userId를 사용하여 사용자 조회
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        Optional<User> userOptional = userRepository.findById(userId);

        // 사용자가 존재하지 않는 경우 null을 반환
        if (userOptional.isPresent()) {
            return userOptional.get(); // 사용자 정보를 반환
        } else {
            return null;
        }

    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록

        if (user == null) {
            throw new UserAlreadyExistsException("User cannot be null");
        }

        // userId로 사용자 수 확인
        int userCount = userRepository.countByUserId(user.getUserId());
        if (userCount > 0) {
            throw new UserAlreadyExistsException("User already exists with id: " + user.getUserId());
        }

        // 사용자 정보 저장
        userRepository.save(user);

    }

    @Override
    public void updateUser(UserUpdateDto userUpdateDto) {
        //todo#4-3 회원수정

        // 사용자 ID로 존재 여부 확인
        int userCount = userRepository.countByUserId(userUpdateDto.getUserId());
        if (userCount == 0) {
            throw new IllegalArgumentException("User not found with id: " + userUpdateDto.getUserId());
        }

        userRepository.update(userUpdateDto);

    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제

        // 입력값 유효성 검사
        if(userId == null || userId.isEmpty()){
            throw new IllegalArgumentException("userID Null");
        }
        // 사용자 ID로 존재 여부 확인
        int userCount = userRepository.countByUserId(userId);
        if (userCount == 0) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        userRepository.deleteByUserId(userId);

    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회

        // 입력값 유효성 검사
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        if (userPassword == null || userPassword.isEmpty()) {
            throw new IllegalArgumentException("User password cannot be null or empty");
        }

        // userId로 사용자 조회
        Optional<User> userOptional = userRepository.findByUserIdAndUserPassword(userId, userPassword);

        // 사용자가 존재하지 않는 경우 예외 발생
        User user = userOptional.orElseThrow(() ->
                new UserNotFoundException("User not found with id: " + userId)
        );

        // 비밀번호 확인
        if (!user.getUserPassword().equals(userPassword)) {
            throw new UserNotFoundException("Invalid password for user: " + userId);
        }

        // 로그인 성공 시 최신 로그인 시간 업데이트
        userRepository.updateLatestLoginAtByUserId(user.getUserId(), LocalDateTime.now());

        // 로그인 성공 시 사용자 정보 반환
        return user;
    }

}
