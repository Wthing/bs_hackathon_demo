
CREATE SEQUENCE user_seq;
CREATE SEQUENCE sc_seq CACHE 20 CYCLE;
CREATE SEQUENCE book_seq;
CREATE SEQUENCE comment_seq CACHE 5;
CREATE SEQUENCE review_seq;

CREATE TABLE Users
(
    user_id            bigint DEFAULT NEXTVAL('user_seq'),
    first_name         VARCHAR(100),
    last_name          VARCHAR(100),
    phone_number       VARCHAR(11),
    email              VARCHAR(50),
    password           VARCHAR(16),
    is_email_confirmed BOOLEAN
);

CREATE TABLE Roles
(
    authority_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    authority    VARCHAR(50)
);

CREATE TABLE user_role
(
    user_id   bigint REFERENCES Users(user_id),
    role_id   int REFERENCES Roles(role_id)
    CONSTRAINT user_role_const UNIQUE (user_id, role_id)
);

CREATE TABLE SecretCodes
(
    code_id     bigint DEFAULT NEXTVAL('sc_seq'),
    user_id     bigint REFERENCES Users (user_id),
    code        CHAR(4),
    create_date TIMESTAMP,
    expire_date TIMESTAMP
);

CREATE TABLE Books
(
    book_id     bigint DEFAULT NEXTVAL('book_seq'),
    title       VARCHAR(255),
    isbn        VARCHAR(10),
    pub_year    int,
    total       int,
    available   int,
    digital_url VARCHAR(255)
);

CREATE TABLE Genres
(
    genre_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    genre    VARCHAR(255)
);

CREATE TABLE Authors
(
    author_id         int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    author_first_name VARCHAR(100),
    author_last_name  VARCHAR(100),
    author_middle_name VARCHAR(100) -- Добавлено поле для отчества
);

CREATE TABLE Forums
(
    forum_id    int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id     bigint REFERENCES Users (user_id),
    name        VARCHAR(100),
    create_date DATE
);

CREATE TABLE ForumComments
(
    comment_id bigint DEFAULT NEXTVAL('comment_seq'),
    forum_id   int REFERENCES Forums (forum_id),
    user_id    bigint REFERENCES Users (user_id),
    content    VARCHAR(1500),
    send_date  TIMESTAMP
);

CREATE TABLE Ratings
(
    user_id   bigint REFERENCES Users (user_id),
    book_id   bigint REFERENCES Books (book_id),
    rate      int,
    CONSTRAINT ratings_unique UNIQUE (user_id, book_id)
);

CREATE TABLE Reviews
(
    review_id      bigint DEFAULT NEXTVAL('review_seq'),
    user_id        bigint REFERENCES Users (user_id),
    book_id        bigint REFERENCES Books (book_id),
    review_content VARCHAR(1000),
    send_date      TIMESTAMP
);