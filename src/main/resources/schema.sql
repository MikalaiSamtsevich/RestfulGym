drop table if exists schedules;

drop table if exists training_types;

drop table if exists user_roles;

drop table if exists roles;

drop table if exists notifications;

drop table if exists users;

drop table if exists trainers;


create table roles
(
    id   serial
        primary key,
    name varchar(50) not null
);
create table trainers
(
    id               serial
        primary key,
    cost_per_session numeric(2)   not null,
    email            varchar(100) not null,
    first_name       varchar(100) not null,
    last_name        varchar(100) not null,
    phone_number     varchar(20)  not null,
    photo            varchar(255)
);

create table training_types
(
    id   serial
        primary key,
    name varchar(100) not null
);

create table users
(
    id         serial
        primary key,
    email      varchar(100) not null,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    password   varchar(255) not null,
    username   varchar(100) not null,
    trainer_id integer
        constraint fkh22b6bdb065nbri3q9dinb0k5
            references trainers
            on update cascade on delete set null
);
create table schedules
(
    id               serial
        primary key,
    end_time         timestamp(6) with time zone not null,
    start_time       timestamp(6) with time zone not null,
    trainer_id       integer
        constraint fk8k8sargvsxu3pbed0a0q0bspi
            references trainers
            on update cascade on delete cascade,
    training_type_id integer
        constraint fke2fsgktybwn091y3io76hsrtd
            references training_types
            on update cascade on delete set null,
    user_id          integer
        constraint fkd4y4xekwahv9boo6lc8gfl3jv
            references users
            on update cascade on delete set null
);
create table user_roles
(
    user_id integer not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_id serial
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    primary key (role_id, user_id)
);
create table notifications
(
    id         serial
        primary key,
    created_at timestamp(6) with time zone,
    message    text    not null,
    user_id    integer not null
        constraint fk9y21adhxn0ayjhfocscqox7bh
            references users
);

