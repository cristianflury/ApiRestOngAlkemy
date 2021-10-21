/*ADMIN*/
INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"noah_smith@gmail.com", "noah", "smith", CURRENT_TIMESTAMP,"$2a$10$8yzx7nHircNLJIe6c3P3RO5n3SoPlfnNXvtfgYpTmkOKAkY/yNDem", "src//img/noah.jpg", id,2
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"jacob_williams@gmail.com", "jacob", "williams", CURRENT_TIMESTAMP,"$2a$10$IZlkF0CT0Bo6i4rCm0Bo8eeQRD130nWSRoafiwe8PSs8P36z1Ytyy", "src//img/jacob.jpg", id,1
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"emma_johnson@gmail.com", "emma", "johnson", CURRENT_TIMESTAMP,"$2a$10$Ss47neelt9.48vlSaSVlVOPoEx9/QXeb6E0wcV99WoJWbRCBzNvGW", "src//img/emma.jpg", id,2
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"ava_brown@gmail.com", "ava", "brown", CURRENT_TIMESTAMP,"$2a$10$0XWf6GBtcMRtAlYJJQT.ruGbpWp9AQP3LrHkhpYun6rUO/HxMfvh.", "src//img/ava.jpg", id,5
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"emily_jones@gmail.com", "emily", "jones", CURRENT_TIMESTAMP,"$2a$10$xjnnLkjdXvo1vnL2xrKFauFTCJ6mCbxWYeLDtJm7sN.NgVOhVqaFW", "src//img/emily.jpg", id,4
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"james_garcia@gmail.com", "james", "garcia", CURRENT_TIMESTAMP,"$2a$10$rX/75a2KLrgGv3NlfSRlm.K0Y55.4cjdIFqMO1SDM3f1SD4VzZJ96", "src//img/james.jpg", id,4
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"liam_miller@gmail.com", "liam", "miller", CURRENT_TIMESTAMP,"$2a$10$V8PrsAfKNeBAq2A/4mzQ1eKjv3kPwwYjqc3cmIn0u3NBvRUMvbaKa", "src//img/liam.jpg", id,3
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"daniel_davis@gmail.com", "daniel", "davis", CURRENT_TIMESTAMP,"$2a$10$0ynacLoeKVqtLuqixAoUT.xXydEjDbDJNzGDJaxH1D315mwQLZC1y", "src//img/daniel.jpg", id,2
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"elizabeth_rodriguez@gmail.com", "elizabeth", "rodriguez", CURRENT_TIMESTAMP,"$2a$10$uPL/y3OQLuU2OtLKmThI5u5YqEGrw.V413ecQKxJNPCpIajCweHNO", "src//img/elizabeth.jpg", id,1
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"sofia_martinez@gmail.com", "sofia", "martinez", CURRENT_TIMESTAMP,"$2a$10$Mkw5iDfSAW9w/pHB43WIveUEtdmY5C/hcEKq2T8CVOKL1p/xuNaJy", "src//img/sofia.jpg", id,4
FROM somos_mas.role
WHERE name="ROLE_ADMIN";

/*USER*/
INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"elijah_lopez@gmail.com", "elijah", "lopez", CURRENT_TIMESTAMP,"$2a$10$0vrg3Y.Z7ChAnG2cwuhwtuzeGYOegsmjM6ancs/xqotGb9Srj.W1u", "src//img/elijah.jpg", id,10
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"aiden_gonzalez@gmail.com", "aiden", "gonzalez", CURRENT_TIMESTAMP,"$2a$10$BpBfQVOxjwzoKKpIhwnMa.HZth7ooI4vTXR6yGU214Vjcnodr2xnW", "src//img/aiden.jpg", id,9
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"logan_wilson@gmail.com", "logan", "wilson", CURRENT_TIMESTAMP,"$2a$10$wWbPymxp9nS8foK5eN9Q3ewKvs2Ftw.uVdaE4zUX/9w0jWlFlWjU6", "src//img/logan.jpg", id,9
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"grace_anderson@gmail.com", "grace", "anderson", CURRENT_TIMESTAMP,"$2a$10$nl0oRONFFbAnPZhlyaMIBO/LEBfS0EiXYNzEtZQHKtDUBeWNeMsVy", "src//img/grace.jpg", id,9
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"natalie_taylor@gmail.com", "natalie", "taylor", CURRENT_TIMESTAMP,"$2a$10$GKUEwBxi5L5ypP8lZzRGkOvUgRyR7eRRq45ovKZmfPXuuHhzumb0e", "src//img/natalie.jpg", id,7
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"victoria_moore@gmail.com", "victoria", "moore", CURRENT_TIMESTAMP,"$2a$10$J3yQ2IUUnx7YVtaW/0lBTOg2AE0hVbOgaWrrXjRPBRzsCGRr8aceC", "src//img/victoria.jpg", id,6
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"andrew_jackson@gmail.com", "andrew", "jackson", CURRENT_TIMESTAMP,"$2a$10$OWl9f0bzU.x72D.Xqm9yl.I8O0lxb3mzOSDzjPGF4B11sasJ.O6lC", "src//img/andrew.jpg", id,7
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"gabriel_martin@gmail.com", "gabriel", "martin", CURRENT_TIMESTAMP,"$2a$10$W27yvh1uPFVGvrgNAbfH1ua6gB9/rA6TZc4yEYchlcYEk7FtXI6y2", "src//img/gabriel.jpg", id,8
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"homero_thompson@gmail.com", "homero", "thompson", CURRENT_TIMESTAMP,"$2a$10$RvWyU0az6EZIs8TxeHlCh.Va94SrU8n908xoduvO6Ipmuyg2hM2nK", "src//img/homero.jpg", id,7
FROM somos_mas.role
WHERE name="ROLE_USER";

INSERT INTO somos_mas.user(created_at,deleted,deleted_at,email, first_name, last_name, modified_at,password, photo, role_id)
SELECT CURRENT_TIMESTAMP,FALSE,NULL,"felipe_hernandez@gmail.com", "felipe", "hernandez", CURRENT_TIMESTAMP,"$2a$10$IVQSsUvpytGKhSU.vuYVX.XjCeZsfcC/7jcLhqLcXb2iYYOQj90jS", "src//img/felipe.jpg", id,6
FROM somos_mas.role
WHERE name="ROLE_USER";