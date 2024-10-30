package com.nhnacademy.shoppingmall.common.mvc.transaction;

import com.nhnacademy.shoppingmall.common.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class DbConnectionThreadLocal {
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> sqlErrorThreadLocal = ThreadLocal.withInitial(()->false);

    public static void initialize(){

        //todo#2-1 - connection pool에서 connectionThreadLocal에 connection을 할당합니다.


        try {
            Connection connection = DbUtils.getDataSource().getConnection();
            connectionThreadLocal.set(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //todo#2-2 connectiond의 Isolation level을 READ_COMMITED를 설정 합니다.
        try {
            connectionThreadLocal.get().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //todo#2-3 auto commit 을 false로 설정합니다.
        try {
            connectionThreadLocal.get().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection(){
        return connectionThreadLocal.get();
    }

    public static void setSqlError(boolean sqlError){
        sqlErrorThreadLocal.set(sqlError);
    }

    public static boolean getSqlError(){
        return sqlErrorThreadLocal.get();
    }

    public static void reset() {
        // 현재 스레드에서 사용 중인 Connection을 가져옵니다.
        Connection connection = connectionThreadLocal.get();

        // Connection이 null인지 체크합니다.
        if (connection == null) {
            throw new RuntimeException();
        }
        try {
            if (getSqlError()) {
                connection.rollback(); // Rollback
            } else {
                connection.commit(); // Commit
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            // todo#2-4 사용이 완료된 connection은 close를 호출하여 connection pool에 반환합니다.
            try {
                connection.close(); // 커넥션 닫기
            } catch (SQLException e) {
                throw new RuntimeException();
            }

            // todo#2-7 현재 사용하고 있는 connection을 재사용할 수 없도록 connectionThreadLocal을 초기화 합니다.
            connectionThreadLocal.remove();
            //sqlErrorThreadLocal.remove();
            sqlErrorThreadLocal.set(false);
        }
    }

}
