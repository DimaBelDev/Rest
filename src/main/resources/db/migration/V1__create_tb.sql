create table users(
    id SERIAL primary key,
    firstname VARCHAR(32) NOT NULL,
    lastname VARCHAR(32) NOT NULL,
    data VARCHAR(32) NOT NULL,
    party INTEGER NOT NULL
);

INSERT INTO users VALUES(1,'Ivan', 'Ivanov', '14.01.1987', 3)