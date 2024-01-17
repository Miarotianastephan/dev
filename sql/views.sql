--categorie no mampiverimberina an'io
-- idannonce | prixvente | descriptions | statusvente | etat | dateannonce | idlieu | nomlieu | idvoitureinfo | nomvoiture | nombreplace | kilometrage | transmission | vitesse | iduser | nomuser | prenomuser | idcarburant | nomcarburant | idmarque | nommarque | idmodel | nommodel | idcategorie | nomcategorie | datevente | datemodifstatus
---statusvente : 0 : vendu /10 : non vendu
---etat : 0:encour demande / 10 :accepter / 20: refuser
create or replace view annoncedetail_v as
   select a.idannonce,
         a.prixvente,a.descriptions,a.statusvente,a.etat,a.dateannonce,
         a.idlieu,l.nomlieu,
         a.idvoitureinfo,vi.nomvoiture,vi.nombreplace,vi.kilometrage,vi.transmission,vi.vitesse,
         vi.iduser,u.nomuser,u.prenomuser,
         vi.idcarburant,cb.nomcarburant,
         vi.idmarque,mq.nommarque,
         vi.idmodel,md.nommodel,
         ct.idcategorie,ct.nomcategorie,
         v.datevente,
         v.datemodifstatus,
         aph.idannoncephoto,
         aph.photo
         from annonce as a
         join lieu as l on l.idlieu=a.idlieu
         join voitureinfo as vi on a.idvoitureinfo=vi.idvoitureinfo
         join users as u on u.iduser=vi.iduser
         join carburant as cb on cb.idcarburant=vi.idcarburant
         join marque as mq on mq.idmarque=vi.idmarque
         join models as md on md.idmodel=vi.idmodel
         join categorievoiture as cv on cv.idvoitureinfo=vi.idvoitureinfo
         join categorie as ct on ct.idcategorie=cv.idcategorie
         left join annoncephoto as aph on aph.idannonce=a.idannonce
         left join vendu as v on v.idannonce=a.idannonce;

--pub a part mes pubs, non vendu , deja accepter
--LIMIT 6 OFFSET 5; --->le 5e ligne tsy asehony , de manomboka eo @ ]5, ...6 ligne ambony]
select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v 
left join annoncefavoris as af 
      on (ad_v.idannonce=af.idannonce and af.iduser='iduserconnect') 
where ad_v.iduser!=1 
      and ad_v.statusvente=10 
      and ad_v.etat=10 
order by ad_v.idannonce ASC,
      ad_v.idcategorie ASC,
      ad_v.dateannonce ASC 
LIMIT 6 OFFSET 6;


select a.* from annonce as a join voitureinfo as vif on a.idannonce=vif.idannonce where a.idannonce= and vif.iduser=

create or replace view codecredit_v as
      select c.*,v.valeur from codecredit as c 
            join valeurcredit as v 
            on c.idvaleurcredit=v.idvaleurcredit;



-- prixvente double
-- descriptions text
-- dateannonce date
-- nomlieu varchar
-- nomvoiture varchar
-- nombreplace int
-- kilometrage double
-- transmission int
-- vitesse
-- nomuser
-- prenomuser
-- nomcarburant
-- nommarque
-- idmodel
-- nommodel
-- nomcategorie
select ad_v.*, af.idannoncefavoris from annoncedetail_v as ad_v left join annoncefavoris as af on (ad_v.idannonce=af.idannonce and af.iduser= :iduser) 
where ad_v.iduser!= :iduser
ad_v.dateannonce ASC
LIMIT :nbaffiche OFFSET :numlineBeforeFirst