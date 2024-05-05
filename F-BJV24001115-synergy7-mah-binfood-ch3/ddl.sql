CREATE DATABASE binar_fud;

CREATE TABLE users (
    id UUID not null PRIMARY KEY,
    username VARCHAR(255) not null,
    email_address VARCHAR(255) not null,
    password VARCHAR(255) not null
);

CREATE TABLE merchant (
    id UUID not null PRIMARY KEY,
    merchant_name VARCHAR(255) not null,
    merchant_location VARCHAR(255) not null,
    open BOOLEAN not null
);

CREATE TABLE product (
    id UUID not null PRIMARY KEY,
    product_name VARCHAR(255) not null,
    price DECIMAL not null,
    merchant_id UUID not null REFERENCES merchant(id)
);

CREATE TABLE "order" (
    id UUID not null PRIMARY KEY,
    order_time TIMESTAMP not null,
    destination_address VARCHAR(255) not null,
    user_id UUID not null REFERENCES users(id),
    completed BOOLEAN not null
);

CREATE TABLE order_detail (
    id UUID not null PRIMARY KEY,
    order_id UUID not null REFERENCES "order"(id),
    product_id UUID not null REFERENCES product(id),
    quantity INTEGER not null,
    total_price DECIMAL not null
);
