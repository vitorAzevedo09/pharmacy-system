CREATE TABLE roles (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE privilege (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE roles_privileges (
  role_id BIGINT NOT NULL REFERENCES roles(id),
  privilege_id BIGINT NOT NULL REFERENCES privilege(id),
  PRIMARY KEY (role_id, privilege_id)
);

CREATE TABLE users_roles (
  user_id BIGINT NOT NULL REFERENCES users(id),
  role_id BIGINT NOT NULL REFERENCES roles(id),
  PRIMARY KEY (user_id, role_id)
);
