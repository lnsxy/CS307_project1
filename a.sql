create table if not exists containers(
    code varchar(80) primary key ,
    type varchar(80)
) ;

create table if not exists City(
    name varchar(80) primary key
);

create table if not exists company(
    name varchar(80) primary key
);

create table if not exists item(
    name varchar(80) primary key ,
    type varchar(80),
    price int,
    logTime timestamp,
    containers varchar(80),
    foreign key (containers) references containers(code)
);

create table if not exists courier(
    name varchar(80) not null ,
    phoneNumber varchar(80) not null ,
    primary key (name,phoneNumber),
    age int,
    gender varchar(80),
    company varchar(80),
    city varchar(80),
    foreign key (city) references City(name),
    foreign key (company) references company(name)
);

create table if not exists ship(
    name varchar(80) primary key ,
    company varchar(80),
    foreign key (company) references company(name)
);

create table if not exists import(
    item varchar(80) ,
    city varchar(80),
    primary key (item,city),
    time date,
    tax float,
    foreign key (item) references item(name),
    foreign key (city) references City(name)
);

create table if not exists export(
    item varchar(80) ,
    city varchar(80),
    primary key (item,city),
    time date,
    tax float,
    foreign key (item) references item(name),
    foreign key (city) references City(name)
);

create table if not exists retrieval(
    item varchar(80) ,
    courier varchar(80),
    courierPhoneNumber varchar(80),
    primary key (item,courier,courierPhoneNumber),
    city varchar(80),
    startTime date,
    foreign key (item) references item(name),
    foreign key (courier,courierPhoneNumber) references courier(name,phoneNumber),
    foreign key (city) references city(name)
);

create table if not exists delivery(
    item varchar(80) ,
    courier varchar(80),
    courierPhoneNumber varchar(80),
    primary key (item,courier,courierPhoneNumber),
    city varchar(80),
    finishTime date,
    foreign key (item) references item(name),
    foreign key (courier,courierPhoneNumber) references courier(name,phoneNumber),
    foreign key (city) references city(name)
);
