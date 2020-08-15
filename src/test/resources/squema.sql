create table engines (
    id   serial primary key,
    name text
);

create table cars (
    id        serial primary key,
    name      text,
    engine_id int references engines (id) not null
);

create table drivers (
    id   serial primary key,
    name text
);

create table owner_history (
    car_id    int references cars (id),
    driver_id int references drivers (id)
)