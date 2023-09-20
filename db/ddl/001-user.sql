create table "user"
(
    id          uuid        not null,
    username    varchar(63) not null,
    preferences jsonb       not null,
    primary key (id),
    unique (username)
);
