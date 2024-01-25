---statusvente : 0 : vendu /10 : non vendu
---etat : 0:encour demande / 10 :accepter / 20: refuser

create or replace view annoncedetail_v as
   select a.idannonce,
         a.prixvente,a.descriptions,a.statusvente,a.etat,a.dateannonce,
         a.idlieu,l.nomlieu,
         a.idvoitureinfo,vi.nomvoiture,vi.nombreplace,vi.kilometrage,md.transmission,t.nomtransmission,md.vitesse,
         vi.iduser,u.nomuser,u.prenomuser,
         md.idcarburant,cb.nomcarburant,
         md.idmarque,mq.nommarque,
         vi.idmodel,md.nommodel,
         ct.idcategorie,ct.nomcategorie,
         md.anneefab,
         v.datevente,
         v.datemodifstatus,
         aph.idannoncephoto,
         aph.photo
         from annonce as a
         join lieu as l on l.idlieu=a.idlieu
         join voitureinfo as vi on a.idvoitureinfo=vi.idvoitureinfo
         join users as u on u.iduser=vi.iduser
         join models as md on md.idmodel=vi.idmodel
         join carburant as cb on cb.idcarburant=md.idcarburant
         join marque as mq on mq.idmarque=md.idmarque
         join transmission as t on t.idtransmission=md.transmission
         join categorievoiture as cv on cv.idvoitureinfo=vi.idvoitureinfo
         join categorie as ct on ct.idcategorie=cv.idcategorie
         left join annoncephoto as aph on aph.idannonce=a.idannonce
         left join vendu as v on v.idannonce=a.idannonce;


create or replace view annoncenbrepeat_v as
      select idannonce,iduser,statusvente,etat,count(idannonce) as nbrepeat from annoncedetail_v
      group by idannonce,iduser,statusvente,etat;


--pub a part mes pubs, non vendu , deja accepter
--LIMIT 6 OFFSET 5; --->le 5e ligne tsy asehony , de manomboka eo @ ]5, ...6 ligne ambony]


create or replace view codecredit_v as
      select c.*,v.valeur from codecredit as c 
            join valeurcredit as v 
            on c.idvaleurcredit=v.idvaleurcredit;





-- -- Pour changer le type d'une colonne dans une table
-- ALTER TABLE annoncephoto
-- ALTER COLUMN photo TYPE text;


-- select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v 
-- left join annoncefavoris as af 
--       on (ad_v.idannonce=af.idannonce and af.iduser='iduserconnect') 
-- where ad_v.iduser!=1 
--       and ad_v.statusvente=10 
--       and ad_v.etat=10 
-- order by ad_v.idannonce ASC,
--       ad_v.idcategorie ASC,
--       ad_v.dateannonce ASC 
-- LIMIT 6 OFFSET 6;
