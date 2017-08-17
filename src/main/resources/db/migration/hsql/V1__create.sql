create table if not exists user (
	id bigint primary key,
	phone varchar (256) not null,
	name varchar (256),
	birth_date date,
	virtual char not null
);

create sequence seq_user start with 1 increment by 1;

create table if not exists user_like (
	user_id bigint,
	user_like varchar (2000)
);