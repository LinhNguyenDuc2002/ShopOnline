CREATE DATABASE shop_online;

CREATE TABLE category(
	id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE product(
	id BIGINT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    unit VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    discount DOUBLE NOT NULL,
    available BIGINT NOT NULL,
    sold BIGINT NOT NULL,
    image LONGBLOB,
    update_day DATE NOT NULL,
    description TEXT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE user(
	id BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    sex BOOLEAN,
    avatar LONGBLOB,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    detail_address VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    join_date DATE NOT NULL,
    role VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    note TEXT
);

CREATE TABLE bill(
	id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_date DATE NOT NULL,
    delivery_address VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE detail_order(
	id BIGINT PRIMARY KEY,
    bill_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE comment(
	id BIGINT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    posting_date DATE NOT NULL,
    image LONGBLOB,
    content TEXT,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO user(id, username, password, fullname, birthday, phone, email, detail_address, city, country, role, status) 
			values(1, "linhnguyenduc", "123", "Nguyễn Đức Linh", "16/04/2002", "0395084430", "abc@gmail.com", "Đông Anh", "Hà Nội", "Việt Nam", "ADMIN", true);
