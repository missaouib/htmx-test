create table transaction
(
    id       uuid not null,
    date     timestamp(6) with time zone,
    account  varchar(255),
    category varchar(255),
    note     varchar(2556),
    amount   numeric(38, 2),
    primary key (id)
);

create index IDXk3n1glkf8cs6vumkk0t46lk60 on transaction (date);
