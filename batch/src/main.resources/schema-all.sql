DROP TABLE book IF EXISTS;
DROP TABLE client IF EXISTS;
DROP TABLE admins IF EXISTS;
DROP TABLE library IF EXISTS;
DROP TABLE loan IF EXISTS;

CREATE TABLE loan  (
  id BIGINT NOT NULL PRIMARY KEY,
  state VARCHAR(40),
  start_date DATE ,
  end_date DATE,
  updated_date DATE,
  renewed BOOLEAN,
  CONSTRAINT FKEY FOREIGN KEY (books) REFERENCES [Book] (id),
  CONSTRAINT FKEY FOREIGN KEY (client) REFERENCES [Client] (id)
);

CREATE TABLE library  (
  id BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(40),
  phoneNumber VARCHAR(40),
  adress VARCHAR(200)
);

CREATE TABLE client  (
  id BIGINT NOT NULL PRIMARY KEY,
  firstname VARCHAR(40),
  lastname VARCHAR(40),
  password VARCHAR(200),
  email VARCHAR(100),
  created_at Date,
  updated_at DATE
);

CREATE TABLE book  (
  id BIGINT NOT NULL PRIMARY KEY,
  title VARCHAR(40),
  author VARCHAR(40),
  language VARCHAR(20),
  state VARCHAR(20),
  loan_start_date Date,
  loan_end_date DATE,
  CONSTRAINT FKEY FOREIGN KEY (id) REFERENCES [Library] (id),
  CONSTRAINT FKEY FOREIGN KEY (id) REFERENCES [Library] (id)
);

CREATE TABLE admins  (
  id BIGINT NOT NULL PRIMARY KEY
  CONSTRAINT FKEY FOREIGN KEY (library) REFERENCES [Library] (id),
);
