CREATE TABLE `users` (
                         `user_id` varchar(50) NOT NULL COMMENT '아이디',
                         `user_name` varchar(50) NOT NULL COMMENT '이름',
                         `user_password` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                         `user_birth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
                         `user_auth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                         `user_point` int NOT NULL COMMENT 'default : 1000000',
                         `created_at` datetime NOT NULL COMMENT '가입 일자',
                         `latest_login_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';

CREATE TABLE orders (
                        order_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 ID',
                        user_id VARCHAR(50) NOT NULL COMMENT '회원 ID',
                        order_date DATETIME NOT NULL COMMENT '주문 일자',
                        total_amount INT NOT NULL COMMENT '총 결제 금액',
                        status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '주문 상태',
                        point_earned INT NOT NULL COMMENT '적립 포인트',
                        FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 명세';

CREATE TABLE order_items (
                             order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 상품 항목 ID',
                             order_id BIGINT NOT NULL COMMENT '주문 ID',
                             product_id BIGINT NOT NULL COMMENT '상품 ID',
                             quantity INT NOT NULL COMMENT '주문 수량',
                             price_per_unit INT NOT NULL COMMENT '상품 단가',
                             total_price INT GENERATED ALWAYS AS (quantity * price_per_unit) COMMENT '총 가격',
                             FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
                             FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 상품 관계';

CREATE TABLE points_history (
                                history_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '기록 ID',
                                user_id VARCHAR(50) NOT NULL COMMENT '회원 ID',
                                change_date DATETIME NOT NULL COMMENT '변동 일자',
                                points_changed INT NOT NULL COMMENT '변동된 포인트',
                                type ENUM('EARNED', 'USED') NOT NULL COMMENT '적립 혹은 사용 구분',
                                description VARCHAR(255) COMMENT '설명',
                                FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='포인트 사용 내역';

CREATE TABLE user_addresses (
                                address_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주소 ID',
                                user_id VARCHAR(50) NOT NULL COMMENT '회원 ID',
                                address_line VARCHAR(255) NOT NULL COMMENT '주소',
                                city VARCHAR(50) NOT NULL COMMENT '도시',
                                state VARCHAR(50) NOT NULL COMMENT '시/도',
                                zip_code VARCHAR(10) NOT NULL COMMENT '우편번호',
                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '주소 등록일',
                                updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '주소 수정일',
                                FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원 주소';

CREATE TABLE categories (
                            category_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리 ID',
                            category_name VARCHAR(100) NOT NULL COMMENT '카테고리 이름',
                            sort_order INT NOT NULL COMMENT '정렬 순서'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품 카테고리';

CREATE TABLE products (
                          product_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 ID',
                          product_name VARCHAR(100) NOT NULL COMMENT '상품 이름',
                          thumbnail_image VARCHAR(255) DEFAULT 'no-image.png' COMMENT '썸네일 이미지',
                          detail_image VARCHAR(255) COMMENT '상세 이미지',
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자',
                          updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일자'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';

CREATE TABLE product_categories (
                                    product_id BIGINT NOT NULL COMMENT '상품 ID',
                                    category_id BIGINT NOT NULL COMMENT '카테고리 ID',
                                    PRIMARY KEY (product_id, category_id),
                                    FOREIGN KEY (product_id) REFERENCES products(product_id),
                                    FOREIGN KEY (category_id) REFERENCES categories(category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품-카테고리 관계';
