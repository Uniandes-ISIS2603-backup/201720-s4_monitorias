delete from SalonEntity;
delete from SedeEntity;
delete from PagoEntity;

delete  from  RecursoEntity;
delete  from BibliotecaEntity;


delete from MONITORENTITY;
delete from MONITORENTITY_IDIOMAENTITY;
delete from IDIOMAENTITY;
delete from ACTIVIDADENTITY;
delete from HorarioEntity;
delete from EstudianteEntity;


insert into SedeEntity (id, name, direccion) values (100, 'Sede1', 'Calle 101 9 40');
insert into SedeEntity (id, name, direccion) values (200, 'Sede2', 'Calle 5 1 12');
insert into SedeEntity (id, name, direccion) values (300, 'Sede3', 'Calle 53 68 27');


insert into SalonEntity (id, name,  disponibilidad, localizacion, sede_id) values (100,'', 1, 'W 505', 100);
insert into SalonEntity (id, name,  disponibilidad, localizacion, sede_id) values (200,'', 0, 'ML 302', 100);
insert into SalonEntity (id, name,  disponibilidad, localizacion, sede_id) values (300,'', 1, 'SD 703', 200);
insert into SalonEntity (id, name,  disponibilidad, localizacion, sede_id) values (400,'', 0, 'Q 907', 300);
insert into SalonEntity (id, name,  disponibilidad, localizacion, sede_id) values (500,'', 1, 'Z 101', 300);

insert into MonitorEntity (id, nombre,  tipo, valPromedio, codigo) values (1,'Lucia Perez',1,4.6, 134);
insert into MonitorEntity (id, nombre,  tipo, valPromedio, codigo) values (2,'Pepe Castro',2,3.5, 14);
insert into MonitorEntity (id, nombre,  tipo, valPromedio, codigo) values (3,'Sofia Duarte',3,5.0, 13);

insert into PagoEntity (id,  valor, monitor_id) values (1, 4.6, 2);
insert into PagoEntity (id,  estado, valor, monitor_id) values (2,1, 5.6, 1);
insert into PagoEntity (id,  valor, monitor_id) values (3, 3, 2);


insert into BIBLIOTECAENTITY (id, name, ubicacion) values (100, 'Stamm-Mertz', 'Ilene');
insert into BIBLIOTECAENTITY (id, name, ubicacion) values (200, 'Littel-Lindgren', 'East');
insert into BIBLIOTECAENTITY (id, name, ubicacion) values (300, 'Toy, Hammes and Weissnat', 'Blaine');
insert into BIBLIOTECAENTITY (id, name, ubicacion) values (400, 'Considine LLC', 'Farragut');
insert into BIBLIOTECAENTITY (id, name, ubicacion) values (500, 'Cole-Jacobson', 'Sommers');

insert into IDIOMAENTITY(id, idioma) values (100, 'ingles');
insert into IDIOMAENTITY(id, idioma) values (200, 'frances');
insert into IDIOMAENTITY(id, idioma) values (300, 'italiano');
insert into IDIOMAENTITY(id, idioma) values (400, 'ruso');
insert into IDIOMAENTITY(id, idioma) values (500, 'aleman');

insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (100, 0, 'Mybuzz', 'Miller, Ferry and Auer', 100);
insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (200, 0, 'Oyoba', 'Heidenreich-Konopelski', 200);
insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (300, 1, 'Shuffledrive', 'Buckridge, Schimmel and Labadie', 300);
insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (400, 1, 'Oloo', 'Jacobson, Hauck and Hirthe', 400);
insert into RecursoEntity (id, disponibilidad, editorial, name, biblioteca_id) values (500, 0, 'Lazz', 'Aufderhar-Bartell', 500);

insert into ACTIVIDADENTITY(id, tareaAsignada, descripcion) values (100,'realizar lectura','leer el primer capitulo del libro: Hauck and Hirthe' );
insert into ACTIVIDADENTITY(id, tareaAsignada, descripcion) values (200,'realizar lectura 2','leer el segundo capitulo del libro: Aufderhar-Bartell' );
insert into ACTIVIDADENTITY(id, tareaAsignada, descripcion) values (300,'realizar lectura','leer el cuarto capitulo del libro: Ferry and Auer' );



insert into HorarioEntity (id, horaInicio, horaFin) values (1, 'Tue 26 0[20:05:36]0 COT 2017', 'Tue 29 0[22:05:36]0 COT 2017');
insert into HorarioEntity (id, horaInicio, horaFin) values (2, 'Tue 26 0[22:05:36]0 COT 2017', 'Tue 28 0[22:05:36]0 COT 2017');
insert into HorarioEntity (id, horaInicio, horaFin) values (3, 'Tue 26 0[21:05:36]0 COT 2017', '2018-10-08T08:30:20-05:00');


insert into EstudianteEntity (id, name,  codigo, penalizacion,ultimaMonitoria) values (1,'Cristian', 2015, 'false','2018-10-08T08:30:20-05:00');
insert into EstudianteEntity (id, name,  codigo, penalizacion,ultimaMonitoria) values (2,'Cristiannn', 20153, '2018-10-08T08:30:20-05:00');
insert into EstudianteEntity (id, name,  codigo, penalizacion,ultimaMonitoria) values (3,'Cristiann', 20151, '2018-10-08T08:30:20-05:00');
insert into EstudianteEntity (id, name,  codigo, penalizacion,ultimaMonitoria) values (4,'Cristiannn', 20152, '2018-10-08T08:30:20-05:00');

