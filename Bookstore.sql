DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS appuser;





CREATE TABLE category
(categoryid BIGSERIAL PRIMARY KEY
, categoryname VARCHAR(20)
);

INSERT INTO category (categoryname)
VALUES ('Horror'),
        ('Fantasy'),
        ('Drama');

CREATE TABLE book
(id BIGSERIAL PRIMARY KEY
, title VARCHAR(50) NOT NULL
, author VARCHAR(50) NOT NULL
, isbn VARCHAR(50)
, publicationyear INT
, price FLOAT
, categoryid BIGINT
, FOREIGN KEY (categoryid) REFERENCES category(id)
);

INSERT INTO book (title, author, isbn, publicationyear, price, categoryid)
VALUES ('The Shining', 'Stephen King', '11111', '1975', '15', '1'),
        ('The Hobbit', 'J.R.R Tolkien', '2222', '1950', '18', '2'),
        ('Pirde and Prejudice', 'Jane Austen', '3333', '1812', '20', '3');


CREATE TABLE appuser
(id BIGSERIAL PRIMARY KEY
, username VARCHAR(20) NOT NULL
, password_hash VARCHAR(250) NOT NULL
, role VARCHAR(100) NOT NULL);

INSERT INTO appuser (username, password_hash, role)
VALUES ('user', '$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6', 'USER'),
        ('admin', '$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C', 'ADMIN');

SELECT * FROM book;
SELECT * FROM category;
SELECT * FROM application_user;