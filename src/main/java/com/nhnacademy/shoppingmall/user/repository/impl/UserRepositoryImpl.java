package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.util.DbUtils;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.dto.UserUpdateDto;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;
import static jakarta.servlet.SessionTrackingMode.URL;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        // SQL Injection 방지 및 쿼리 실행
        String sql = "SELECT * FROM users WHERE user_id = ? AND user_password = ?";
        log.debug("sql: {}", sql);

        Connection connection = DbConnectionThreadLocal.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            // 파라미터 설정
            statement.setString(1, userId);
            statement.setString(2, userPassword);

            try (ResultSet rs = statement.executeQuery()) { // 쿼리 실행
                if (rs.next()) {
                    User user = new User(
                            rs.getString("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_birth"),
                            User.Auth.valueOf(rs.getString("user_auth")),
                            rs.getInt("user_point"),
                            Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user from database",e);
        }
        return Optional.empty();
    }


    @Override
    public Optional<User> findById(String userId) {
        // TODO #3-2 회원 조회
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getString("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_birth"),
                            User.Auth.valueOf(rs.getString("user_auth")),
                            rs.getInt("user_point"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime(): null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }



    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();

        String sql = "INSERT INTO users (user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserPassword());
            statement.setString(4, user.getUserBirth());
            statement.setString(5, user.getUserAuth().name());
            statement.setInt(6, user.getUserPoint());
            statement.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            statement.setTimestamp(8, user.getLatestLoginAt() != null ? Timestamp.valueOf(user.getLatestLoginAt()) : null);

            return statement.executeUpdate(); // 성공적으로 저장된 행 수를 반환
        }catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("java.sql.SQLIntegrityConstraintViolationException", e);
        }catch (SQLException e) {
            throw new RuntimeException("Error saving user to the database", e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);

            // 쿼리 실행하고, 삭제된 행의 수를 반환
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user with user_id: " + userId, e);

        }
    }

    @Override
    public int update(UserUpdateDto userUpdateDto) {
        // 회원 수정
        Connection connection = DbConnectionThreadLocal.getConnection();

        String sql = "UPDATE users SET user_name = ?, user_birth = ?, user_password = ? WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userUpdateDto.getUserName());
            statement.setString(2, userUpdateDto.getUserBirth());
            statement.setString(3, userUpdateDto.getUserPassword());
            statement.setString(4, userUpdateDto.getUserId());

            // 쿼리 실행하고, 수정된 행의 수를 반환
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();

        String sql = "UPDATE users SET latest_login_at = ? WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, latestLoginAt.toString());
            statement.setString(2, userId);
            // 실행된 행의 수를 반환
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM users WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1); // COUNT 결과 반환
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return 0;
    }
//    public List<User> findAll() {
//        List<User> users = new ArrayList<>();
//
//        // JDBC 연결을 위한 객체
//        try (
//             PreparedStatement statement = connection.prepareStatement("SELECT id, name, email, role FROM users");
//             ResultSet resultSet = statement.executeQuery()) {
//
//            // 결과를 반복하면서 사용자 목록에 추가
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//                String email = resultSet.getString("email");
//                String role = resultSet.getString("role");
//                users.add(new User(id, name, email, role));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // 오류 처리
//        }
//
//        return users; // 사용자 목록 반환
//    }

}
