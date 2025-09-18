CREATE TABLE notifications (
    notification_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    role VARCHAR(64),
    title VARCHAR(255) NOT NULL,
    message VARCHAR(1024) NOT NULL,
    data_json TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);