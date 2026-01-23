-- users schema
create schema if not exists users;

create table if not exists users.user_id (
    id          serial                      not null primary key,
    created_at  timestamp without time zone not null default current_timestamp
);

create table if not exists users.withdrawal (
    id          serial                      not null primary key,
    user_id     integer                     not null references users.user_id(id),
    reason      text,                       -- 退会理由（任意）
    created_at  timestamp without time zone not null default current_timestamp
);
create index idx_withdrawal_user_id on users.withdrawal(user_id);

create table if not exists users.profile (
    id          serial                      not null primary key,
    user_id     integer                     not null references users.user_id(id),
    first_name  varchar(100)                not null,
    last_name   varchar(100)                not null,
    created_at  timestamp without time zone not null default current_timestamp
);
create index idx_profile_user_id on users.profile(user_id, created_at desc);

create table if not exists users.contact (
    id           serial                      not null primary key,
    user_id      integer                     not null references users.user_id(id),
    mail_address varchar(255)                not null,
    phone_number varchar(11)                 not null,
    created_at   timestamp without time zone not null default current_timestamp
);
create index idx_contact_user_id on users.contact(user_id, created_at desc);

create table if not exists users.address (
    id              serial not null primary key,
    user_id         integer                     not null references users.user_id(id),
    postal_code     varchar(7)                  not null,
    prefecture      varchar(20)                 not null,
    city            varchar(20)                 not null,
    address_line    varchar(255)                not null,
    building_name   varchar(100)                not null default '',
    building_number varchar(10)                 not null default '',
    created_at      timestamp without time zone not null default current_timestamp
);
create index idx_address_user_id on users.address(user_id, created_at desc);

-- products schema
create schema if not exists products;

create table if not exists products.product_id (
    id          serial                      not null primary key,
    created_at  timestamp without time zone not null default current_timestamp
);

create table if not exists products.overview (
    id          serial                      not null primary key,
    product_id  integer                     not null references products.product_id(id),
    name        varchar(255)                not null,
    description text,
    created_at  timestamp without time zone not null default current_timestamp
);
create index idx_overview_product_id on products.overview(product_id, created_at desc);

create table if not exists products.price (
    id          serial                      not null primary key,
    product_id  integer                     not null references products.product_id(id),
    price       integer                     not null,
    created_at  timestamp without time zone not null default current_timestamp
);
create index idx_price_product_id on products.price(product_id, created_at desc);

create table if not exists products.stock (
    id          serial                      not null primary key,
    product_id  integer                     not null references products.product_id(id),
    quantity    integer                     not null,
    created_at  timestamp without time zone not null default current_timestamp
);
create index idx_stock_product_id on products.stock(product_id, created_at desc);

-- orders schema
create schema if not exists orders;

create table if not exists orders.order_id (
    id          serial                      not null primary key,
    user_id     integer                     not null references users.user_id(id),
    created_at  timestamp without time zone not null default current_timestamp
);
create index idx_order_id_user_id on orders.order_id(user_id, created_at desc);

create table if not exists orders.shipping_address (
    id              serial                      not null primary key,
    order_id        integer                     not null references orders.order_id(id),
    postal_code     varchar(7)                  not null,
    address_full    text                        not null, -- 注文時の住所を固定
    created_at      timestamp without time zone not null default current_timestamp
);
create index idx_orders_shipping_address_order_id on orders.shipping_address(order_id, created_at desc);

create table if not exists orders.item (
    id              serial                      not null primary key,
    order_id        integer                     not null references orders.order_id(id),
    product_id      integer                     not null references products.product_id(id),
    unit_price      integer                     not null, -- 購入時の価格
    quantity        integer                     not null,
    created_at      timestamp without time zone not null default current_timestamp
);
create index idx_orders_item_order_id on orders.item(order_id, created_at desc);

create table if not exists orders.status (
     id              serial                      not null primary key,
     order_id        integer                     not null references orders.order_id(id),
     status_code     varchar(20)                 not null, -- 'ORDERED', 'SHIPPED', 'CANCELLED' など
     created_at      timestamp without time zone not null default current_timestamp
);
create index idx_orders_status_order_id on orders.status(order_id, created_at desc);