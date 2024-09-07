
CREATE SEQUENCE user_seq;
CREATE SEQUENCE sc_seq CACHE 20 CYCLE;
CREATE SEQUENCE book_seq;
CREATE SEQUENCE comment_seq CACHE 5;
CREATE SEQUENCE review_seq;
CREATE SEQUENCE rate_get CACHE 5 CYCLE;

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

CREATE TABLE GenresAndAuthorsToBooks
(
    gen_and_auth_to_book_id SERIAL PRIMARY KEY,
    book_id                 int REFERENCES Books (id),
    user_id                 int REFERENCES Users (id),
    author_id               int REFERENCES Authors (id)
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
    id         bigint DEFAULT NEXTVAL('rate_get'),
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

CREATE TABLE Works
(
    id          SERIAL PRIMARY KEY,
    user_id     INT REFERENCES Users (id),
    title       VARCHAR(255),
    description TEXT,
    file_url    VARCHAR(255),
    create_date TIMESTAMP
);

CREATE TABLE WorkComments
(
    id SERIAL PRIMARY KEY,
    work_id         INT REFERENCES Works (id),
    user_id         INT REFERENCES Users (id),
    content         VARCHAR(500),
    send_date       TIMESTAMP
);

CREATE TABLE Games
(
    id   SERIAL PRIMARY KEY,
    game_name VARCHAR(255)
);


CREATE TABLE Game_attributes
(
    id             SERIAL PRIMARY KEY,
    attribute_name VARCHAR(100)
);



CREATE TABLE Game_attribute_values
(
    id                SERIAL PRIMARY KEY,
    game_id           INT REFERENCES Games (id),
    game_attribute_id INT REFERENCES Game_attributes (id),
    value             VARCHAR(255)
);

CREATE TABLE PlayerToGames
(
    player_id SERIAL PRIMARY KEY,
    user_id   INT REFERENCES Users (id),
    game_id   INT REFERENCES Games (id),
    score     int
);