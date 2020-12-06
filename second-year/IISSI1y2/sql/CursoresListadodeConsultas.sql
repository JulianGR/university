-------------------------------------------------------------------------------
--  Scripts de cursores y de listados de consultas
--   Creado por el grupo formado por:
--      Jesus Gamez Larrad
--      Alvaro Rubia Tapia
--      Javier Navarro Pliego
--      Julian Gomez Rodriguez
-- Para la asignatura de IISI1 de la ETSII. Curso 2018/ 2019
-------------------------------------------------------------------------------



--USUARIOS

--nombres y apellidos de los usuarios familia

CREATE OR REPLACE PROCEDURE listausuariofamilias IS
    CURSOR c IS SELECT
                    nombre_uf,
                    apellidos_uf
                FROM
                    usuariofamilias
                ORDER BY
                    apellidos_uf ASC;

    w_listausuariofamilias   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listausuariofamilias;
    dbms_output.put_line(rpad('Nombre familia: ',63)
                           || 'Apellidos familia: ');
    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listausuariofamilias.nombre_uf,63)
                               || w_listausuariofamilias.apellidos_uf);    --rpad sirve para rellener hasta el caracter x (25) de cierto caracter (no le pongo nada, asi que caracter vacio)

        FETCH c INTO w_listausuariofamilias;
    END LOOP;

    CLOSE c;
END listausuariofamilias;
/

--nombres y apellidos de los usuarios profesores

CREATE OR REPLACE PROCEDURE listaprofesores IS
    CURSOR c IS SELECT
                    nombre_up,
                    apellidos_up
                FROM
                    usuarioprofesores
                ORDER BY
                    apellidos_up ASC;

    w_listaprofesores   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listaprofesores;
    dbms_output.put_line(rpad('Nombre profesor: ',63)
                           || 'Apellidos profesor');
    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listaprofesores.nombre_up,63)
                               || w_listaprofesores.apellidos_up);    --rpad sirve para rellener hasta el caracter x (25) de cierto caracter (no le pongo nada, asi que caracter vacio)

        FETCH c INTO w_listaprofesores;
    END LOOP;

    CLOSE c;
END listaprofesores;
/

--email de todos los padres de una clase

CREATE OR REPLACE PROCEDURE listaemailclase (
    w_curso   IN clases.curso%TYPE,
    w_grupo   IN clases.grupo%TYPE
) IS

    CURSOR c IS SELECT
                   nombre_uf,
                   apellidos_uf,
                   email_uf
               FROM
                   usuariofamilias
                   NATURAL JOIN clases
                   NATURAL JOIN alumnos
               WHERE
                   curso = w_curso
                   AND grupo = w_grupo
               ORDER BY
                   nombre_uf ASC;

    w_listaemailclase   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listaemailclase;
    dbms_output.put_line(rpad('Nombre familia: ',63)
                           || rpad('Apellidos familia',63)
                           || 'Email familia');

    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listaemailclase.nombre_uf,63)
                               || rpad(w_listaemailclase.apellidos_uf,63)
                               || w_listaemailclase.email_uf);    --rpad sirve para rellener hasta el caracter x (25) de cierto caracter (no le pongo nada, asi que caracter vacio)

        FETCH c INTO w_listaemailclase;
    END LOOP;

    CLOSE c;
END listaemailclase;
/


--AVISOS
--lista de destinatarios de un aviso

CREATE OR REPLACE PROCEDURE listadestinatarios (
    w_oid_av   IN avisos.oid_av%TYPE
) IS
    CURSOR c IS SELECT
                    nombre_uf,
                    apellidos_uf
                FROM
                    usuariofamilias
                ORDER BY
                    apellidos_uf ASC;

    w_listadestinatarios   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listadestinatarios;
    dbms_output.put_line(rpad('Nombre familia: ',63)
                           || 'Apellidos familia');
    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listadestinatarios.nombre_uf,63)
                               || w_listadestinatarios.apellidos_uf);    --rpad sirve para rellener hasta el caracter x (25) de cierto caracter (no le pongo nada, asi que caracter vacio)

        FETCH c INTO w_listadestinatarios;
    END LOOP;

    CLOSE c;
END listadestinatarios;
/

--tiempo que ha pasado desde la fecha de suceso hasta la de publicacion de todos los avisos

