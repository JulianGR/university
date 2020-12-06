-------------------------------------------------------------------------------
--  Scripts de creación de funciones y procedimientos
--   Creado por el grupo formado por:
--      Jesus Gamez Larrad
--      Alvaro Rubia Tapia
--      Javier Navarro Pliego
--      Julian Gomez Rodriguez
-- Para la asignatura de IISI1 de la ETSII. Curso 2018/ 2019
-------------------------------------------------------------------------------


--------------------------------------------------------
--  Procedimientos de creacion y borrado
--------------------------------------------------------
 --usuariofamilias

CREATE OR REPLACE PROCEDURE crearusuariofamilia (
    w_nick          IN usuariofamilias.nick%TYPE,
    w_contrasenya   IN usuariofamilias.contrasenya%TYPE,
    w_nombre        IN usuariofamilias.nombre_uf%TYPE,
    w_apellidos     IN usuariofamilias.apellidos_uf%TYPE,
    w_email_uf         IN usuariofamilias.email_uf%TYPE
) IS
BEGIN
    INSERT INTO usuariofamilias VALUES (
        sec_uf.NEXTVAL,
        w_nick,
        w_contrasenya,
        w_nombre,
        w_apellidos,
        w_email_uf
    );

    COMMIT WORK;
END crearusuariofamilia;
/

create or replace PROCEDURE borrarusuariofamilia (
    w_oid_uf   IN usuariofamilias.oid_uf%TYPE
) IS
BEGIN
    DELETE FROM usuariofamilias
    WHERE
        oid_uf = w_oid_uf;

    COMMIT WORK;
END borrarusuariofamilia;
/
--usuarioprofesores

CREATE OR REPLACE PROCEDURE crearusuarioprofesor (
    w_nick          IN usuarioprofesores.nick%TYPE,
    w_contrasenya   IN usuarioprofesores.contrasenya%TYPE,
    w_nombre        IN usuarioprofesores.nombre_up%TYPE,
    w_apellidos     IN usuarioprofesores.apellidos_up%TYPE,
    w_email_up         IN usuarioprofesores.email_up%TYPE,
    w_estutor       IN usuarioprofesores.estutor%TYPE,
    w_fotourl       IN usuarioprofesores.fotourl%TYPE
) IS
BEGIN
    INSERT INTO usuarioprofesores VALUES (
        sec_up.NEXTVAL,
        w_nick,
        w_contrasenya,
        w_nombre,
        w_apellidos,
        w_email_up,
        w_estutor,
        w_fotourl
    );

    COMMIT WORK;
END crearusuarioprofesor;
/

create or replace PROCEDURE borrarusuarioprofesor (
    w_oid_up   IN usuarioprofesores.oid_up%TYPE
) IS
BEGIN
    DELETE FROM usuarioprofesores
    WHERE
        oid_up = w_oid_up;

    COMMIT WORK;
END borrarusuarioprofesor;
/
--avisos

CREATE OR REPLACE PROCEDURE crearaviso (
    w_titulo        IN avisos.titulo_av%TYPE,
    w_cuerpo        IN avisos.cuerpo%TYPE,
    w_archivourl    IN avisos.archivourl%TYPE,
    w_fechasuceso   IN avisos.fechasuceso%TYPE,
    w_oid_up        IN avisos.oid_up%TYPE
) IS
BEGIN
    INSERT INTO avisos VALUES (
        sec_av.NEXTVAL,
        w_titulo,
        w_cuerpo,
        w_archivourl,
        w_fechasuceso,
        SYSDATE,
        w_oid_up
    );

    COMMIT WORK;
END crearaviso;
/

CREATE OR REPLACE PROCEDURE borraravisos (
    w_oid_av   IN avisos.oid_av%TYPE
) IS
BEGIN
    DELETE FROM avisos
    WHERE
        oid_av = w_oid_av;

    COMMIT WORK;
END borraravisos;
/
--clases

CREATE OR REPLACE PROCEDURE crearclase (
    w_curso    IN clases.curso%TYPE,
    w_grupo    IN clases.grupo%TYPE,
    w_oid_up   IN clases.oid_up%TYPE
) IS
BEGIN
    INSERT INTO clases VALUES (
        sec_cl.NEXTVAL,
        w_curso,
        w_grupo,
        w_oid_up
    );

    COMMIT WORK;
END crearclase;
/

CREATE OR REPLACE PROCEDURE borrarclase (
    w_oid_cl   IN clases.oid_cl%TYPE
) IS
BEGIN
    DELETE clases
    WHERE
        oid_cl = w_oid_cl;

    COMMIT WORK;
END borrarclase;
/
--alumnos

CREATE OR REPLACE PROCEDURE crearalumno (
    w_nombre            IN alumnos.nombre_al%TYPE,
    w_apellidos         IN alumnos.apellidos_al%TYPE,
    w_fechanacimiento   IN alumnos.fechanacimiento%TYPE,
    w_oid_uf            IN alumnos.oid_uf%TYPE,
    w_oid_cl            IN alumnos.oid_cl%TYPE
) IS
BEGIN
    INSERT INTO alumnos VALUES (
        sec_al.NEXTVAL,
        w_nombre,
        w_apellidos,
        w_fechanacimiento,
        w_oid_uf,
        w_oid_cl
    );

    COMMIT WORK;
END crearalumno;
/

