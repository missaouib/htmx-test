create table operation_transaction
(
    id             uuid           not null,
    operation_id   uuid           not null,
    transaction_id uuid           not null,
    amount         numeric(38, 2) not null,
    primary key (id),
    unique (operation_id, transaction_id)
);

alter table if exists operation_transaction
    add constraint operation_transaction_operation_id_fkey foreign key (operation_id) references operation;
alter table if exists operation_transaction
    add constraint operation_transaction_transaction_id_fkey foreign key (transaction_id) references transaction;