CREATE OR REPLACE PROCEDURE tiempotranscurrido (
    w_oid_av   IN avisos.oid_av%TYPE
) IS

    CURSOR c IS SELECT
                   titulo_av
               FROM
                   avisos
               ORDER BY
                   fechapublicacion ASC;

    w_tiempotranscurrido   c%rowtype;
    w_fechapublicacion     avisos.fechapublicacion%TYPE;
BEGIN
    SELECT
        fechapublicacion
    INTO w_fechapublicacion
    FROM
        avisos
    WHERE
        oid_av = w_oid_av;

    OPEN c;
    FETCH c INTO w_tiempotranscurrido;
    dbms_output.put_line(rpad('Titulo aviso: ',100)
                           || 'Tiempo transcurrido:');
    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_tiempotranscurrido.titulo_av,100)
                               || SYSDATE - w_fechapublicacion);

        FETCH c INTO w_tiempotranscurrido;
    END LOOP;

    CLOSE c;
END tiempotranscurrido;
/

--avisos que no han pasado todavia

CREATE OR REPLACE PROCEDURE avisosnopasados IS
    CURSOR c IS SELECT
                    titulo_av
                FROM
                    avisos
                WHERE
                    fechasuceso < SYSDATE;

    w_avisosnopasados   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_avisosnopasados;
    dbms_output.put_line('Titulo aviso: ');
    WHILE c%found LOOP
        dbms_output.put_line(w_avisosnopasados.titulo_av);
        FETCH c INTO w_avisosnopasados;
    END LOOP;

    CLOSE c;
END avisosnopasados;
/
--CLASES
--lista de las clases con los tutores

CREATE OR REPLACE PROCEDURE listaclasescontutores IS

    CURSOR c IS SELECT
                   curso,
                   grupo,
                   nombre_up,
                   apellidos_up
               FROM
                   clases
                   NATURAL JOIN usuarioprofesores
               ORDER BY
                   curso ASC;

    w_listaclasescontutores   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listaclasescontutores;
    dbms_output.put_line(rpad('Curso: ',10)
                           || rpad('Grupo: ',10)
                           || rpad('Nombre profesor: ',63)
                           || 'Apellidos profesor: ');

    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listaclasescontutores.curso,10)
                               || rpad(w_listaclasescontutores.grupo,10)
                               || rpad(w_listaclasescontutores.nombre_up,63)
                               || w_listaclasescontutores.apellidos_up);

        FETCH c INTO w_listaclasescontutores;
    END LOOP;

    CLOSE c;
END listaclasescontutores;
/
--alumnos de una clase

CREATE OR REPLACE PROCEDURE listaalumnosclase (
    w_curso   IN clases.curso%TYPE,
    w_grupo   IN clases.grupo%TYPE
) IS

    CURSOR c IS SELECT
                   nombre_al,
                   apellidos_al
               FROM
                   alumnos
                   NATURAL JOIN clases
               ORDER BY
                   apellidos_al ASC;

    w_listaalumnosclase   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listaalumnosclase;
    dbms_output.put_line(rpad('Nombre alumnos: ',63)
                           || 'Apellidos alumno: ');
    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listaalumnosclase.nombre_al,63)
                               || w_listaalumnosclase.apellidos_al);    --rpad sirve para rellener hasta el caracter x (25) de cierto caracter (no le pongo nada, asi que caracter vacio)

        FETCH c INTO w_listaalumnosclase;
    END LOOP;

    CLOSE c;
END listaalumnosclase;
/
--asignaturas que tiene un alumno

CREATE OR REPLACE PROCEDURE listaasignaturasporalumno (
    w_oid_al   IN alumnos.oid_al%TYPE
) IS
    CURSOR c IS SELECT
                    nombre_as
                FROM
                    alumnos
                    NATURAL JOIN asignaturas
                WHERE
                    oid_al = w_oid_al;

    w_listaasignaturasporalumno   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listaasignaturasporalumno;
    dbms_output.put_line('Nombre asignatura: ');
    WHILE c%found LOOP
        dbms_output.put_line(w_listaasignaturasporalumno.nombre_as);
        FETCH c INTO w_listaasignaturasporalumno;
    END LOOP;

    CLOSE c;
END listaasignaturasporalumno;
/
--lista de alumnos en las cuales sus calificaciones de cierta asignatura hayan superado el 5

