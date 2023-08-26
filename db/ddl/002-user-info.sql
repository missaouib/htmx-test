create table user_info
(
    id          uuid not null,
    preferences jsonb,
    primary key (id)
);
