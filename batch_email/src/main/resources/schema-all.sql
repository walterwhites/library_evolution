CREATE TABLE IF NOT EXISTS clients (
  id BIGINT NOT NULL PRIMARY KEY,
  firstname VARCHAR(40),
  lastname VARCHAR(40),
  PASSWORD VARCHAR(200),
  email VARCHAR(100),
  created_at Date,
  updated_at DATE
);

CREATE TABLE IF NOT EXISTS library (
  id BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(40),
  phoneNumber VARCHAR(40),
  adress VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS book (
  id BIGINT NOT NULL PRIMARY KEY,
  title VARCHAR(40),
  author VARCHAR(40),
  LANGUAGE VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS loan (
  id BIGINT NOT NULL PRIMARY KEY,
  state VARCHAR(40),
  start_date DATE,
  end_date DATE,
  updated_date DATE,
  renewed BOOLEAN
);

ALTER TABLE loan ADD CONSTRAINT fk_loan_book FOREIGN KEY (id) REFERENCES book (id);
ALTER TABLE loan ADD CONSTRAINT fk_loan_clients FOREIGN KEY (id) REFERENCES clients (id);

CREATE TABLE IF NOT EXISTS admins (
  id BIGINT NOT NULL PRIMARY KEY
);
ALTER TABLE admins ADD CONSTRAINT fk_admins_library FOREIGN KEY (id) REFERENCES library (id);