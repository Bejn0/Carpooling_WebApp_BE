insert into person (user_id, first_name, last_name, "password", email)
values (1, 'Branimir', 'Cirovic', 'test123', 'bane@gmail.com'), 
(2, 'Vladimir', 'Cirovic', 'test123', 'biba@gmail.com'),
(3, 'Uros', 'Savic', 'test123', 'savke@gmail.com');

insert into ride (start_location, end_location, datetime, space, user_id)
values ('Opovo', 'Novi Sad', '2023-09-10 12:15:00', 5, 1),
('Opovo', 'Beograd', '2023-09-12 19:00:00', 3, 1),
('Opovo', 'Pancevo', '2023-09-14 15:00:00', 5, 1),
('Vrsac', 'Pancevo', '2023-09-10 16:15:00', 5, 2),
('Vrbas', 'Novi Sad', '2023-09-13 06:00:00', 3, 2),
('Opovo', 'Novi Sad', '2023-09-15 08:00:00', 5, 2),
('Padinska Skela', 'Beograd', '2023-09-15 19:15:00', 6, 3),
('Beograd', 'Subotica', '2023-09-11 19:15:00', 3, 3),
('Borca', 'Pancevo', '2023-09-12 20:30:00', 2, 3);