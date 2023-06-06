INSERT INTO public.users( username, password, email ) values ( 'antonio.salazar',   '$2a$10$nmW9HJ85NMXdeXlZk8CyXeTNQVy77s9w2buztRnB4.eRpYBOO0v.q', 'antonio.salazar@gmail.com' );
INSERT INTO public.users( username, password, email ) values ( 'juan.beltran',      '$2a$10$nmW9HJ85NMXdeXlZk8CyXeTNQVy77s9w2buztRnB4.eRpYBOO0v.q', 'juan.beltran@gmail.com' );

INSERT INTO public.roles( name ) values ( 'ROLE_ADMIN' );
INSERT INTO public.roles( name ) values ( 'ROLE_USER' );

INSERT INTO public.users_roles( user_id, role_id ) values ( 1, 1 )
INSERT INTO public.users_roles( user_id, role_id ) values ( 1, 2 )
INSERT INTO public.users_roles( user_id, role_id ) values ( 2, 2 )
