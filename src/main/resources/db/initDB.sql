DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS lunch;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE lunch (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  price       DECIMAL                   NOT NULL,
  date        DATE DEFAULT CURRENT_DATE NOT NULL,
  description TEXT                      NOT NULL
);

CREATE TABLE votes (
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date_time   TIMESTAMP DEFAULT now() NOT NULL,
    user_id     INTEGER                 NOT NULL,
    lunch_id    INTEGER                 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (lunch_id) REFERENCES lunch (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_user_lunch_idx
    ON votes (user_id, lunch_id);