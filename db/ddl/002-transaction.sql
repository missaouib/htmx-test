create table transaction
(
    id        uuid                        not null,
    "user_id" uuid                        not null,
    date      timestamp(6) with time zone not null,
    account   varchar(255)                not null,
    note      varchar(2556)               not null,
    amount    numeric(38, 2)              not null,
    primary key (id)
);

alter table if exists transaction
    add constraint transaction_user_id_fkey foreign key ("user_id") references "user";

create index transaction_date_idx on transaction (date);
create index transaction_user_id_idx on transaction ("user_id");

comment on column transaction.note is 'General-use text column';
