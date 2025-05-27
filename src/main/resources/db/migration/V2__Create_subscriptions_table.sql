CREATE TABLE subscriptions (
    id BIGINT NOT NULL DEFAULT nextval('test_id_seq'),
    create_date TIMESTAMP,
    update_date TIMESTAMP,

    user_id BIGINT NOT NULL REFERENCES users(id),
    subscription_type VARCHAR NOT NULL,
    start_date DATE,
    renew_date DATE,
    expiration_date DATE,

    PRIMARY KEY (id)
);