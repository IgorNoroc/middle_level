create table person (
   id serial primary key not null,
   login varchar(2000),
   password varchar(2000)
);

create table employee(
    id serial primary key not null,
    name varchar(200),
    lastname varchar(200),
    personalCode varchar(200),
    hired timestamp  default now()
);

create table employee_person(
    employee_id int references employee(id),
    person_id int references person(id)
)