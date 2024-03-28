CREATE TABLE postLike
(
    postId BIGINT,
    userId BIGINT,
    PRIMARY KEY (postId, userId),
    FOREIGN KEY (postId) REFERENCES Post (postId),
    FOREIGN KEY (userId) REFERENCES User (userId)
);


INSERT INTO postLike (postId, userId)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (3, 4),
       (4, 5),
       (6, 1),
       (7, 2),
       (8, 3),
       (9, 4),
       (10, 5);
