
CREATE SEQUENCE user_seq;
CREATE SEQUENCE sc_seq CACHE 20 CYCLE;
CREATE SEQUENCE book_seq;
CREATE SEQUENCE comment_seq CACHE 5;
CREATE SEQUENCE review_seq;

CREATE TABLE Users
(
    id                 bigint DEFAULT NEXTVAL('user_seq'),
    first_name         VARCHAR(100),
    last_name          VARCHAR(100),
    phone_number       CHAR(11),
    email              VARCHAR(50),
    password           VARCHAR(16),
    role_id            int REFERENCES Roles(id),
    is_email_confirmed BOOLEAN
);

CREATE TABLE Roles
(
    id      int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(50)
);

CREATE TABLE SecretCodes
(
    id          bigint DEFAULT NEXTVAL('sc_seq'),
    user_id     bigint REFERENCES Users(id),
    code        CHAR(4),
    create_date TIMESTAMP,
    expire_date TIMESTAMP
);

CREATE TABLE Books
(
    id          bigint DEFAULT NEXTVAL('book_seq'),
    title       VARCHAR(255),
    isbn        CHAR(13),
    pub_year    int,
    total       int,
    available   int,
    digital_url VARCHAR(255)
);

CREATE TABLE Genres
(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    genre    VARCHAR(255)
);

CREATE TABLE Authors
(
    id          int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name  VARCHAR(100),
    last_name   VARCHAR(100),
    middle_name VARCHAR(100) -- Добавлено поле для отчества
);

CREATE TABLE Forums
(
    id          int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    creator     bigint REFERENCES Users(id),
    name        VARCHAR(100),
    create_date DATE
);

CREATE TABLE ForumComments
(
    id bigint  DEFAULT NEXTVAL('comment_seq'),
    forum_id   int REFERENCES Forums(id),
    user_id    bigint REFERENCES Users(id),
    content    VARCHAR(1500),
    send_date  TIMESTAMP
);

CREATE TABLE Ratings
(
    user_id    bigint REFERENCES Users(id),
    book_id    bigint REFERENCES Books(id),
    rate       int,
    CONSTRAINT ratings_unique UNIQUE (user_id, book_id)
);

CREATE TABLE Reviews
(
    id             bigint DEFAULT NEXTVAL('review_seq'),
    user_id        bigint REFERENCES Users(id),
    book_id        bigint REFERENCES Books(id),
    review_content VARCHAR(1000),
    send_date      TIMESTAMP
);