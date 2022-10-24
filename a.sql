create table item(
    name varchar(80) primary key ,
    type varchar(80),
    price int,
    Log_time time,
    containers varchar(80),
    foreign key (containers)
        references containers(code)
);

create table containers(
    code varchar(80) primary key ,
    type varchar(80)
);

create table City(
    name varchar(80)
);

create table courier(
    name varchar(80) primary key ,
    phone_number varchar(80),
    age int,
    gender varchar(80),
    company varchar(80),
    city varchar(80),
    foreign key (city)
        references City(name),
    foreign key (company)
        references company(name)
);

create table company(
    name varchar(80) primary key
);

create table ship(
    name varchar(80) primary key ,
    company varchar(80),
    foreign key (company)
        references company(name)
);

create table import(
    item varchar(80) primary key ,
    city varchar(80),
    time time,
    tax int,
    foreign key (item) references item(name),
    foreign key (city) references City(name)
);

create table export(
    item varchar(80) primary key ,
    city varchar(80),
    time time,
    tax int,
    foreign key (item) references item(name),
    foreign key (city) references City(name)
);

create table retrieval(
    item varchar(80) primary key ,
    courier varchar(80),
    Start_time time,
    foreign key (item) references item(name),
    foreign key (courier) references courier(name)
);

create table develiver(
    item varchar(80) primary key ,
    courier varchar(80),
    Finish_time time,
    foreign key (item) references item(name),
    foreign key (courier) references courier(name)
);
