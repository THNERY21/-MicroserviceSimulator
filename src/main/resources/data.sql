INSERT INTO TYPE_PRODUCT 
( NAME_TYPE , TYPE_DESCRIPTION  )
VALUES( 'Algodão', '100% Algodão');


INSERT INTO TYPE_PRODUCT 
( NAME_TYPE , TYPE_DESCRIPTION  )
VALUES( 'Poliéster', '100% Poliéster');

INSERT INTO PRODUCTS 
(NAME , SIZE , TYPE , WEIGHT ) VALUES ('Camisa','M','Algodão','0.3');

INSERT INTO PRODUCTS 
(NAME , SIZE , TYPE , WEIGHT ) VALUES ('Bermuda','M','Poliéster','0.12');

INSERT INTO USER 
( NAME , CPF , EMAIL , PASSWORD , USER )
VALUES  ('THIAGO','73183823306','thnery23@hotmail.com','$2a$10$QkeW1SKbx3Kbwhq/mv56K.vUgWQeBa59m6aPrF2I5p4cerGHh.TUm','thnery23' );

INSERT INTO USER 
( NAME , CPF , EMAIL , PASSWORD , USER )
VALUES  ('Admin','73183823306','admin@admin.com','$2a$10$QkeW1SKbx3Kbwhq/mv56K.vUgWQeBa59m6aPrF2I5p4cerGHh.TUm','administrador' );

 

INSERT INTO PROFILE_USER ( ID , NAME ) VALUES (1,'ROLE_USER');

INSERT INTO PROFILE_USER ( ID , NAME ) VALUES (2,'ROLE_ADMINISTRATOR');


INSERT INTO USER_PROFILES  ( USER_ID,PROFILES_ID  ) VALUES (1,1);
INSERT INTO USER_PROFILES  ( USER_ID,PROFILES_ID  ) VALUES (1,2);
