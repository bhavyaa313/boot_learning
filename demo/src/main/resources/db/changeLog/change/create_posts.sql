CREATE TABLE Posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(50) NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME
);
