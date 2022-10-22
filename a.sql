create table item(
    name varchar primary key ,
    type varchar,
    price int,
    Export_tax int,
    Export_time time,
    Retrieval_start_time time,
    Delivery_finished_time time,
    Import_tax int,
    Import_time time,
    Log_time time,
    Import_city varchar,
    Export_city varchar,
    retrieval_city varchar,
    deliver_city varchar,
    container varchar,
    retrieval_courier int,
    deliver_courier int,
    foreign key (Import_city)
                 references city(name),
    foreign key (Export_city)
                 references city(name),
    foreign key (retrieval_city)
                 references city(name),
    foreign key (deliver_city)
                 references city(name),
    foreign key (container)
                 references contaners(code),
    foreign key (retrieval_courier)
                 references courier(phone_number),
    foreign key (deliver_courier)
                 references courier(phone_number)
);

create table city(
    name varchar primary key
);

create table contaners(
    code int primary key ,
    type varchar
);

create table courier(
    phone_number int primary key ,
    name varchar ,
    gender varchar,
    age int,
    city varchar,
    company varchar,
    foreign key (city)
                    references city(name),
    foreign key (company)
                    references company(name)
);

create table ship(
    name varchar primary key ,
    company varchar,
    foreign key (company)
                 references company(name)
);

create table company(
    name varchar primary key
);