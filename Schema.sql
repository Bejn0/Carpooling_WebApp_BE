create table person(
user_id serial primary key,
first_name VARCHAR (50) not null,
last_name VARCHAR (50) not null,
password VARCHAR (255) not null,
email VARCHAR(255) unique not null);

create table ride(
ride_id serial primary key,
start_location VARCHAR(100) not null,
end_location VARCHAR(100) not null,
dateTime timestamp not null,
space int not null,
user_id int not null,
foreign key(user_id) 
	references person (user_id));
	
create table reservation(
reservation_id serial primary key,
user_id int not null,
ride_id int not null,
space int not null,
foreign key (user_id)
	references person (user_id),
foreign key (ride_id)
	references ride (ride_id)
);