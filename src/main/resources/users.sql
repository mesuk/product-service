create table user
(
	id int auto_increment,
	full_name varchar(200) not null,
	email varchar(100) not null,
	mobile_number varchar(20) not null,
	gender varchar(10) not null,
	user_name varchar(30) not null,
	constraint user_pk
		primary key (id)
);