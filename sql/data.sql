--user
insert into users(nomuser,prenomuser,mail,nee,pwd) values('Miaro','Tina','miaro@gmail.com','2002-10-10','1234');
insert into users(nomuser,prenomuser,mail,nee,pwd) values('Taf','Ita','tafita@gmail.com','2002-11-11','1234');
--marque
insert into marque(nommarque) values('MarqueU'),('MarqueV'),('MarqueW'),('MarqueX');
--model
insert into models(nommodel) values('ModelA'),('ModelB'),('ModelC'),('ModelD');
--Categorie
insert into categorie(nomcategorie) values('CategorieB'),('CategorieA'),('CategorieA'),('CategorieB'),('CategorieD'),('CategorieD'),('CategorieC'),('CategorieC');
--model categorie
insert into modelcategorie(idmodel,idcategorie) values
(1,2),(1,3),
(2,4),(2,1),
(3,8),(3,7),
(4,5),(4,6);
--Carburant
insert into carburant(nomcarburant) values('Essence'),('Gasoil'),('Electrique');
--lieu
insert into lieu(nomlieu) values('Mahajanga'),('Antsirabe'),('Toamasina'),('Toliara');
--credit code 
insert into valeurcredit(valeur) values(10000),(25000),(50000),(100000),(350000),(500000);
insert into codecredit(code,etats,idvaleurcredit) values
(10000001,0,1),
(10000002,0,1),
(10000003,0,1),
(10000004,0,1),
(10000005,0,2),
(10000006,0,2),
(10000007,0,2),
(10000008,0,2),
(10000009,0,3),
(10000010,0,3),
(10000011,0,3),
(10000012,0,3),
(10000013,0,4),
(10000014,0,4),
(10000015,0,4),
(10000016,0,4),
(10000017,0,5),
(10000018,0,5),
(10000019,0,5),
(10000020,0,5),
(10000021,0,6),
(10000022,0,6),
(10000023,0,6),
(10000024,0,6);
--regle taux commission
insert into regletaux (coderegle,nomregle,tauxpourcent) values('C000','commission',10);