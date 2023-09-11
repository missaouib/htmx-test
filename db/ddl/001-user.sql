create table "user"
(
    id          uuid  not null,
    preferences jsonb not null,
    primary key (id)
);
