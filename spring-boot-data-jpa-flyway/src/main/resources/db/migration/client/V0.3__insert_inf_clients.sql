--########
-- person_role
INSERT INTO public.person_role
(name_role, description)
VALUES('admin', 'administrador');
INSERT INTO public.person_role
(name_role, description)
VALUES('employe', 'empleado');
INSERT INTO public.person_role
(name_role, description)
VALUES('client', 'cliente');

-- type identification
INSERT INTO public.type_identification_person
(name_identify, description)
VALUES('CIF', '');
INSERT INTO public.type_identification_person
(name_identify, description)
VALUES('NIF', '');
INSERT INTO public.type_identification_person
(name_identify, description)
VALUES('NIE', '');
INSERT INTO public.type_identification_person
(name_identify, description)
VALUES('PASSPORT', '');

--########
--person
INSERT INTO public.person_account
(account_id, nick, email, pwd, input_date, exit_date)
VALUES(1, 'chupendo', 'a@gmail.com', 'h234sas', TIMESTAMP '2001-02-16 20:38:40' AT TIME ZONE 'MST', null);
INSERT INTO public.person
(person_id, account_id, role_id, type_identification_person_id, identifier, first_name, last_name, surnames, address, birthday, post_card, town, city, country, photo)
VALUES(1, 1, 1, 1, '289795684A', 'Andrés', '', 'Ruiz Peñuela', 'Calle Alfonso XII, 3 1º', NOW()::timestamp, 23400 , 'UBEDA', 'JAEN', 'ESPAÑA', '');


INSERT INTO public.person_account
(account_id, nick, email, pwd, input_date, exit_date)
VALUES(2, 'roke', 'roke@gmail.com', 'h234sas', TIMESTAMP '2001-02-16 20:38:40' AT TIME ZONE 'MST', null);
INSERT INTO public.person
(person_id, account_id, role_id, type_identification_person_id, identifier, first_name, last_name, surnames, address, birthday, post_card, town, city, country, photo)
VALUES(2, 2, 1, 1, '289795684A', 'Pedro', '', 'ERs', 'Calle Alfonso XII, 3 1º', NOW()::timestamp, 23400 , 'UBEDA', 'JAEN', 'ESPAÑA', '');

--select * from public.person_role;
--select * from public.type_identification_person;
--select * from public.person_account;
--select * from public.person;


/*
 DELETE FROM public.type_identification_person
WHERE type_identification_id=2;

DELETE FROM public.person
WHERE person_id=1;

DELETE FROM public.person_client
WHERE client_id=1;


DELETE FROM public.person_role
WHERE role_id=1;
 */