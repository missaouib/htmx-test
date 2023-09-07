create table transaction
(
    id       uuid                        not null,
    date     timestamp(6) with time zone not null,
    account  varchar(255)                not null,
    category varchar(255)                not null,
    note     varchar(2556)               not null,
    amount   numeric(38, 2)              not null,
    primary key (id)
);

create index transaction_date_idx on transaction (date);

comment on column transaction.note is 'General-use text column';
