create database voitureoccas;
\c voitureoccas

CREATE TABLE admins(
   idadmins SERIAL,
   nomadmin VARCHAR(50) ,
   prenomadmin VARCHAR(50) ,
   mail VARCHAR(50) ,
   nee DATE,
   pwd text,
   PRIMARY KEY(idadmins),
   UNIQUE(mail)
);

CREATE TABLE users(
   iduser SERIAL,
   nomuser VARCHAR(50) ,
   prenomuser VARCHAR(50) ,
   mail VARCHAR(50) ,
   nee DATE,
   pwd text,
   PRIMARY KEY(iduser),
   UNIQUE(mail)
);

CREATE TABLE models(
   idmodel SERIAL,
   nommodel VARCHAR(50) ,
   PRIMARY KEY(idmodel)
);

CREATE TABLE marque(
   idmarque SERIAL,
   nommarque VARCHAR(50) ,
   PRIMARY KEY(idmarque)
);

CREATE TABLE categorie(
   idcategorie SERIAL,
   nomcategorie VARCHAR(50) ,
   PRIMARY KEY(idcategorie)
);

CREATE TABLE carburant(
   idcarburant SERIAL,
   nomcarburant VARCHAR(50) ,
   PRIMARY KEY(idcarburant)
);

CREATE TABLE voitureinfo(
   idvoitureinfo SERIAL,
   nomvoiture VARCHAR(50) ,
   nombreplace INTEGER,
   kilometrage DOUBLE PRECISION,
   transmission INTEGER,
   vitesse DOUBLE PRECISION,
   iduser INTEGER NOT NULL,
   idcarburant INTEGER NOT NULL,
   idmarque INTEGER NOT NULL,
   idmodel INTEGER NOT NULL,
   PRIMARY KEY(idvoitureinfo),
   FOREIGN KEY(iduser) REFERENCES users(iduser),
   FOREIGN KEY(idcarburant) REFERENCES carburant(idcarburant),
   FOREIGN KEY(idmarque) REFERENCES marque(idmarque),
   FOREIGN KEY(idmodel) REFERENCES models(idmodel)
);

CREATE TABLE lieu(
   idlieu SERIAL,
   nomlieu VARCHAR(50) ,
   PRIMARY KEY(idlieu)
);
CREATE TABLE tokenuser(
   idtokenuser SERIAL,
   token TEXT,
   datedebut TIMESTAMP,
   dateexp TIMESTAMP,
   etats INTEGER,
   iduser INTEGER NOT NULL,
   PRIMARY KEY(idtokenuser),
   FOREIGN KEY(iduser) REFERENCES users(iduser)
);

CREATE TABLE soldeuser(
   idsoldeuser SERIAL,
   solde DOUBLE PRECISION,
   dateupdate TIMESTAMP,
   iduser INTEGER NOT NULL,
   PRIMARY KEY(idsoldeuser),
   UNIQUE(iduser),
   FOREIGN KEY(iduser) REFERENCES users(iduser)
);

CREATE TABLE soldesite(
   Idsoldesite SERIAL,
   solde DOUBLE PRECISION,
   dateupdate TIMESTAMP,
   PRIMARY KEY(Idsoldesite)
);

CREATE TABLE valeurcredit(
   idvaleurcredit SERIAL,
   valeur DOUBLE PRECISION,
   PRIMARY KEY(idvaleurcredit),
   UNIQUE(valeur)
);

CREATE TABLE motif(
   idmotif SERIAL,
   nommotif VARCHAR(50) ,
   codemotif INTEGER,
   PRIMARY KEY(idmotif),
   UNIQUE(codemotif)
);

CREATE TABLE annonce(
   idannonce SERIAL,
   prixvente DOUBLE PRECISION,
   descriptions VARCHAR(200) ,
   statusvente INTEGER,
   etat INTEGER,
   idlieu INTEGER NOT NULL,
   idvoitureinfo INTEGER NOT NULL,
   PRIMARY KEY(idannonce),
   UNIQUE(idvoitureinfo),
   FOREIGN KEY(idlieu) REFERENCES lieu(idlieu),
   FOREIGN KEY(idvoitureinfo) REFERENCES voitureinfo(idvoitureinfo)
);

