create table bp_user (
	id bigint identity(1,1) not null,
	phone nvarchar (256) not null,
	name nvarchar (256),
	birth_date date,
	virtual char not null,
    constraint pk_user primary key clustered (id)
);

create table bp_user_like (
	user_id bigint,
	user_like nvarchar (2000)
);

create table bp_user_dislike (
	user_id bigint,
	user_dislike nvarchar (2000)
);

create table bp_follower (
	follower_id bigint,
	followed_id bigint
);

create table bp_plan (
	id bigint identity(1,1) not null,
	birthday_guy_id bigint,
	gift_list_validated char not null,
	event_completed char not null,
    constraint pk_plan primary key clustered (id)
);

create table bp_participation (
	id bigint identity(1,1) not null,
	participant_id bigint,
	plan_id bigint,
	average_price_proposed float,
	is_author char not null,
	approved char not null,
    constraint pk_participation primary key clustered (id)
);

create table bp_gift (
	id bigint identity(1,1) not null,
	plan_id bigint,
	name nvarchar (256),
	detail nvarchar (2000),
	buyer_id bigint,
	author_id bigint,
	collected char not null,
    constraint pk_gift primary key clustered (id)
);

create table bp_gift_like (
	gift_id bigint,
	user_id bigint
);

create table bp_gift_dislike (
	gift_id bigint,
	user_id bigint
);

create table bp_gift_comment (
	id bigint identity(1,1) not null,
	gift_id bigint,
	author_id bigint,
	comment nvarchar (2000),
    constraint pk_gift_comment primary key clustered (id)
);