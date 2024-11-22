drop table if exists users cascade;
drop table if exists quotes cascade;
drop table if exists profiles;
drop table if exists goods;
drop table if exists categories;
drop table if exists follows;
drop type if exists role;


create type role as enum ('ADMIN','USER');

create table users (
	id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    authority role not null,
    limited_period date,
    banned boolean not null
);


create table quotes (
	id serial primary key,
	
	quotes varchar(255) not null,
	
	category integer not null,
	
	whose varchar(255) not null,
	
	whose_detail varchar(50),
	
	user_id integer REFERENCES users(id) ON DELETE CASCADE,
	
	mine boolean not null,
	
	good integer not null,
	
	created_at timestamp without time zone
	
	
);


create table profiles (
	id serial primary key,
	
	user_id integer references users(id) on delete cascade,
	
	favorite_quotes_id integer,
	
	image_file_name varchar(255),
	
	follow integer not null,
	
	follower integer not null
); 

create table goods (
	id serial primary key,
	
	user_id integer references users(id) on delete cascade,
	
	quotes_id integer not null references quotes(id) on delete cascade
);

create table categories (
	id integer primary key,
	
	category varchar(255)
);

create table follows (
	id serial primary key,
	
	user_id integer references users(id) on delete cascade,
	
	follow_id integer not null
);














