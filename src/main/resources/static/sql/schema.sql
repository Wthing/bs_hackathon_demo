CREATE SEQUENCE user_seq START 1 INCREMENT 1;
CREATE SEQUENCE sc_seq START 1 INCREMENT 1 CACHE 20 CYCLE;
CREATE SEQUENCE book_seq START 1 INCREMENT 1;
CREATE SEQUENCE comment_seq START 1 INCREMENT 1 CACHE 5;
CREATE SEQUENCE review_seq START 1 INCREMENT 1;
CREATE SEQUENCE rate_seq START 1 INCREMENT 1 CACHE 5 CYCLE;
CREATE SEQUENCE role_seq START 1 INCREMENT 1;
CREATE SEQUENCE genre_seq START 1 INCREMENT 1;
CREATE SEQUENCE author_seq START 1 INCREMENT 1;
CREATE SEQUENCE gen_and_auth_to_book_seq START 1 INCREMENT 1;
CREATE SEQUENCE forum_seq START 1 INCREMENT 1;
CREATE SEQUENCE work_seq START 1 INCREMENT 1;
CREATE SEQUENCE work_comment_seq START 1 INCREMENT 1;
CREATE SEQUENCE game_seq START 1 INCREMENT 1;
CREATE SEQUENCE game_attribute_seq START 1 INCREMENT 1;
CREATE SEQUENCE game_attribute_value_seq START 1 INCREMENT 1;
CREATE SEQUENCE player_to_game_seq START 1 INCREMENT 1;

-- Таблица пользователей
CREATE TABLE Users
(
    id                 BIGINT DEFAULT NEXTVAL('user_seq') PRIMARY KEY,
    first_name         VARCHAR(100),
    last_name          VARCHAR(100),
    phone_number       CHAR(11),
    email              VARCHAR(50),
    password           VARCHAR(16),
    role_id            BIGINT REFERENCES Roles(id),
    is_email_confirmed BOOLEAN
);

-- Таблица ролей
CREATE TABLE Roles
(
    id      BIGINT DEFAULT NEXTVAL('role_seq') PRIMARY KEY,
    name    VARCHAR(50)
);

-- Секретные коды для подтверждения
CREATE TABLE SecretCodes
(
    id          BIGINT DEFAULT NEXTVAL('sc_seq') PRIMARY KEY,
    user_id     BIGINT REFERENCES Users(id),
    code        CHAR(4),
    create_date TIMESTAMP,
    expire_date TIMESTAMP
);

-- Таблица книг
CREATE TABLE Books
(
    id          BIGINT DEFAULT NEXTVAL('book_seq') PRIMARY KEY,
    title       VARCHAR(255),
    isbn        CHAR(13),
    pub_year    INT,
    total       INT,
    available   INT,
    digital_url VARCHAR(255)
);

-- Жанры
CREATE TABLE Genres
(
    id     BIGINT DEFAULT NEXTVAL('genre_seq') PRIMARY KEY,
    genre  VARCHAR(255)
);

-- Авторы
CREATE TABLE Authors
(
    id          BIGINT DEFAULT NEXTVAL('author_seq') PRIMARY KEY,
    first_name  VARCHAR(100),
    last_name   VARCHAR(100),
    middle_name VARCHAR(100)
);

-- Связь авторов и жанров с книгами
CREATE TABLE GenresAndAuthorsToBooks
(
    id          BIGINT DEFAULT NEXTVAL('gen_and_auth_to_book_seq') PRIMARY KEY,
    book_id     BIGINT REFERENCES Books(id),
    author_id   BIGINT REFERENCES Authors(id)
);

-- Форумы
CREATE TABLE Forums
(
    id          BIGINT DEFAULT NEXTVAL('forum_seq') PRIMARY KEY,
    creator     BIGINT REFERENCES Users(id),
    name        VARCHAR(100),
    create_date DATE
);

-- Комментарии на форумах
CREATE TABLE ForumComments
(
    id          BIGINT DEFAULT NEXTVAL('comment_seq') PRIMARY KEY,
    forum_id    BIGINT REFERENCES Forums(id),
    user_id     BIGINT REFERENCES Users(id),
    content     VARCHAR(1500),
    send_date   TIMESTAMP
);

-- Рейтинги книг
CREATE TABLE Ratings
(
    id         BIGINT DEFAULT NEXTVAL('rate_seq') PRIMARY KEY,
    user_id    BIGINT REFERENCES Users(id),
    book_id    BIGINT REFERENCES Books(id),
    rate       INT,
    CONSTRAINT ratings_unique UNIQUE (user_id, book_id)
);

-- Обзоры книг
CREATE TABLE Reviews
(
    id             BIGINT DEFAULT NEXTVAL('review_seq') PRIMARY KEY,
    user_id        BIGINT REFERENCES Users(id),
    book_id        BIGINT REFERENCES Books(id),
    review_content VARCHAR(1000),
    send_date      TIMESTAMP
);

-- Работы пользователей (например, рефераты, эссе)
CREATE TABLE Works
(
    id          BIGINT DEFAULT NEXTVAL('work_seq') PRIMARY KEY,
    user_id     BIGINT REFERENCES Users(id),
    title       VARCHAR(255),
    description TEXT,
    file_url    VARCHAR(255),
    create_date TIMESTAMP
);

-- Комментарии к работам
CREATE TABLE WorkComments
(
    id          BIGINT DEFAULT NEXTVAL('work_comment_seq') PRIMARY KEY,
    work_id     BIGINT REFERENCES Works(id),
    user_id     BIGINT REFERENCES Users(id),
    content     VARCHAR(500),
    send_date   TIMESTAMP
);

-- Игры
CREATE TABLE Games
(
    id          BIGINT DEFAULT NEXTVAL('game_seq') PRIMARY KEY,
    game_name   VARCHAR(255)
);

-- Атрибуты игр (например, сложность, тема и т.д.)
CREATE TABLE GameAttributes
(
    id             BIGINT DEFAULT NEXTVAL('game_attribute_seq') PRIMARY KEY,
    attribute_name VARCHAR(100)
);

-- Значения атрибутов игр
CREATE TABLE GameAttributeValues
(
    id                BIGINT DEFAULT NEXTVAL('game_attribute_value_seq') PRIMARY KEY,
    game_id           BIGINT REFERENCES Games(id),
    game_attribute_id BIGINT REFERENCES GameAttributes(id),
    value             VARCHAR(255)
);

-- Связь игроков с играми (и результаты)
CREATE TABLE PlayerToGames
(
    id        BIGINT DEFAULT NEXTVAL('player_to_game_seq') PRIMARY KEY,
    user_id   BIGINT REFERENCES Users(id),
    game_id   BIGINT REFERENCES Games(id),
    score     INT
);
