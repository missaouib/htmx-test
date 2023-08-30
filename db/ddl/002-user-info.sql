create table user_info
(
    id          uuid  not null,
    preferences jsonb not null,
    primary key (id)
);
