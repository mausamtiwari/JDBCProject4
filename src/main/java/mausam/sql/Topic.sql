CREATE TABLE topic
(
    topicId BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    name    VARCHAR(255) NOT NULL,
    slug    VARCHAR(255) NOT NULL
);


INSERT INTO topic (topicId, name, slug)
VALUES (1, 'Technology', 'technology'),
       (2, 'Science', 'science'),
       (3, 'Travel', 'travel'),
       (4, 'Food', 'food'),
       (5, 'Sports', 'sports'),
       (6, 'Health', 'health'),
       (7, 'Fashion', 'fashion'),
       (8, 'Art', 'art'),
       (9, 'Music', 'music'),
       (10, 'Books', 'books');