CREATE TABLE annoncephoto(
   idannoncephoto SERIAL,
   photo VARCHAR(200) ,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idannoncephoto),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);

CREATE TABLE vendu(
   idvendu SERIAL,
   datevente DATE,
   datemodifstatus DATE,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idvendu),
   UNIQUE(idannonce),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);

CREATE TABLE annoncevalidation(
   idannoncevalidation SERIAL,
   datevalide DATE,
   idadmins INTEGER NOT NULL,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idannoncevalidation),
   UNIQUE(idannonce),
   FOREIGN KEY(idadmins) REFERENCES admins(idadmins),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);

CREATE TABLE annoncefavoris(
   idannoncefavoris SERIAL,
   iduser INTEGER NOT NULL,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idannoncefavoris),
   FOREIGN KEY(iduser) REFERENCES users(iduser),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);

CREATE TABLE annoncerefus(
   idannoncerefus SERIAL,
   daterefus DATE,
   idadmins INTEGER NOT NULL,
   idannonce INTEGER NOT NULL,
   PRIMARY KEY(idannoncerefus),
   UNIQUE(idannonce),
   FOREIGN KEY(idadmins) REFERENCES admins(idadmins),
   FOREIGN KEY(idannonce) REFERENCES annonce(idannonce)
);

CREATE TABLE debitersoldeuser(
   iddebit SERIAL,
   montantd VARCHAR(50) ,
   dated TIMESTAMP,
   idmotif INTEGER NOT NULL,
   idsoldeuser INTEGER NOT NULL,
   PRIMARY KEY(iddebit),
   FOREIGN KEY(idmotif) REFERENCES motif(idmotif),
   FOREIGN KEY(idsoldeuser) REFERENCES soldeuser(idsoldeuser)
);

CREATE TABLE creditersoldesite(
   idcredit SERIAL,
   montantc DOUBLE PRECISION,
   datec TIMESTAMP,
   iddebitersoldeuser INTEGER NOT NULL,
   idmotif INTEGER NOT NULL,
   Idsoldesite INTEGER NOT NULL,
   PRIMARY KEY(idcredit),
   UNIQUE(iddebitersoldeuser),
   FOREIGN KEY(iddebitersoldeuser) REFERENCES debitersoldeuser(iddebitersoldeuser),
   FOREIGN KEY(idmotif) REFERENCES motif(idmotif),
   FOREIGN KEY(Idsoldesite) REFERENCES soldesite(Idsoldesite)
);

CREATE TABLE codecredit(
   idcodecredit SERIAL,
   code INTEGER,
   etats INTEGER,
   idvaleurcredit INTEGER NOT NULL,
   PRIMARY KEY(idcodecredit),
   UNIQUE(code),
   FOREIGN KEY(idvaleurcredit) REFERENCES valeurcredit(idvaleurcredit)
);

CREATE TABLE creditersoldeuser(
   idcredit SERIAL,
   montantc DOUBLE PRECISION,
   datec TIMESTAMP,
   idcodecredit INTEGER NOT NULL,
   idsoldeuser INTEGER NOT NULL,
   PRIMARY KEY(idcredit),
   FOREIGN KEY(idcodecredit) REFERENCES codecredit(idcodecredit),
   FOREIGN KEY(idsoldeuser) REFERENCES soldeuser(idsoldeuser)
);

CREATE TABLE categorievoiture(
   idcategorievoiture SERIAL,
   idcategorie INTEGER,
   idvoitureinfo INTEGER,
   PRIMARY KEY(idcategorievoiture),
   FOREIGN KEY(idcategorie) REFERENCES categorie(idcategorie),
   FOREIGN KEY(idvoitureinfo) REFERENCES voitureinfo(idvoitureinfo)
);


---------------------------NON RELATIONNEL
CREATE TABLE messages(
   idmessages SERIAL,
   nomsend VARCHAR(50) ,
   prenomsend VARCHAR(50) ,
   nomreceive VARCHAR(50) ,
   prenomreceive VARCHAR(50),
   contenu TEXT,
   typemessage INTEGER,
   datehmsg TIMESTAMP,
   idusersend INTEGER NOT NULL,
   iduserreceive INTEGER NOT NULL
);