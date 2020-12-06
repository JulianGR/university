-------------------------------------------------------------------------------
--  Script de pruebas, que deben cubrir todo el c�digo desarrollado
--   Creado por el grupo formado por:
--      Jesus Gamez Larrad
--      Alvaro Rubia Tapia
--      Javier Navarro Pliego
--      Julian Gomez Rodriguez
-- Para la asignatura de IISI1 de la ETSII. Curso 2018/ 2019
-------------------------------------------------------------------------------
SET SERVEROUTPUT ON

CREATE OR REPLACE FUNCTION assert_equals (
    salida BOOLEAN,
    salida_esperada BOOLEAN
) RETURN VARCHAR2 AS
BEGIN
    IF ( salida = salida_esperada ) THEN
        RETURN 'EXITO';
    ELSE
        RETURN 'FALLO';
    END IF;
END assert_equals;
/

-------------------------------------------------------------------------------
--Cabeceras de packages
-------------------------------------------------------------------------------

CREATE OR REPLACE PACKAGE paquete_usuariofamilias IS
    PROCEDURE inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_nick           IN usuariofamilias.nick%TYPE,
        w_contrasenya    IN usuariofamilias.contrasenya%TYPE,
        w_nombre_uf      IN usuariofamilias.nombre_uf%TYPE,
        w_apellidos_uf   IN usuariofamilias.apellidos_uf%TYPE,
        w_email_uf       IN usuariofamilias.email_uf%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_uf         IN usuariofamilias.oid_uf%TYPE,
        w_nick           IN usuariofamilias.nick%TYPE,
        w_contrasenya    IN usuariofamilias.contrasenya%TYPE,
        w_nombre_uf      IN usuariofamilias.nombre_uf%TYPE,
        w_apellidos_uf   IN usuariofamilias.apellidos_uf%TYPE,
        w_email_uf       IN usuariofamilias.email_uf%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_uf         IN usuariofamilias.oid_uf%TYPE,
        salidaesperada   BOOLEAN
    );

END paquete_usuariofamilias;
/

CREATE OR REPLACE PACKAGE paquete_usuarioprofesores IS
    PROCEDURE inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_nick           IN usuarioprofesores.nick%TYPE,
        w_contrasenya    IN usuarioprofesores.contrasenya%TYPE,
        w_nombre_up      IN usuarioprofesores.nombre_up%TYPE,
        w_apellidos_up   IN usuarioprofesores.apellidos_up%TYPE,
        w_email_up       IN usuarioprofesores.email_up%TYPE,
        w_estutor        IN usuarioprofesores.estutor%TYPE,
        w_fotourl        IN usuarioprofesores.fotourl%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_up         IN usuarioprofesores.oid_up%TYPE,
        w_nick           IN usuarioprofesores.nick%TYPE,
        w_contrasenya    IN usuarioprofesores.contrasenya%TYPE,
        w_nombre_up      IN usuarioprofesores.nombre_up%TYPE,
        w_apellidos_up   IN usuarioprofesores.apellidos_up%TYPE,
        w_email_up       IN usuarioprofesores.email_up%TYPE,
        w_estutor        IN usuarioprofesores.estutor%TYPE,
        w_fotourl        IN usuarioprofesores.fotourl%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_up         IN usuarioprofesores.oid_up%TYPE,
        salidaesperada   BOOLEAN
    );

END;
/


CREATE OR REPLACE PACKAGE paquete_avisos AS
    PROCEDURE inicializar;

    PROCEDURE insertar (
        nombreprueba         VARCHAR2,
        w_oid_up             IN avisos.oid_up%TYPE,
        w_titulo_av          IN avisos.titulo_av%TYPE,
        w_cuerpo             IN avisos.cuerpo%TYPE,
        w_archivourl         IN avisos.archivourl%TYPE,
        w_fechasuceso        IN avisos.fechasuceso%TYPE,
        w_fechapublicacion   IN avisos.fechapublicacion%TYPE,
        salidaesperada       BOOLEAN
    );

    PROCEDURE actualizar (
        nombreprueba         VARCHAR2,
        w_oid_av             IN avisos.oid_av%TYPE,
        w_oid_up             IN avisos.oid_up%TYPE,
        w_titulo_av          IN avisos.titulo_av%TYPE,
        w_cuerpo             IN avisos.cuerpo%TYPE,
        w_archivourl         IN avisos.archivourl%TYPE,
        w_fechasuceso        IN avisos.fechasuceso%TYPE,
        w_fechapublicacion   IN avisos.fechapublicacion%TYPE,
        salidaesperada       BOOLEAN
    );

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_av         IN avisos.oid_av%TYPE,
        salidaesperada   BOOLEAN
    );

END paquete_avisos;
/

