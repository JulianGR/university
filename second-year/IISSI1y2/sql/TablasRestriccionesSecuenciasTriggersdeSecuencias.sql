-------------------------------------------------------------------------------
--  Scripts de creación de tablas, restricciones, secuencias, triggers asociados a la gestión de secuencias
--   Creado por el grupo formado por:
--      Jesus Gamez Larrad
--      Alvaro Rubia Tapia
--      Javier Navarro Pliego
--      Julian Gomez Rodriguez
-- Para la asignatura de IISI1 de la ETSII. Curso 2018/ 2019
-------------------------------------------------------------------------------

--------------------------------------------------------
--  Borrado de tablas
--------------------------------------------------------
DROP TABLE calificaciones CASCADE CONSTRAINTS;

DROP TABLE asignaturas CASCADE CONSTRAINTS;

DROP TABLE alumnos CASCADE CONSTRAINTS;

DROP TABLE esdestinatario CASCADE CONSTRAINTS;

DROP TABLE clases CASCADE CONSTRAINTS;

DROP TABLE respuestaabiertas CASCADE CONSTRAINTS;

DROP TABLE respuestaescalas CASCADE CONSTRAINTS;

DROP TABLE preguntasabierta CASCADE CONSTRAINTS;

DROP TABLE preguntasescala CASCADE CONSTRAINTS;

DROP TABLE completa CASCADE CONSTRAINTS;

DROP TABLE encuestas CASCADE CONSTRAINTS;

DROP TABLE avisos CASCADE CONSTRAINTS;

DROP TABLE usuarioprofesores CASCADE CONSTRAINTS;

DROP TABLE usuariofamilias CASCADE CONSTRAINTS;

--------------------------------------------------------
--  Creación de tablas
--------------------------------------------------------

CREATE TABLE usuariofamilias (
    oid_uf         NUMBER(12,2),
    nick           VARCHAR2(63) NOT NULL UNIQUE,
    contrasenya    VARCHAR2(63) NOT NULL,
    nombre_uf      VARCHAR2(63) NOT NULL,
    apellidos_uf   VARCHAR2(63) NOT NULL,
    email_uf       VARCHAR2(255) NOT NULL UNIQUE,
    PRIMARY KEY ( oid_uf )
);

CREATE TABLE usuarioprofesores (
    oid_up         NUMBER(12,2),
    nick           VARCHAR2(63) NOT NULL UNIQUE,
    contrasenya    VARCHAR2(63) NOT NULL,
    nombre_up      VARCHAR2(63) NOT NULL,
    apellidos_up   VARCHAR2(63) NOT NULL,
    email_up       VARCHAR2(255) NOT NULL UNIQUE,
    estutor        NUMBER(1,0) NOT NULL,
    fotourl        VARCHAR2(511),
    PRIMARY KEY ( oid_up )
);

CREATE TABLE avisos (
    oid_av             NUMBER(12,2),
    titulo_av          VARCHAR2(255) NOT NULL,
    cuerpo             VARCHAR2(1023) NOT NULL,
    archivourl         VARCHAR2(511),
    fechasuceso        DATE,
    fechapublicacion   DATE NOT NULL,
    oid_up             NUMBER(12,2) NOT NULL,
    CONSTRAINT fechasucesoanteriorafechapub CHECK ( fechapublicacion < fechasuceso ),
    PRIMARY KEY ( oid_av ),
    FOREIGN KEY ( oid_up )
        REFERENCES usuarioprofesores
            ON DELETE SET NULL --on update cascade
);


CREATE TABLE clases (
    oid_cl   NUMBER(12,2),
    curso    NUMBER(1) NOT NULL,
    grupo    VARCHAR2(1) NOT NULL,
    oid_up   NUMBER(12,2) NOT NULL,
    CONSTRAINT cursofueralimite CHECK ( curso >= 1
                                        AND curso <= 6 ),
    CONSTRAINT cursoneg CHECK ( curso > 0 ),
    PRIMARY KEY ( oid_cl ),
    FOREIGN KEY ( oid_up )
        REFERENCES usuarioprofesores
            ON DELETE SET NULL --on update cascade
);


CREATE TABLE alumnos (
    oid_al            NUMBER(12,2),
    nombre_al         VARCHAR2(63) NOT NULL,
    apellidos_al      VARCHAR2(63) NOT NULL,
    fechanacimiento   DATE NOT NULL,
    oid_uf            NUMBER(12,2) NOT NULL,
    oid_cl            NUMBER(12,2) NOT NULL,
    PRIMARY KEY ( oid_al ),
    FOREIGN KEY ( oid_uf )
        REFERENCES usuariofamilias
            ON DELETE CASCADE,
    FOREIGN KEY ( oid_cl )
        REFERENCES clases
            ON DELETE SET NULL --on update cascade
);

CREATE TABLE asignaturas (
    oid_as      NUMBER(12,2),
    nombre_as   VARCHAR2(63) NOT NULL,
    oid_up      NUMBER(12,2) NOT NULL,
    oid_cl      NUMBER(12,2) NOT NULL,
    PRIMARY KEY ( oid_as ),
    FOREIGN KEY ( oid_up )
        REFERENCES usuarioprofesores
            ON DELETE SET NULL,
    FOREIGN KEY ( oid_cl )
        REFERENCES clases
            ON DELETE SET NULL --on update cascade
);

