create table if not exists bp_user (
	id bigint primary key,
	phone varchar (256) not null,
	name varchar (256),
	birth_date date,
	virtual char not null
);

create sequence seq_user start with 1 increment by 1;

create table if not exists bp_user_like (
	user_id bigint,
	user_like varchar (2000)
);

create table if not exists bp_user_dislike (
	user_id bigint,
	user_dislike varchar (2000)
);

create table if not exists bp_follower (
	follower_id bigint,
	followed_id bigint
);

create table if not exists bp_plan (
	id bigint primary key,
	birthday_guy_id bigint,
	gift_list_validated char not null,
	event_completed char not null
);

create sequence seq_plan start with 1 increment by 1;

create table if not exists bp_participation (
	id bigint primary key,
	participant_id bigint,
	plan_id bigint,
	average_price_proposed float,
	is_author char not null,
	approved char not null
);

create sequence seq_participation start with 1 increment by 1;

create table if not exists bp_gift (
	id bigint primary key,
	plan_id bigint,
	name varchar (256),
	detail varchar (2000),
	buyer_id bigint,
	author_id bigint,
	collected char not null
);

create sequence seq_gift start with 1 increment by 1;

create table if not exists bp_gift_like (
	gift_id bigint,
	user_id bigint
);

create table if not exists bp_gift_dislike (
	gift_id bigint,
	user_id bigint
);

create table if not exists bp_gift_comment (
	id bigint primary key,
	gift_id bigint,
	author_id bigint,
	comment varchar (2000)
);

create sequence seq_gift_comment start with 1 increment by 1;