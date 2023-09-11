create table operation
(
    id        uuid                        not null,
    "user_id" uuid                        not null,
    date      timestamp(6) with time zone not null,
    category  varchar(255)                not null,
    note      varchar(2556)               not null,
    amount    numeric(38, 2)              not null,
    primary key (id)
);

alter table if exists operation
    add constraint operation_user_id_fkey foreign key ("user_id") references "user";

create index operation_date_idx on operation (date);
create index operation_user_id_idx on operation ("user_id");

comment on column operation.note is 'General-use text column';
