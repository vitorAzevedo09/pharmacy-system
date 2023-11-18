CREATE TABLE roles (
  id serial PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE privileges (
  id serial PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE roles_privileges (
  role_id INTEGER NOT NULL REFERENCES roles(id),
  privilege_id INTEGER NOT NULL REFERENCES privileges(id),
  PRIMARY KEY (role_id, privilege_id)
);

CREATE TABLE users_roles (
  user_id BIGINT NOT NULL REFERENCES users(id),
  role_id BIGINT NOT NULL REFERENCES roles(id),
  PRIMARY KEY (user_id, role_id)
);
