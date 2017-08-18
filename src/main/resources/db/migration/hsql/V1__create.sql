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

create table if not exists planner (
	id bigint primary key,
	birthday_guy_id bigint,
	author_id bigint
);

create sequence seq_planner start with 1 increment by 1;

create table if not exists participation (
	id bigint primary key,
	participant_id bigint,
	planner_id bigint,
	average_price_proposed float,
	is_author char not null,
	approved char not null
);

create sequence seq_participation start with 1 increment by 1;