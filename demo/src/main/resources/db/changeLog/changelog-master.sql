--liquibase formatted sql

--changeset Bhavyaa:115
CREATE TABLE demo_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

--changeset Bhavyaa:116
INSERT INTO demo_table (name, description) VALUES ('bavya', 'This is the first sample description.');
INSERT INTO demo_table (name, description) VALUES ('Sample Name 2', 'This is the second sample description.');