CREATE OR REPLACE PROCEDURE listaalumnosaprobados (
    w_nombre_as   IN asignaturas.nombre_as%TYPE
) IS

    CURSOR c IS SELECT
                   nombre_al,
                   apellidos_al,
                   curso,
                   grupo,
                   valor_ca
               FROM
                   alumnos
                   NATURAL JOIN clases
                   NATURAL JOIN calificaciones
                   NATURAL JOIN asignaturas
               WHERE
                   valor_ca >= 5
               ORDER BY
                   apellidos_al ASC;

    w_listaalumnosaprobados   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listaalumnosaprobados;
    dbms_output.put_line(rpad('Nombre alumnos: ',63)
                           || rpad('Apellidos alumno: ',63)
                           || rpad('Curso:',10)
                           || rpad('Grupo:',10)
                           || 'Valor');

    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listaalumnosaprobados.nombre_al,63)
                               || rpad(w_listaalumnosaprobados.apellidos_al,63)
                               || rpad(w_listaalumnosaprobados.curso,10)
                               || rpad(w_listaalumnosaprobados.grupo,10)
                               || w_listaalumnosaprobados.valor_ca);

        FETCH c INTO w_listaalumnosaprobados;
    END LOOP;

    CLOSE c;
END listaalumnosaprobados;
/
--lista de todas las calificaciones de cierta evaluacion (convocatoria) de un alumno

CREATE OR REPLACE PROCEDURE listanotasconvocatoria (
    w_convocatoria   IN calificaciones.convocatoria%TYPE,
    w_oid_al         IN alumnos.oid_al%TYPE
) IS

    CURSOR c IS SELECT
                   nombre_as,
                   valor_ca
               FROM
                   alumnos
                   NATURAL JOIN calificaciones
                   NATURAL JOIN asignaturas
               WHERE
                   oid_al = w_oid_al
                   AND convocatoria = w_convocatoria;

    w_listanotasconvocatoria   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listanotasconvocatoria;
    dbms_output.put_line(rpad('Nombre asignatura: ',50)
                           || 'Nota obtenida: ');
    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listanotasconvocatoria.nombre_as,63)
                               || w_listanotasconvocatoria.valor_ca);

        FETCH c INTO w_listanotasconvocatoria;
    END LOOP;

    CLOSE c;
END listanotasconvocatoria;
/
--lista de todos los profesores de una clase

CREATE OR REPLACE PROCEDURE listaprofesoresclase (
    w_curso   IN clases.curso%TYPE,
    w_grupo   IN clases.grupo%TYPE
) IS

    CURSOR c IS SELECT
                   nombre_up,
                   apellidos_up
               FROM
                   clases
                   NATURAL JOIN asignaturas
                   NATURAL JOIN usuarioprofesores
               WHERE
                   curso = w_curso
                   AND grupo = w_grupo;

    w_listaprofesoresclase   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listaprofesoresclase;
    dbms_output.put_line(rpad('Nombre profesor: ',63)
                           || 'Apellidos profesor: ');
    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listaprofesoresclase.nombre_up,63)
                               || w_listaprofesoresclase.apellidos_up);

        FETCH c INTO w_listaprofesoresclase;
    END LOOP;

    CLOSE c;
END listaprofesoresclase;
/
--lista de todos los profesores que tiene un alumno

CREATE OR REPLACE PROCEDURE listaprofesoresalumno (
    w_oid_al   IN alumnos.oid_al%TYPE
) IS

    CURSOR c IS SELECT
                   nombre_up,
                   apellidos_up
               FROM
                   alumnos
                   NATURAL JOIN calificaciones
                   NATURAL JOIN asignaturas
                   NATURAL JOIN usuarioprofesores
               WHERE
                   oid_al = w_oid_al;

    w_listaprofesoresalumno   c%rowtype;
BEGIN
    OPEN c;
    FETCH c INTO w_listaprofesoresalumno;
    dbms_output.put_line(rpad('Nombre profesor: ',63)
                           || 'Apellidos profesor: ');
    WHILE c%found LOOP
        dbms_output.put_line(rpad(w_listaprofesoresalumno.nombre_up,63)
                               || w_listaprofesoresalumno.apellidos_up);

        FETCH c INTO w_listaprofesoresalumno;
    END LOOP;

    CLOSE c;
END listaprofesoresalumno;
/