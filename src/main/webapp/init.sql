CREATE TABLE "user"
(
  id    SERIAL  NOT NULL
    CONSTRAINT user_pkey
    PRIMARY KEY,
  email VARCHAR NOT NULL,
  pswrd VARCHAR NOT NULL,
  salt  VARCHAR NOT NULL
);

CREATE UNIQUE INDEX user_id_uindex
  ON "user" (id);

CREATE UNIQUE INDEX user_email_uindex
  ON "user" (email);