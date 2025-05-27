-- V1__Create_users_table.sql
CREATE SEQUENCE IF NOT EXISTS test_id_seq START 1 INCREMENT 1;

CREATE TABLE users (
    id BIGINT NOT NULL DEFAULT nextval('test_id_seq'),
    create_date TIMESTAMP,
    update_date TIMESTAMP,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    PRIMARY KEY (id)
);