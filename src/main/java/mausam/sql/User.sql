CREATE TABLE user
(
    userId    BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    username  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE ,
    role      VARCHAR(50) DEFAULT 'user',
    password  VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



INSERT INTO user (userId, username, email, role, password, createdAt, updatedAt)
VALUES (1, 'mausam.tiwari', 'mausam@realmadrid.com', 'admin', 'mausam123', NOW(), NOW()),
       (2, 'dani.carvajal', 'dani@realmadrid.com', 'user', 'dani123', NOW(), NOW()),
       (3, 'tony.kroos', 'tony@realmadrid.com', 'user', 'tony123', NOW(), NOW()),
       (4, 'nacho.fernandez', 'nacho@realmadrid.com', 'user', 'nacho123', NOW(), NOW()),
       (5, 'david.alaba', 'david@realmadrid.com', 'user', 'david123', NOW(), NOW()),
       (6, 'jadon.sancho', 'jadon@realmadrid.com', 'user', 'jadon123', NOW(), NOW()),
       (7, 'rodrygo.goes', 'rodrygol@realmadrid.com', 'user', 'rodrygo123', NOW(), NOW()),
       (8, 'vinicius.junior', 'vinicius@realmadrid.com', 'user', 'vinicius123', NOW(), NOW()),
       (9, 'jude.bellingham', 'jude@realmadrid.com', 'user', 'jude123', NOW(), NOW()),
       (10, 'luka.modric', 'luka@realmadrid.com', 'user', 'luka123', NOW(), NOW());

