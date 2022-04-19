alter table users
    add password varchar(255) not null default 'admin';

create table examples
(
    id varchar(255) not null primary key,
    task_id varchar(255) constraint fki85gogg74g8m4t5ihuvslrxg9 references tasks
);

alter table examples
    owner to qwerty;

