create table operation_transaction
(
    id             uuid           not null,
    "user_id"      uuid           not null,
    operation_id   uuid           not null,
    transaction_id uuid           not null,
    amount         numeric(38, 2) not null,
    primary key (id),
    unique (operation_id, transaction_id)
);

alter table if exists operation_transaction
    add constraint operation_transaction_user_id_fkey foreign key ("user_id") references "user";
alter table if exists operation_transaction
    add constraint operation_transaction_operation_id_fkey foreign key (operation_id) references operation;
alter table if exists operation_transaction
    add constraint operation_transaction_transaction_id_fkey foreign key (transaction_id) references transaction;

create index operation_transaction_transaction_id_idx on operation_transaction (transaction_id);
create index operation_transaction_user_id_idx on operation ("user_id");