CREATE OR REPLACE PACKAGE paquete_alumnos AS
    PROCEDURE inicializar;

    PROCEDURE insertar (
        nombreprueba        VARCHAR2,
        w_oid_uf            IN alumnos.oid_uf%TYPE,
        w_oid_cl            IN alumnos.oid_cl%TYPE,
        w_nombre_al         IN alumnos.nombre_al%TYPE,
        w_apellidos_al      IN alumnos.apellidos_al%TYPE,
        w_fechanacimiento   IN alumnos.fechanacimiento%TYPE,
        salidaesperada      BOOLEAN
    );

    PROCEDURE actualizar (
        nombreprueba        VARCHAR2,
        w_oid_al            IN alumnos.oid_al%TYPE,
        w_oid_uf            IN alumnos.oid_uf%TYPE,
        w_oid_cl            IN alumnos.oid_cl%TYPE,
        w_nombre_al         IN alumnos.nombre_al%TYPE,
        w_apellidos_al      IN alumnos.apellidos_al%TYPE,
        w_fechanacimiento   IN alumnos.fechanacimiento%TYPE,
        salidaesperada      BOOLEAN
    );

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_al         IN alumnos.oid_al%TYPE,
        salidaesperada   BOOLEAN
    );

END paquete_alumnos;
/

CREATE OR REPLACE PACKAGE paquete_clases AS
    PROCEDURE inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_curso          IN clases.curso%TYPE,
        w_grupo          IN clases.grupo%TYPE,
        w_oid_up         IN clases.oid_up%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_cl         IN clases.oid_cl%TYPE,
        w_curso          IN clases.curso%TYPE,
        w_grupo          IN clases.grupo%TYPE,
        w_oid_up         IN clases.oid_up%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_cl         IN clases.oid_cl%TYPE,
        salidaesperada   BOOLEAN
    );

END paquete_clases;
/

CREATE OR REPLACE PACKAGE paquete_asignaturas AS
    PROCEDURE inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_nombre_as      IN asignaturas.nombre_as%TYPE,
        w_oid_up         IN asignaturas.oid_up%TYPE,
        w_oid_cl         IN asignaturas.oid_cl%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_as         IN asignaturas.oid_as%TYPE,
        w_nombre_as      IN asignaturas.nombre_as%TYPE,
        w_oid_up         IN asignaturas.oid_up%TYPE,
        w_oid_cl         IN asignaturas.oid_cl%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_as         IN asignaturas.oid_as%TYPE,
        salidaesperada   BOOLEAN
    );

END paquete_asignaturas;
/

CREATE OR REPLACE PACKAGE paquete_calificaciones AS
    PROCEDURE inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_valor_ca       IN calificaciones.valor_ca%TYPE,
        w_convocatoria   IN calificaciones.convocatoria%TYPE,
        w_oid_al         IN calificaciones.oid_al%TYPE,
        w_oid_as         IN calificaciones.oid_as%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_ca         IN calificaciones.oid_ca%TYPE,
        w_valor_ca       IN calificaciones.valor_ca%TYPE,
        w_convocatoria   IN calificaciones.convocatoria%TYPE,
        w_oid_al         IN calificaciones.oid_al%TYPE,
        w_oid_as         IN calificaciones.oid_as%TYPE,
        salidaesperada   BOOLEAN
    );

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_ca         IN calificaciones.oid_ca%TYPE,
        salidaesperada   BOOLEAN
    );

END paquete_calificaciones;
/

--=============================================================================================================


CREATE OR REPLACE PACKAGE BODY paquete_usuariofamilias IS

    PROCEDURE inicializar IS
    BEGIN
        DELETE FROM usuariofamilias;

    END inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_nick           IN usuariofamilias.nick%TYPE,
        w_contrasenya    IN usuariofamilias.contrasenya%TYPE,
        w_nombre_uf      IN usuariofamilias.nombre_uf%TYPE,
        w_apellidos_uf   IN usuariofamilias.apellidos_uf%TYPE,
        w_email_uf       IN usuariofamilias.email_uf%TYPE,
        salidaesperada   BOOLEAN
    ) IS
        salida           BOOLEAN := true;
        usuariofamilia   usuariofamilias%rowtype;
        w_oid_uf         NUMBER(12,2);
    BEGIN
        INSERT INTO usuariofamilias VALUES (
            sec_uf.NEXTVAL,
            w_nick,
            w_contrasenya,
            w_nombre_uf,
            w_apellidos_uf,
            w_email_uf
        );

        w_oid_uf := sec_uf.currval;
        SELECT
            *
        INTO usuariofamilia
        FROM
            usuariofamilias
        WHERE
            oid_uf = w_oid_uf;

        IF ( usuariofamilia.nick <> w_nick OR usuariofamilia.contrasenya <> w_contrasenya OR usuariofamilia.nombre_uf <> w_nombre_uf

        OR usuariofamilia.apellidos_uf <> w_apellidos_uf OR usuariofamilia.email_uf <> w_email_uf ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ':'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END insertar;

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_uf         IN usuariofamilias.oid_uf%TYPE,
        w_nick           IN usuariofamilias.nick%TYPE,
        w_contrasenya    IN usuariofamilias.contrasenya%TYPE,
        w_nombre_uf      IN usuariofamilias.nombre_uf%TYPE,
        w_apellidos_uf   IN usuariofamilias.apellidos_uf%TYPE,
        w_email_uf       IN usuariofamilias.email_uf%TYPE,
        salidaesperada   BOOLEAN
    ) IS
        salida           BOOLEAN := true;
        usuariofamilia   usuariofamilias%rowtype;
    BEGIN
        UPDATE usuariofamilias
        SET
            nick = w_nick,
            contrasenya = w_contrasenya,
            nombre_uf = w_nombre_uf,
            apellidos_uf = w_apellidos_uf,
            email_uf = w_email_uf
        WHERE
            oid_uf = w_oid_uf;

        SELECT
            *
        INTO usuariofamilia
        FROM
            usuariofamilias
        WHERE
            oid_uf = w_oid_uf;

        IF usuariofamilia.nick <> w_nick OR usuariofamilia.contrasenya <> w_contrasenya OR usuariofamilia.nombre_uf <> w_nombre_uf

        OR usuariofamilia.apellidos_uf <> w_apellidos_uf OR usuariofamilia.email_uf <> w_email_uf THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ': '
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ': '
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END actualizar;

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_uf         IN usuariofamilias.oid_uf%TYPE,
        salidaesperada   BOOLEAN
    ) IS
        salida              BOOLEAN := true;
        n_usuariofamilias   INTEGER;
    BEGIN
        DELETE FROM usuariofamilias
        WHERE
            oid_uf = w_oid_uf;

        SELECT
            COUNT(*)
        INTO n_usuariofamilias
        FROM
            usuariofamilias
        WHERE
            oid_uf = w_oid_uf;

        IF ( n_usuariofamilias <> 0 ) THEN
            salida := false;
        END IF;
        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ';'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END eliminar;

