SHOW DATABASES
USE `week 0 erd`
SHOW TABLES

-- ========================================================
-- TABLES (MySQL)
-- ========================================================

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       phone VARCHAR(20),
                       created_at DATETIME(6) NOT NULL,
                       updated_at DATETIME(6) NOT NULL,
                       status ENUM('active', 'inactive', 'deleted') NOT NULL DEFAULT 'active',
                       inactive_date DATETIME,
                       point INT NOT NULL DEFAULT 0,
                       notification_enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE locations (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE stores (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255),
                        location_id BIGINT NOT NULL,
                        CONSTRAINT fk_stores_locations FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE missions (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          store_id BIGINT NOT NULL,
                          title VARCHAR(255) NOT NULL,
                          description TEXT,
                          reward_point INT NOT NULL,
                          updated_at DATETIME(6) NOT NULL,
                          created_at DATETIME(6) NOT NULL,
                          deadline DATETIME NOT NULL,
                          CONSTRAINT fk_missions_stores FOREIGN KEY (store_id) REFERENCES stores(id)
);

CREATE TABLE user_missions (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               mission_id BIGINT NOT NULL,
                               status ENUM('in_progress', 'expired', 'completed') NOT NULL,
                               received_at DATETIME(6) NOT NULL,
                               completed_at DATETIME(6),
                               CONSTRAINT fk_user_missions_users FOREIGN KEY (user_id) REFERENCES users(id),
                               CONSTRAINT fk_user_missions_missions FOREIGN KEY (mission_id) REFERENCES missions(id)
);

CREATE TABLE alarms (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        created_at DATETIME(6) NOT NULL,
                        updated_at DATETIME(6) NOT NULL,
                        title TEXT NOT NULL,
                        body TEXT,
                        is_confirmed BOOLEAN NOT NULL DEFAULT FALSE,
                        which_type ENUM('notice', 'review') NOT NULL,
                        CONSTRAINT fk_alarms_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE reviews (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         store_id BIGINT NOT NULL,
                         content TEXT NOT NULL,
                         rating INT NOT NULL,
                         created_at DATETIME(6) NOT NULL,
                         updated_at DATETIME(6) NOT NULL,
                         CONSTRAINT fk_reviews_users FOREIGN KEY (user_id) REFERENCES users(id),
                         CONSTRAINT fk_reviews_stores FOREIGN KEY (store_id) REFERENCES stores(id)
);

CREATE TABLE review_replies (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                review_id BIGINT NOT NULL,
                                user_id BIGINT NOT NULL,
                                content TEXT NOT NULL,
                                created_at DATETIME(6) NOT NULL,
                                updated_at DATETIME(6) NOT NULL,
                                CONSTRAINT fk_review_replies_reviews FOREIGN KEY (review_id) REFERENCES reviews(id),
                                CONSTRAINT fk_review_replies_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE inquiries (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           title VARCHAR(255) NOT NULL,
                           content TEXT NOT NULL,
                           created_at DATETIME(6) NOT NULL,
                           updated_at DATETIME(6) NOT NULL,
                           status ENUM('new', 'wait', 'closed') NOT NULL DEFAULT 'new',
                           CONSTRAINT fk_inquiries_users FOREIGN KEY (user_id) REFERENCES users(id)
);
