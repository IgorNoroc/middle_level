create database one_to_many;

create table cars(
    id serial primary key,
    name text
)

create table car_brands(
    id serial primary key,
    name text
)

create table car_brands_cars (
     car_brand_id int references car_brands(id),
     car_id int references cars(id)
)
