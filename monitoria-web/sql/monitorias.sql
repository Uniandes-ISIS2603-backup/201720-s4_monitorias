delete from SalonEntity;
delete from SedeEntity;
delete from MonitorEntity;
delete from PagoEntity;

insert into SedeEntity (id, name, direccion) values (100, 'Sede1', 'Calle 101 9 40');
insert into SedeEntity (id, name, direccion) values (100, 'Sede2', 'Calle 5 1 12');
insert into SedeEntity (id, name, direccion) values (100, 'Sede3', 'Calle 53 68 27');


insert into SalonEntity (id, name,  disponibilidad, localizacion) values (100,'', true, 'W 505');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (200,'', false, 'ML 302');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (300,'', true, 'SD 703');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (400,'', false, 'Q 907');
insert into SalonEntity (id, name,  disponibilidad, localizacion) values (500,'', true, 'Z 101');

insert into MonitorEntity (id, name,  tipo, valPromedio, codigo) values (1,'Lucia Perez',1,4.6, 134);
insert into MonitorEntity (id, name,  tipo, valPromedio, codigo) values (2,'Pepe Castro',2,3.5, 14);
insert into MonitorEntity (id, name,  tipo, valPromedio, codigo) values (3,'Sofia Duarte',3,5.0, 13);

insert into PagoEntity (id,  valor, monitor_id) values (1, 4.6, 2);
insert into PagoEntity (id,  estado, valor, monitor_id) values (2,1, 5.6, 1);
insert into PagoEntity (id,  valor, monitor_id) values (3, 3, 2);