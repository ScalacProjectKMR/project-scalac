CREATE DATABASE shop;    

\c shop;

DROP TABLE IF EXISTS shopping_basket_product;
DROP TABLE IF EXISTS shopping_basket;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS customer;

create table product(
	product_id int primary key,
	name varchar(255) not null,
	price numeric(19,0) not null,
	count int not null
);

create table shopping_basket_product(
	shopping_basket_id int primary key,
	product_id int not null,
	constraint fk_product_id foreign key(product_id) REFERENCES product(product_id),
	count int not null
);


create table customer(
	email varchar(255) primary key,
	name varchar(255) not null,
	phone varchar(255) not null,
	address varchar(255) not null
);

create table shopping_basket(
	id int primary key,
	customer_email varchar(255),
	constraint fk_customer_email foreign key(customer_email) REFERENCES customer(email)
);