END;
/

CREATE OR REPLACE PACKAGE BODY paquete_usuarioprofesores IS

    PROCEDURE inicializar IS
    BEGIN
        DELETE FROM usuarioprofesores;

    END inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_nick           IN usuarioprofesores.nick%TYPE,
        w_contrasenya    IN usuarioprofesores.contrasenya%TYPE,
        w_nombre_up      IN usuarioprofesores.nombre_up%TYPE,
        w_apellidos_up   IN usuarioprofesores.apellidos_up%TYPE,
        w_email_up       IN usuarioprofesores.email_up%TYPE,
        w_estutor        IN usuarioprofesores.estutor%TYPE,
        w_fotourl        IN usuarioprofesores.fotourl%TYPE,
        salidaesperada   BOOLEAN
    ) IS
        salida            BOOLEAN := true;
        usuarioprofesor   usuarioprofesores%rowtype;
        w_oid_up          NUMBER(12,2);
    BEGIN
        INSERT INTO usuarioprofesores VALUES (
            sec_up.NEXTVAL,
            w_nick,
            w_contrasenya,
            w_nombre_up,
            w_apellidos_up,
            w_email_up,
            w_estutor,
            w_fotourl
        );

        w_oid_up := sec_up.currval;
        SELECT
            *
        INTO usuarioprofesor
        FROM
            usuarioprofesores
        WHERE
            oid_up = w_oid_up;

        IF ( usuarioprofesor.nick <> w_nick OR usuarioprofesor.contrasenya <> w_contrasenya OR usuarioprofesor.nombre_up <> w_nombre_up

        OR usuarioprofesor.apellidos_up <> w_apellidos_up OR usuarioprofesor.email_up <> w_email_up OR usuarioprofesor.estutor <> w_estutor OR usuarioprofesor.fotourl <> w_fotourl ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ':'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END insertar;

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_up         IN usuarioprofesores.oid_up%TYPE,
        w_nick           IN usuarioprofesores.nick%TYPE,
        w_contrasenya    IN usuarioprofesores.contrasenya%TYPE,
        w_nombre_up      IN usuarioprofesores.nombre_up%TYPE,
        w_apellidos_up   IN usuarioprofesores.apellidos_up%TYPE,
        w_email_up       IN usuarioprofesores.email_up%TYPE,
        w_estutor        IN usuarioprofesores.estutor%TYPE,
        w_fotourl        IN usuarioprofesores.fotourl%TYPE,
        salidaesperada   BOOLEAN
    ) IS
        salida            BOOLEAN := true;
        usuarioprofesor   usuarioprofesores%rowtype;
    BEGIN
        UPDATE usuarioprofesores
        SET
            nick = w_nick,
            contrasenya = w_contrasenya,
            nombre_up = w_nombre_up,
            apellidos_up = w_apellidos_up,
            email_up = w_email_up,
            estutor = w_estutor,
            fotourl = w_fotourl
        WHERE
            oid_up = w_oid_up;

        SELECT
            *
        INTO usuarioprofesor
        FROM
             usuarioprofesores
        WHERE
            oid_up = w_oid_up;

        IF ( usuarioprofesor.nick <> w_nick OR usuarioprofesor.contrasenya <> w_contrasenya OR usuarioprofesor.nombre_up <> w_nombre_up

        OR usuarioprofesor.apellidos_up <> w_apellidos_up OR usuarioprofesor.email_up <> w_email_up OR usuarioprofesor.estutor <> w_estutor OR usuarioprofesor.fotourl <> w_fotourl ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ': '
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ': '
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END actualizar;

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_up         IN usuarioprofesores.oid_up%TYPE,
        salidaesperada   BOOLEAN
    ) IS
        salida                BOOLEAN := true;
        n_usuarioprofesores   INTEGER;
    BEGIN
        DELETE FROM usuarioprofesores
        WHERE
            oid_up = w_oid_up;

        SELECT
            COUNT(*)
        INTO n_usuarioprofesores
        FROM
            usuarioprofesores
        WHERE
            oid_up = w_oid_up;

        IF ( n_usuarioprofesores <> 0 ) THEN
            salida := false;
        END IF;
        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ';'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END eliminar;

