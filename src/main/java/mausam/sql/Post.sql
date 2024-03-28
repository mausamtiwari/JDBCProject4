CREATE TABLE post
(
    postId    BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    userId    BIGINT       NOT NULL,
    title     VARCHAR(255) NOT NULL,
    slug      VARCHAR(255) NOT NULL ,
    views     BIGINT       DEFAULT 0,
    image     VARCHAR(255) DEFAULT NULL,
    body      TEXT,
    published BIGINT       DEFAULT 0,
    createdAt TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES User (userId)
);


INSERT INTO post (postId, userId, title, slug, views, image, body, published, createdAt, updatedAt)
VALUES (1, 1, 'My post', 'my-post', 10000, 'image1.jpg', 'Hi!.', 1, NOW(), NOW()),
       (2, 2, 'His post', 'his-post', 15000, 'image2.jpg', 'Feeling loved.', 1, NOW(), NOW()),
       (3, 3, 'her post', 'her-post', 20000, 'image3.jpg', 'Feeling weird.', 1, NOW(), NOW()),
       (4, 4, 'Our post', 'our-post', 34000, 'image4.jpg', 'Hello!', 1, NOW(), NOW()),
       (5, 5, 'Their post', 'their-post', 31000, 'image5.jpg', 'All good.', 1, NOW(), NOW()),
       (6, 6, 'First post', 'first-post', 5000, 'image6.jpg', 'Feeling happy.', 1, NOW(), NOW()),
       (7, 7, 'Second post', 'second-post', 10000, 'image7.jpg', 'Feeling great.', 1, NOW(), NOW()),
       (8, 8, 'Third post', 'third-post', 20000, 'image8.jpg', 'Love you all.', 1, NOW(), NOW()),
       (9, 9, 'Fourth post', 'fourth-post', 25000, 'image9.jpg', 'I am good.', 1, NOW(), NOW()),
       (10, 10, 'Fifth post', 'fifth-post', 400000, 'image10.jpg', 'Tchau!', 1, NOW(), NOW());

