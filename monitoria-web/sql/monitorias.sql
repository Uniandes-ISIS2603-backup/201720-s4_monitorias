delete from SalonEntity;
delete from SedeEntity;
delete from PagoEntity;

delete  from  RecursoEntity;
delete  from BibliotecaEntity;

delete from MONITORENTITY_IDIOMAENTITY;
delete from MONITORENTITY;
delete from MONITORIAENTITY;
delete from VALORACIONENTITY;
delete from IDIOMAENTITY;
delete from ACTIVIDADENTITY;
delete from HorarioEntity;
delete from EstudianteEntity;


insert into SedeEntity (id, name, direccion) values (100, 'Sede1', 'Calle 101 9 40');
insert into SedeEntity (id, name, direccion) values (200, 'Sede2', 'Calle 5 1 12');
insert into SedeEntity (id, name, direccion) values (300, 'Sede3', 'Calle 53 68 27');


insert into SalonEntity (id, nombre, sede_id) values (100, 'W 505', 100);
insert into SalonEntity (id, nombre, sede_id) values (200, 'ML 302', 100);
insert into SalonEntity (id, nombre, sede_id) values (300, 'SD 703', 200);
insert into SalonEntity (id, nombre, sede_id) values (400, 'Q 907', 300);
insert into SalonEntity (id, nombre, sede_id) values (500, 'Z 101', 300);

insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Lucia Perez',1,0, 134,'https://vignette.wikia.nocookie.net/doblaje/images/4/4b/732867_1306822514358_full.jpg/revision/latest?cb=20110823195937&path-prefix=es');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Pepe Castro',2,0, 14,'http://img4.wikia.nocookie.net/__cb20120515182324/superpoderosa/es/images/thumb/6/6a/Powerpuff-Girls-cn05.jpg/179px-Powerpuff-Girls-cn05.jpg');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Sofia Duarte',3,0,13,'http://www.canalfreak.net/wp-content/uploads/2016/04/bellota.png');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Martin Ruiz',3,0, 346,'http://www.canalfreak.net/wp-content/uploads/2016/04/bellota.png');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Manuel Giraldo',3,0, 1243,'https://vignette.wikia.nocookie.net/legendsofthemultiuniverse/images/3/36/Kim_Possible0.png/revision/latest?cb=20131207031528');
insert into MonitorEntity ( nombre,  tipo, valPromedio, codigo,foto) values ('Carlos Nope',3,0, 67,'http://www.canalfreak.net/wp-content/uploads/2016/04/bellota.png');




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

insert into PagoEntity (id,  valor, monitor_codigo) values (1, 4.6, 14);
insert into PagoEntity (id,  estado, valor, monitor_codigo) values (2,1, 5.6, 13);
insert into PagoEntity (id,  valor, monitor_codigo) values (3, 3, 67);


insert into HorarioEntity (horaInicio, horaFin) values ('2017-08-04 03:11:50', '2017-02-23 23:32:44');
insert into HorarioEntity (horaInicio, horaFin) values ('2017-07-22 12:17:22', '2017-04-02 12:06:51');
insert into HorarioEntity (horaInicio, horaFin) values ('2016-11-08 07:32:14', '2017-04-01 00:09:34');
insert into HorarioEntity (horaInicio, horaFin) values ('2017-10-30 05:37:58', '2017-05-04 21:08:14');
insert into HorarioEntity (horaInicio, horaFin) values ('2017-03-17 22:32:22', '2017-03-13 14:17:10');
insert into HorarioEntity (horaInicio, horaFin) values ('2016-12-10 03:15:12', '2017-09-12 16:49:24');

insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (1, 'Giacomo Laydel', 4023467, 'glaydel0@marketwatch.com', 1, '2016-11-23 09:10:31');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (2, 'Korey Sidney', 5921319, 'ksidney1@vkontakte.ru', 1, '2017-07-04 22:40:22');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (3, 'Linette Fouldes', 4606418, 'lfouldes2@webeden.co.uk', 1, '2017-04-19 07:34:07');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (4, 'Ag Strickett', 2902476, 'astrickett3@t-online.de', 0, '2017-06-05 09:16:43');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (5, 'Ricoriki Stoney', 5338212, 'rstoney4@storify.com', 0, '2017-04-16 18:19:08');
insert into EstudianteEntity (id, name, codigo, correo, penalizacion, ultimaMonitoria) values (6, 'Sancho Beynke', 5541165, 'sbeynke5@thetimes.co.uk', 0, '2017-06-24 22:44:55');