END;
/

CREATE OR REPLACE PACKAGE BODY paquete_avisos AS

    PROCEDURE inicializar AS
    BEGIN
        DELETE FROM avisos;

    END inicializar;

    PROCEDURE insertar (
        nombreprueba         VARCHAR2,
        w_oid_up             IN avisos.oid_up%TYPE,
        w_titulo_av          IN avisos.titulo_av%TYPE,
        w_cuerpo             IN avisos.cuerpo%TYPE,
        w_archivourl         IN avisos.archivourl%TYPE,
        w_fechasuceso        IN avisos.fechasuceso%TYPE,
        w_fechapublicacion   IN avisos.fechapublicacion%TYPE,
        salidaesperada       BOOLEAN
    ) AS
        salida     BOOLEAN := true;
        aviso      avisos%rowtype;
        w_oid_av   NUMBER(12,2);
    BEGIN
        INSERT INTO avisos VALUES (
            sec_av.NEXTVAL,
            w_titulo_av,
            w_cuerpo,
            w_archivourl,
            w_fechasuceso,
            w_fechapublicacion,
            w_oid_up
        );

        w_oid_av := sec_av.currval;
        SELECT
            *
        INTO aviso
        FROM
            avisos
        WHERE
            oid_av = w_oid_av;

        IF ( aviso.oid_up <> w_oid_up OR aviso.titulo_av <> w_titulo_av OR aviso.cuerpo <> w_cuerpo OR aviso.archivourl <> w_archivourl

        OR aviso.fechasuceso <> w_fechasuceso OR aviso.fechapublicacion <> w_fechapublicacion ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ':'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END insertar;

    PROCEDURE actualizar (
        nombreprueba         VARCHAR2,
        w_oid_av             IN avisos.oid_av%TYPE,
        w_oid_up             IN avisos.oid_up%TYPE,
        w_titulo_av          IN avisos.titulo_av%TYPE,
        w_cuerpo             IN avisos.cuerpo%TYPE,
        w_archivourl         IN avisos.archivourl%TYPE,
        w_fechasuceso        IN avisos.fechasuceso%TYPE,
        w_fechapublicacion   IN avisos.fechapublicacion%TYPE,
        salidaesperada       BOOLEAN
    ) AS
        salida   BOOLEAN := true;
        aviso    avisos%rowtype;
    BEGIN
        UPDATE avisos
        SET
            titulo_av = w_titulo_av,
            cuerpo = w_cuerpo,
            archivourl = w_archivourl,
            fechasuceso = w_fechasuceso,
            fechapublicacion = w_fechapublicacion,
            oid_up = w_oid_up
        WHERE
            oid_av = w_oid_av;

        SELECT
            *
        INTO aviso
        FROM
            avisos
        WHERE
            oid_av = w_oid_av;

        IF ( aviso.titulo_av <> w_titulo_av OR aviso.cuerpo <> w_cuerpo OR aviso.archivourl <> w_archivourl OR aviso.fechasuceso

        <> w_fechasuceso OR aviso.fechapublicacion <> w_fechapublicacion OR aviso.oid_up <> w_oid_up ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ': '
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ': '
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END actualizar;

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_av         IN avisos.oid_av%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida     BOOLEAN := true;
        n_avisos   INTEGER;
    BEGIN
        DELETE FROM avisos
        WHERE
            oid_av = w_oid_av;

        SELECT
            COUNT(*)
        INTO n_avisos
        FROM
            avisos
        WHERE
            oid_av = w_oid_av;

        IF ( n_avisos <> 0 ) THEN
            salida := false;
        END IF;
        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ';'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END eliminar;

END;
/

CREATE OR REPLACE PACKAGE BODY paquete_alumnos AS

    PROCEDURE inicializar AS
    BEGIN
        DELETE FROM alumnos;

    END inicializar;

    PROCEDURE insertar (
        nombreprueba        VARCHAR2,
        w_oid_uf            IN alumnos.oid_uf%TYPE,
        w_oid_cl            IN alumnos.oid_cl%TYPE,
        w_nombre_al         IN alumnos.nombre_al%TYPE,
        w_apellidos_al      IN alumnos.apellidos_al%TYPE,
        w_fechanacimiento   IN alumnos.fechanacimiento%TYPE,
        salidaesperada      BOOLEAN
    ) AS
        salida     BOOLEAN := true;
        alumno     alumnos%rowtype;
        w_oid_al   NUMBER(12,2);
    BEGIN
        INSERT INTO alumnos VALUES (
            sec_al.NEXTVAL,
            w_nombre_al,
            w_apellidos_al,
            w_fechanacimiento,
            w_oid_uf,
            w_oid_cl
        );

        w_oid_al := sec_al.currval;
        SELECT
            *
        INTO alumno
        FROM
            alumnos
        WHERE
            oid_al = w_oid_al;

        IF ( alumno.oid_uf <> w_oid_uf OR alumno.oid_cl <> w_oid_cl OR alumno.nombre_al <> w_nombre_al OR alumno.apellidos_al <>

        w_apellidos_al OR alumno.fechanacimiento <> w_fechanacimiento ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ':'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END insertar;

    PROCEDURE actualizar (
        nombreprueba        VARCHAR2,
        w_oid_al            IN alumnos.oid_al%TYPE,
        w_oid_uf            IN alumnos.oid_uf%TYPE,
        w_oid_cl            IN alumnos.oid_cl%TYPE,
        w_nombre_al         IN alumnos.nombre_al%TYPE,
        w_apellidos_al      IN alumnos.apellidos_al%TYPE,
        w_fechanacimiento   IN alumnos.fechanacimiento%TYPE,
        salidaesperada      BOOLEAN
    ) AS
        salida   BOOLEAN := true;
        alumno   alumnos%rowtype;
    BEGIN
        UPDATE alumnos
        SET
            oid_uf = w_oid_uf,
            oid_cl = w_oid_cl,
            nombre_al = w_nombre_al,
            apellidos_al = w_apellidos_al,
            fechanacimiento = w_fechanacimiento
        WHERE
            oid_al = w_oid_al;

        SELECT
            *
        INTO alumno
        FROM
            alumnos
        WHERE
            oid_al = w_oid_al;

        IF alumno.oid_uf <> w_oid_uf OR alumno.oid_cl <> w_oid_cl OR alumno.nombre_al <> w_nombre_al OR alumno.apellidos_al <> w_apellidos_al

        OR alumno.fechanacimiento <> w_fechanacimiento THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ': '
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ': '
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END actualizar;

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_al         IN alumnos.oid_al%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida      BOOLEAN := true;
        n_alumnos   INTEGER;
    BEGIN
        DELETE FROM alumnos
        WHERE
            oid_al = w_oid_al;

        SELECT
            COUNT(*)
        INTO n_alumnos
        FROM
            alumnos
        WHERE
            oid_al = w_oid_al;

        IF ( n_alumnos <> 0 ) THEN
            salida := false;
        END IF;
        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ';'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END eliminar;

END;
/

CREATE OR REPLACE PACKAGE BODY paquete_clases AS

    PROCEDURE inicializar AS
    BEGIN
        DELETE FROM clases;

    END inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_curso          IN clases.curso%TYPE,
        w_grupo          IN clases.grupo%TYPE,
        w_oid_up         IN clases.oid_up%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida     BOOLEAN := true;
        clase      clases%rowtype;
        w_oid_cl   NUMBER(12,2);
    BEGIN
        INSERT INTO clases VALUES (
            sec_cl.NEXTVAL,
            w_curso,
            w_grupo,
            w_oid_up
        );

        w_oid_cl := sec_cl.currval;
        SELECT
            *
        INTO clase
        FROM
            clases
        WHERE
            oid_cl = w_oid_cl;

        IF ( clase.oid_up <> w_oid_up OR clase.curso <> w_curso OR clase.grupo <> w_grupo ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ':'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END insertar;

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_cl         IN clases.oid_cl%TYPE,
        w_curso          IN clases.curso%TYPE,
        w_grupo          IN clases.grupo%TYPE,
        w_oid_up         IN clases.oid_up%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida   BOOLEAN := true;
        clase    clases%rowtype;
    BEGIN
        UPDATE clases
        SET
            curso = w_curso,
            grupo = w_grupo,
            oid_up = w_oid_up
        WHERE
            oid_cl = w_oid_cl;

        SELECT
            *
        INTO clase
        FROM
            clases
        WHERE
            oid_cl = w_oid_cl;

        IF clase.oid_up <> w_oid_up OR clase.curso <> w_curso OR clase.grupo <> w_grupo THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ': '
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ': '
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END actualizar;

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_cl         IN clases.oid_cl%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida     BOOLEAN := true;
        n_clases   INTEGER;
    BEGIN
        DELETE FROM clases
        WHERE
            oid_cl = w_oid_cl;

        SELECT
            COUNT(*)
        INTO n_clases
        FROM
            clases
        WHERE
            oid_cl = w_oid_cl;

        IF ( n_clases <> 0 ) THEN
            salida := false;
        END IF;
        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ';'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END eliminar;

END;
/

CREATE OR REPLACE PACKAGE BODY paquete_asignaturas AS

    PROCEDURE inicializar AS
    BEGIN
        DELETE FROM asignaturas;

    END inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_nombre_as      IN asignaturas.nombre_as%TYPE,
        w_oid_up         IN asignaturas.oid_up%TYPE,
        w_oid_cl         IN asignaturas.oid_cl%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida       BOOLEAN := true;
        asignatura   asignaturas%rowtype;
        w_oid_as     NUMBER(12,2);
    BEGIN
        INSERT INTO asignaturas VALUES (
            sec_as.NEXTVAL,
            w_nombre_as,
            w_oid_up,
            w_oid_cl
        );

        w_oid_as := sec_as.currval;
        SELECT
            *
        INTO asignatura
        FROM
            asignaturas
        WHERE
            oid_as = w_oid_as;

        IF ( asignatura.oid_up <> w_oid_up OR asignatura.oid_cl <> w_oid_cl OR asignatura.nombre_as <> w_nombre_as ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ':'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END insertar;

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_as         IN asignaturas.oid_as%TYPE,
        w_nombre_as      IN asignaturas.nombre_as%TYPE,
        w_oid_up         IN asignaturas.oid_up%TYPE,
        w_oid_cl         IN asignaturas.oid_cl%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida       BOOLEAN := true;
        asignatura   asignaturas%rowtype;
    BEGIN
        UPDATE asignaturas
        SET
            nombre_as = w_nombre_as,
            oid_up = w_oid_up,
            oid_cl = w_oid_cl
        WHERE
            oid_as = w_oid_as;

        SELECT
            *
        INTO asignatura
        FROM
            asignaturas
        WHERE
            oid_as = w_oid_as;

        IF asignatura.oid_up <> w_oid_up OR asignatura.oid_cl <> w_oid_cl OR asignatura.nombre_as <> w_nombre_as THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ': '
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ': '
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END actualizar;

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_as         IN asignaturas.oid_as%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida          BOOLEAN := true;
        n_asignaturas   INTEGER;
    BEGIN
        DELETE FROM asignaturas
        WHERE
            oid_as = w_oid_as;

        SELECT
            COUNT(*)
        INTO n_asignaturas
        FROM
            asignaturas
        WHERE
            oid_as = w_oid_as;

        IF ( n_asignaturas <> 0 ) THEN
            salida := false;
        END IF;
        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ';'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END eliminar;

END;
/

CREATE OR REPLACE PACKAGE BODY paquete_calificaciones AS

    PROCEDURE inicializar AS
    BEGIN
        DELETE FROM calificaciones;

    END inicializar;

    PROCEDURE insertar (
        nombreprueba     VARCHAR2,
        w_valor_ca       IN calificaciones.valor_ca%TYPE,
        w_convocatoria   IN calificaciones.convocatoria%TYPE,
        w_oid_al         IN calificaciones.oid_al%TYPE,
        w_oid_as         IN calificaciones.oid_as%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida         BOOLEAN := true;
        calificacion   calificaciones%rowtype;
        w_oid_ca       NUMBER(12,2);
    BEGIN
        INSERT INTO calificaciones VALUES (
            sec_ca.NEXTVAL,
            w_valor_ca,
            w_convocatoria,
            w_oid_al,
            w_oid_as
        );

        w_oid_ca := sec_ca.currval;
        SELECT
            *
        INTO calificacion
        FROM
            calificaciones
        WHERE
            oid_ca = w_oid_ca;

        IF ( calificacion.oid_al <> w_oid_al OR calificacion.oid_as <> w_oid_as OR calificacion.valor_ca <> w_valor_ca OR calificacion

        .convocatoria <> w_convocatoria ) THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ':'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END insertar;

    PROCEDURE actualizar (
        nombreprueba     VARCHAR2,
        w_oid_ca         IN calificaciones.oid_ca%TYPE,
        w_valor_ca       IN calificaciones.valor_ca%TYPE,
        w_convocatoria   IN calificaciones.convocatoria%TYPE,
        w_oid_al         IN calificaciones.oid_al%TYPE,
        w_oid_as         IN calificaciones.oid_as%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida         BOOLEAN := true;
        calificacion   calificaciones%rowtype;
    BEGIN
        UPDATE calificaciones
        SET
            valor_ca = w_valor_ca,
            convocatoria = w_convocatoria,
            oid_al = w_oid_al,
            oid_as = w_oid_as
        WHERE
            oid_ca = w_oid_ca;

        SELECT
            *
        INTO calificacion
        FROM
            calificaciones
        WHERE
            oid_ca = w_oid_ca;

        IF calificacion.oid_al <> w_oid_al OR calificacion.oid_as <> w_oid_as OR calificacion.valor_ca <> w_valor_ca OR calificacion

        .convocatoria <> w_convocatoria THEN
            salida := false;
        END IF;

        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ': '
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ': '
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END actualizar;

    PROCEDURE eliminar (
        nombreprueba     VARCHAR2,
        w_oid_ca         IN calificaciones.oid_ca%TYPE,
        salidaesperada   BOOLEAN
    ) AS
        salida             BOOLEAN := true;
        n_calificaciones   INTEGER;
    BEGIN
        DELETE FROM calificaciones
        WHERE
            oid_ca = w_oid_ca;

        SELECT
            COUNT(*)
        INTO n_calificaciones
        FROM
            calificaciones
        WHERE
            oid_ca = w_oid_ca;

        IF ( n_calificaciones <> 0 ) THEN
            salida := false;
        END IF;
        COMMIT WORK;
        dbms_output.put_line(nombreprueba
                               || ':'
                               || assert_equals(salida,salidaesperada) );
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(nombreprueba
                                   || ';'
                                   || assert_equals(false,salidaesperada) );
            ROLLBACK;
    END eliminar;

END;
/


----------------------------------------------------------------
--PRUEBAS
----------------------------------------------------------------

--PRUEBAS DE USUARIOFAMILIAS

BEGIN
    paquete_usuariofamilias.inicializar;

    paquete_usuariofamilias.insertar('Inserci�n de un usuario familia','agarcia','p4ssw0rd','Abel','Garc�a Gonz�lez','agarcia@gmail.com',true);

    paquete_usuariofamilias.actualizar('Actualizaci�n de un usuario familia con un email repetido',4,'yeez','p4ssw0rd','Fallos','Men�ndez M�ndez','aruiz@gmail.com',false);

    paquete_usuariofamilias.eliminar('Eliminaci�n de un usuario familia',1,true);
END;
/
--PRUEBAS DE USUARIOPROFESORES

BEGIN
    paquete_usuarioprofesores.inicializar;

    paquete_usuarioprofesores.insertar('Inserci�n de un usuario profesor','eagunstin','p4ssw0rd','El�as','Agust�n Navarro','eagunstin@gmail.com',0,NULL,true);

    paquete_usuarioprofesores.actualizar('Actualizaci�n de un usuario profesor',3,'nuevonick','nuevacontrasenya','Ra�l','Apellidos de prueba' ,'emailtest@yahoo.com',0,'https://image.flaticon.com/icons/png/512/42/42912.png',true);
    paquete_usuarioprofesores.actualizar('Actualizaci�n de un usuario profesor con nick y email repetido',1,'nuevonick','nuevacontrasenya','Ra�l','Apellidos de prueba','fgiralda@gmail.com',0,'https://image.flaticon.com/icons/png/512/42/42912.png',false);


    paquete_usuarioprofesores.eliminar('Eliminaci�n de un usuario profesor',1,true);
END;
/
--PRUEBAS DE AVISOS

BEGIN
    paquete_avisos.inicializar;

    paquete_avisos.insertar('Inserci�n de un aviso',2,'primer aviso','Texto de prueba: Lorem ipsum dolor sit amet, consectetur adipis...','http://...' ,'25/11/2017','07/10/2016',true);
    paquete_avisos.insertar('Inserci�n de un aviso',3,'segundo aviso','Texto de prueba: Lorem ipsum dolor sit amet, consectetur adipis...','http://...',TO_DATE('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),true);
    paquete_avisos.insertar('Inserci�n de un aviso',2,'tercer aviso','Texto de prueba: Lorem ipsum dolor sit amet, consectetur adipis...','http://...',TO_DATE('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),true);
    paquete_avisos.insertar('Inserci�n de un aviso',3,'cuarto aviso','Texto de prueba: Lorem ipsum dolor sit amet, consectetur adipis...','http....',TO_DATE('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),true);
    paquete_avisos.insertar('Inserci�n de un aviso',0,'quinto aviso','Texto de prueba: Lorem ipsum dolor sit amet, consectetur adipis...',NULL,TO_DATE ('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),true);
    paquete_avisos.insertar('Inserci�n de un aviso con titulo null',2,NULL,'Texto de prueba: Lorem ipsum dolor sit amet, consectetur adipis...',NULL,TO_DATE('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),false);
    paquete_avisos.insertar('Inserci�n de un aviso con cuerpo null',3,'tercer aviso',NULL,NULL,TO_DATE('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),false);


    paquete_avisos.actualizar('Actualizaci�n de un aviso',0,2,'primer aviso cambiado a segundo aviso','Texto de prueba: Lorem ipsum dolor sit amet, consectetur adipis...' ,NULL,TO_DATE('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),true);
    paquete_avisos.actualizar('Actualizaci�n de un aviso titulo null',0,2,NULL,'Texto de prueba: Lorem ipsum dolor sit amet, consectetur adipis...',NULL,TO_DATE('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),false);
    paquete_avisos.actualizar('Actualizaci�n de un aviso cuerpo null',0,2,'Segundo aviso',NULL,NULL,TO_DATE('25/11/2017','DD/MM/YYYY'),TO_DATE('07/10/2016','DD/MM/YYYY'),false);

    paquete_avisos.eliminar('Eliminaci�n de un aviso',1,true);
END;
/
--PRUEBAS DE CLASES

BEGIN
    paquete_clases.insertar('Inserci�n de una clase',1,'A',2,true);
    paquete_clases.insertar('Inserci�n de una clase',1,'B',3,true);
    paquete_clases.insertar('Inserci�n de una clase',2,'A',3,true);
    paquete_clases.insertar('Inserci�n de una clase',2,'B',3,true);
    paquete_clases.insertar('Inserci�n de una clase',3,'A',2,true);
    paquete_clases.insertar('Inserci�n de una clase con curso 500',500,'A',2,false);
    paquete_clases.insertar('Inserci�n de una clase con foreign key a null',3,'B',NULL,false);
    paquete_clases.actualizar('Actualizaci�n de una clase',0,0,'A',3,false);
    paquete_clases.eliminar('Eliminaci�n de un usuario profesor',1,true);
END;
/

--PRUEBAS DE ALUMNOS

BEGIN
    paquete_alumnos.inicializar;

    paquete_alumnos.insertar('Inserci�n de un alumno',2,2,'Pepito','Gomez Rodriguez',TO_DATE('05/04/1999','DD/MM/YYYY'),true);
    paquete_alumnos.insertar('Inserci�n de un alumno',3,2,'Jimeno','Castro',TO_DATE('05/04/1999','DD/MM/YYYY'),true);
    paquete_alumnos.insertar('Inserci�n de un alumno',4,2,'Adam','Neely',TO_DATE('05/04/1999','DD/MM/YYYY'),true);
    paquete_alumnos.insertar('Inserci�n de un alumno',0,2,'Roberto','Perez',TO_DATE('05/04/1999','DD/MM/YYYY'),true);
    paquete_alumnos.insertar('Inserci�n de un alumno',3,2,'Norberto','Lopez',TO_DATE('05/04/1999','DD/MM/YYYY'),true );
    paquete_alumnos.insertar('Inserci�n de un alumno',5,4,'Fernando','Gomez Rodriguez',TO_DATE('05/04/1999','DD/MM/YYYY'),true);
    paquete_alumnos.insertar('Inserci�n de un alumno',5,3,'Jules','Aguilar',TO_DATE('05/04/1999','DD/MM/YYYY'),true);
    paquete_alumnos.insertar('Inserci�n de un alumno',5,3,'David','Martin Baco',TO_DATE('05/04/1999','DD/MM/YYYY'),false);
    paquete_alumnos.insertar('Inserci�n de un alumno',2,0,'Rosario','Paloma',TO_DATE('05/04/1999','DD/MM/YYYY'),false);


    paquete_alumnos.actualizar('Actualizaci�n de un alumno con oid de usuario familia inexistente',55,4,2,'Adan','Nailon',TO_DATE('05/04/1999','DD/MM/YYYY'),false);
    paquete_alumnos.actualizar('Actualizaci�n de un alumno con oid de clase inexistente ',2,63,2,'Adan','Nailon',TO_DATE('05/04/1999','DD/MM/YYYY'),false);

    paquete_alumnos.eliminar('Eliminaci�n de un alumno',1,true);
      paquete_alumnos.eliminar('Eliminaci�n de un alumno',0,true);
END;
/

--PRUEBAS DE ASIGNATURAS

BEGIN
    paquete_asignaturas.inicializar;
    paquete_asignaturas.insertar('Inserci�n de una asignatura','Lengua',2,4,true);
    paquete_asignaturas.insertar('Inserci�n de una asignatura','Matem�ticas',2,2,true);
    paquete_asignaturas.insertar('Inserci�n de una asignatura','EF',3,3,true);
    paquete_asignaturas.insertar('Inserci�n de una asignatura','Conocimiento del medio',4,4,true);

    paquete_asignaturas.actualizar('Actualizaci�n de una asignatura',1,'Ingl�s',6,2,true);
    paquete_asignaturas.actualizar('Actualizaci�n de una asignatura',2,'Franc�s',7,2,true);

    paquete_asignaturas.eliminar('Eliminaci�n de una asignatura',1,true);
END;
/
--PRUEBAS DE CALIFICACIONES

BEGIN
    paquete_calificaciones.inicializar;

    paquete_calificaciones.insertar('Inserci�n de una calificaci�n',5,'Primera evaluacion',2,2,true);
    paquete_calificaciones.insertar('Inserci�n de una calificaci�n',10,'Primera evaluacion',2,2,true);
    paquete_calificaciones.insertar('Inserci�n de una calificaci�n',0,'Primera evaluacion',3,3,true);
    paquete_calificaciones.insertar('Inserci�n de una calificaci�n con calificacion > 10',15,'Primera evaluacion',4,4,false);
    paquete_calificaciones.insertar('Inserci�n de una calificaci�n con calificacion < 10',-10,'Primera evaluacion',0,4,false);
    paquete_calificaciones.insertar('Inserci�n de una calificaci�n con oid alumno inexistente',5,'Primera evaluacion',100,2,false);
    paquete_calificaciones.insertar('Inserci�n de una calificaci�n con oid clase inexistente',2,'Primera evaluacion',1,150,false);

    paquete_calificaciones.actualizar('Actualizaci�n de una calificaci�n',1,7,'Primera evaluacion',4,2,true);
    paquete_calificaciones.actualizar('Actualizaci�n de una calificaci�n',2,6,'Primera evaluacion',3,2,true);
    paquete_calificaciones.actualizar('Actualizaci�n de una calificacion con calificacion > 10',3,15,'Primera evaluacion',2,3,false);
    paquete_calificaciones.actualizar('Actualizaci�n de una calificaci�n con calificacion < 10',1,-10,'Primera evaluacion',1,4,false);

    paquete_calificaciones.eliminar('Eliminaci�n de una calificaci�n',1,true);
END;