CREATE OR REPLACE PROCEDURE borraralumno (
    w_oid_al   IN alumnos.oid_al%TYPE
) IS
BEGIN
    DELETE alumnos
    WHERE
        oid_al = w_oid_al;
    COMMIT WORK;
END borraralumno;
/
--asignaturas

CREATE OR REPLACE PROCEDURE crearasignatura (
    w_nombre   IN asignaturas.nombre_as%TYPE,
    w_oid_up   IN asignaturas.oid_up%TYPE,
    w_oid_cl   IN asignaturas.oid_cl%TYPE
) IS
BEGIN
    INSERT INTO asignaturas VALUES (
        sec_as.NEXTVAL,
        w_nombre,
        w_oid_up,
        w_oid_cl
    );

    COMMIT WORK;
END crearasignatura;
/

CREATE OR REPLACE PROCEDURE borrarasignatura (
    w_oid_as   IN asignaturas.oid_as%TYPE
) IS
BEGIN
    DELETE asignaturas
    WHERE
        oid_as = w_oid_as;
    COMMIT WORK;
END borrarasignatura;
/
--calificaciones

CREATE OR REPLACE PROCEDURE crearcalificacion (
    w_valor          IN calificaciones.valor_ca%TYPE,
    w_convocatoria   IN calificaciones.convocatoria%TYPE,
    w_oid_al         IN calificaciones.oid_al%TYPE,
    w_oid_as         IN calificaciones.oid_as%TYPE
) IS
BEGIN
    INSERT INTO calificaciones VALUES (
        sec_ca.NEXTVAL,
        w_valor,
        w_convocatoria,
        w_oid_al,
        w_oid_as
    );

    COMMIT WORK;
END crearcalificacion;
/

CREATE OR REPLACE PROCEDURE borrarcalificacion (
    w_oid_ca   IN calificaciones.oid_ca%TYPE
) IS
BEGIN
    DELETE calificaciones
    WHERE
        oid_ca = w_oid_ca;
    COMMIT WORK;
END borrarcalificacion;
/

CREATE OR REPLACE PROCEDURE modificacalificacion (
    w_oid_ca            IN calificaciones.oid_ca%TYPE,
    nuevacalificacion   IN calificaciones.valor_ca%TYPE
) IS
BEGIN
    UPDATE calificaciones
    SET
        valor_ca = nuevacalificacion
    WHERE
        oid_ca = w_oid_ca;

    COMMIT WORK;
END modificacalificacion;
/

--------------------------------------------------------
--  Procedimientos y funciones varios
--------------------------------------------------------


--Para que un profesor pueda obtener la nota de cierta asignatura de un alumno

CREATE OR REPLACE FUNCTION notaalumno (
    w_oid_al   IN alumnos.oid_al%TYPE,
    w_oid_as   IN asignaturas.oid_as%TYPE
) RETURN NUMBER AS
    w_nota   calificaciones.valor_ca%TYPE;
BEGIN
    SELECT
        valor_ca
    INTO w_nota
    FROM
        alumnos
        NATURAL JOIN calificaciones
        NATURAL JOIN asignaturas
    WHERE
        w_oid_al = oid_al
        AND w_oid_as = oid_as;

    RETURN w_nota;
END;
/

--Nota media de todas las asignaturas de un alumno

CREATE OR REPLACE FUNCTION notamedia (
    w_oid_al   IN alumnos.oid_al%TYPE
) RETURN NUMBER IS
    notamedia_alumno   calificaciones.valor_ca%TYPE;
BEGIN
    SELECT
        AVG(valor_ca)
    INTO notamedia_alumno
    FROM
        calificaciones
    WHERE
        oid_al = w_oid_al;

    RETURN notamedia_alumno;
END notamedia;
/

--Un padre podrá ver la mejor nota de las asignaturas de sus hijos

CREATE OR REPLACE FUNCTION mejornota (
    w_oid_al   IN alumnos.oid_al%TYPE
) RETURN NUMBER IS
    mejornota   calificaciones.valor_ca%TYPE;
BEGIN
    SELECT
        MAX(valor_ca)
    INTO mejornota
    FROM
        calificaciones
    WHERE
        oid_al = w_oid_al;

    RETURN mejornota;
END mejornota;
/

--Obtener la clase de un alumno

CREATE OR REPLACE FUNCTION clasealumno (
    w_oid_al   IN alumnos.oid_al%TYPE
) RETURN VARCHAR2 IS
    aula   VARCHAR2(2);
BEGIN
    SELECT
        curso || TO_CHAR(grupo)
    INTO aula
    FROM
        clases
    WHERE
        oid_cl = (
            SELECT
                oid_cl
            FROM
                alumnos
            WHERE
                oid_al = w_oid_al
        );

    RETURN aula;
END clasealumno;
/

--elimina los avisos que clausuraron hace 30 días

CREATE OR REPLACE PROCEDURE elimina_avisos_caducados IS
BEGIN
    DELETE FROM avisos
    WHERE
        ( SYSDATE - fechasuceso > 30 );

END elimina_avisos_caducados;
/
--elimina los avisos anteriores a un dia determinado

CREATE OR REPLACE PROCEDURE elimina_avisos_antesde (
    w_fecha avisos.fechasuceso%TYPE
) IS
BEGIN
    DELETE FROM avisos
    WHERE
        ( fechasuceso < w_fecha );

END elimina_avisos_antesde;
/