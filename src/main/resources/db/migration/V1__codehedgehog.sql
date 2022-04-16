create table roles
(
    id varchar(255) not null primary key,
    name varchar(255) not null constraint uk_ofx66keruapi6vyqpv6f2or37 unique
);

alter table roles
    owner to qwerty;

create table topics
(
    id varchar(255) not null primary key,
    name varchar(255) not null,
    parent_topic varchar(255) constraint fk1srnnv8dbwe9qk30qobl76h8 references topics
);

alter table topics
    owner to qwerty;

create table tasks
(
    id varchar(255) not null primary key,
    description varchar(255),
    is_draft boolean,
    name varchar(255) not null,
    price integer,
    topic_id varchar(255) constraint fkr6ipq9i6dlewc491k7u6nvt0a references topics
);

alter table tasks
    owner to qwerty;

create table users
(
    id varchar(255) not null primary key,
    name varchar(255),
    surname varchar(255),
    username varchar(255) not null constraint uk_r43af9ap4edm43mmtq01oddj6 unique,
    role_id  varchar(255) constraint fkp56c1712k691lhsyewcssf40f references roles
);

alter table users
    owner to qwerty;

create table solutions
(
    id varchar(255) not null primary key,
    programming_language varchar(255) not null,
    source_code varchar(255) not null,
    verdict varchar(255) not null,
    author_id varchar(255) constraint fk399q475dqkl4p5cmqkvvfm6lb references users,
    task_id varchar(255) constraint fkbqabgk1lnu9xg0rbmno60q2tw references tasks
);

alter table solutions
    owner to qwerty;