CREATE TABLE calificaciones (
    oid_ca         NUMBER(12,2),
    valor_ca       NUMBER(12,2) NOT NULL,
    convocatoria   VARCHAR2(63) NOT NULL,
    oid_al         NUMBER(12,2) NOT NULL,
    oid_as         NUMBER(12,2) NOT NULL,
    CONSTRAINT valorfueralimite CHECK ( valor_ca >= 0
                                        AND valor_ca <= 10 ),
    PRIMARY KEY ( oid_ca ),
    FOREIGN KEY ( oid_al )
        REFERENCES alumnos
            ON DELETE CASCADE,
    FOREIGN KEY ( oid_as )
        REFERENCES asignaturas
            ON DELETE CASCADE --on update cascade
);

--------------------------------------------------------
--  Borrado de secuencias
--------------------------------------------------------

DROP SEQUENCE sec_uf;

DROP SEQUENCE sec_up;

DROP SEQUENCE sec_ed;

DROP SEQUENCE sec_av;

DROP SEQUENCE sec_cl;

DROP SEQUENCE sec_al;

DROP SEQUENCE sec_as;

DROP SEQUENCE sec_ca;

--------------------------------------------------------
--  Creación de secuencias
--------------------------------------------------------

CREATE SEQUENCE sec_uf MINVALUE 0 INCREMENT BY 1 START WITH 0;

CREATE SEQUENCE sec_up MINVALUE 0 INCREMENT BY 1 START WITH 0;

CREATE SEQUENCE sec_ed MINVALUE 0 INCREMENT BY 1 START WITH 0;

CREATE SEQUENCE sec_av MINVALUE 0 INCREMENT BY 1 START WITH 0;

CREATE SEQUENCE sec_cl MINVALUE 0 INCREMENT BY 1 START WITH 0;

CREATE SEQUENCE sec_al MINVALUE 0 INCREMENT BY 1 START WITH 0;

CREATE SEQUENCE sec_as MINVALUE 0 INCREMENT BY 1 START WITH 0;

CREATE SEQUENCE sec_ca MINVALUE 0 INCREMENT BY 1 START WITH 0;

--------------------------------------------------------
--  Creación de triggers de secuencia
--------------------------------------------------------

CREATE OR REPLACE TRIGGER secuencia_usuariofamilias BEFORE
    INSERT ON usuariofamilias
    FOR EACH ROW
BEGIN
    IF :new.oid_uf IS NULL THEN
        SELECT
            sec_uf.NEXTVAL
        INTO :new.oid_uf
        FROM
            dual;

    END IF;
END;
/

ALTER TRIGGER secuencia_usuariofamilias ENABLE;

CREATE OR REPLACE TRIGGER secuencia_usuarioprofesores BEFORE
    INSERT ON usuarioprofesores
    FOR EACH ROW
BEGIN
    IF :new.oid_up IS NULL THEN
        SELECT
            sec_up.NEXTVAL
        INTO :new.oid_up
        FROM
            dual;

    END IF;
END;
/

ALTER TRIGGER secuencia_usuarioprofesores ENABLE;

CREATE OR REPLACE TRIGGER secuencia_avisos BEFORE
    INSERT ON avisos
    FOR EACH ROW
BEGIN
    IF :new.oid_av IS NULL THEN
        SELECT
            sec_av.NEXTVAL
        INTO :new.oid_av
        FROM
            dual;

    END IF;
END;
/

ALTER TRIGGER secuencia_avisos ENABLE;

CREATE OR REPLACE TRIGGER secuencia_clases BEFORE
    INSERT ON clases
    FOR EACH ROW
BEGIN
    IF :new.oid_cl IS NULL THEN
        SELECT
            sec_cl.NEXTVAL
        INTO :new.oid_cl
        FROM
            dual;

    END IF;
END;
/

ALTER TRIGGER secuencia_clases ENABLE;

CREATE OR REPLACE TRIGGER secuencia_alumnos BEFORE
    INSERT ON alumnos
    FOR EACH ROW
BEGIN
    IF :new.oid_al IS NULL THEN
        SELECT
            sec_al.NEXTVAL
        INTO :new.oid_al
        FROM
            dual;

    END IF;
END;
/

ALTER TRIGGER secuencia_alumnos ENABLE;

CREATE OR REPLACE TRIGGER secuencia_asignaturas BEFORE
    INSERT ON asignaturas
    FOR EACH ROW
BEGIN
    IF :new.oid_as IS NULL THEN
        SELECT
            sec_as.NEXTVAL
        INTO :new.oid_as
        FROM
            dual;

    END IF;
END;
/

ALTER TRIGGER secuencia_asignaturas ENABLE;

CREATE OR REPLACE TRIGGER secuencia_calificaciones BEFORE
    INSERT ON calificaciones
    FOR EACH ROW
BEGIN
    IF :new.oid_ca IS NULL THEN
        SELECT
            sec_ca.NEXTVAL
        INTO :new.oid_ca
        FROM
            dual;

    END IF;
END;
/

ALTER TRIGGER secuencia_calificaciones ENABLE;