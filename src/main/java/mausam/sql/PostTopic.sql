CREATE TABLE postTopic
(
    postId  BIGINT,
    topicId BIGINT,
    PRIMARY KEY (postId, topicId),
    FOREIGN KEY (postId) REFERENCES Post (postId),
    FOREIGN KEY (topicId) REFERENCES Topic (topicId)
);


INSERT INTO postTopic (postId, topicId)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);
