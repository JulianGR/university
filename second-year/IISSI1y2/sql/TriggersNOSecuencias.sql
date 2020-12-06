-------------------------------------------------------------------------------
--  Script de creación de triggers no asociados a secuencias
--   Creado por el grupo formado por:
--      Jesus Gamez Larrad
--      Alvaro Rubia Tapia
--      Javier Navarro Pliego
--      Julian Gomez Rodriguez
-- Para la asignatura de IISI1 de la ETSII. Curso 2018/ 2019
-------------------------------------------------------------------------------


-------------------------------------------------------------------------------
-- Triggers de unicidad
-------------------------------------------------------------------------------


--Al insertar un nuevo usuarioProfesor, comprobar que el nick no existe

CREATE OR REPLACE TRIGGER mismonickusuarioprofesores BEFORE
    INSERT ON usuarioprofesores
    FOR EACH ROW
DECLARE
    cuentaup   INTEGER;
    cuentauf   INTEGER;
BEGIN
    SELECT
        COUNT(*)
    INTO cuentaup
    FROM
        usuarioprofesores
    WHERE
        nick =:new.nick;

    SELECT
        COUNT(*)
    INTO cuentauf
    FROM
        usuariofamilias
    WHERE
        nick =:new.nick;

    IF ( cuentaup + cuentauf != 0 ) THEN
        raise_application_error(-20600,:new.nick
                                        || 'El usuario ya existe');
    END IF;

END;
/
--Al insertar una nuevo usuarioFamilia, comprobar que el nick no existe

CREATE OR REPLACE TRIGGER mismonickusuariofamilias BEFORE
    INSERT ON usuariofamilias
    FOR EACH ROW
DECLARE
    cuentaup   INTEGER;
    cuentauf   INTEGER;
BEGIN
    SELECT
        COUNT(*)
    INTO cuentaup
    FROM
        usuarioprofesores
    WHERE
        nick =:new.nick;

    SELECT
        COUNT(*)
    INTO cuentauf
    FROM
        usuariofamilias
    WHERE
        nick =:new.nick;

    IF ( cuentaup + cuentauf != 0 ) THEN
        raise_application_error(-20600,:new.nick
                                        || 'El usuario ya existe');
    END IF;

END;
/
--Comprobar que al crear o actualizar clases, no haya ninguna clase repetida 

CREATE OR REPLACE TRIGGER mismaclase BEFORE
    INSERT OR UPDATE ON clases
    FOR EACH ROW
DECLARE
    cuenta   NUMBER;
BEGIN
    SELECT
        COUNT(*)
    INTO cuenta
    FROM
        clases
    WHERE
        ( curso =:new.curso
          AND grupo =:new.grupo );

    IF ( cuenta > 0 ) THEN
        raise_application_error(-20600,:new.curso
                                        ||:new.grupo
                                        || 'La clase ya existe');

    END IF;

END;
/
-------------------------------------------------------------------------------
-- Triggers de Reglas de Negocio
-------------------------------------------------------------------------------
--Al crear un aviso, comprobar que la fecha de publicacion es anterior a la fecha de suceso

CREATE OR REPLACE TRIGGER fechaalrevesavisos BEFORE
    INSERT ON avisos
    FOR EACH ROW
BEGIN
    IF (:new.fechasuceso != NULL ) THEN
        IF (:new.fechapublicacion >:new.fechasuceso ) THEN
            raise_application_error(-20600,'La fecha del suceso debe ser posterior a la fecha de publicacion');
        END IF;

    END IF;
END;
/
--Al crear o actualizar una encuesta, comprobar que la fecha de inicio es anterior a la fecha de fin

-------------------------------------------------------------------------------
-- Triggers de relaciones
-------------------------------------------------------------------------------

--Al crear una encuesta, comprobar que los usuariofamilias tienen al menos un alumno

CREATE OR REPLACE TRIGGER unhijo AFTER
    DELETE ON alumnos
    FOR EACH ROW
DECLARE
    cuenta   NUMBER;
BEGIN
    SELECT
        COUNT(*)
    INTO cuenta
    FROM
        alumnos
    WHERE
        oid_uf =:old.oid_uf;

    IF ( cuenta = 0 ) THEN
        raise_application_error(-20600,'el usuariofamilia no puede tener 0 alumnos');
    END IF;
END;
/
--al crear un alumno comprobar que la clase a la que pertenece no sobrepasa los 28 alumnos

CREATE OR REPLACE TRIGGER maximoalumnosporclase AFTER
    INSERT ON alumnos
    FOR EACH ROW
DECLARE
    new_numerodealumnos   NUMBER(12,2);
BEGIN
    SELECT
        COUNT(*)
    INTO new_numerodealumnos
    FROM
        alumnos
    WHERE
        oid_cl =:new.oid_cl;

    IF new_numerodealumnos < 28 THEN
        raise_application_error(-20600,'La capacidad máxima de alumnos es de 28');
    END IF;
END;
/
--al crear una clase comprobar que no se le asigna como tutor un usuarioprofesor que ya es tutor de otra clase

CREATE OR REPLACE TRIGGER unsolotutorporclase BEFORE
    INSERT ON clases
    FOR EACH ROW
DECLARE
    new_oid_up        NUMBER(12,2) :=:new.oid_up;
    new_unsolotutor   NUMBER(2);
BEGIN
    SELECT
        COUNT(*)
    INTO new_unsolotutor
    FROM
        clases
    WHERE
        oid_up =:new.oid_up;

    IF new_unsolotutor > 0 THEN
        raise_application_error(-20600,'Una clase solo puede tener un único tutor');
    END IF;
END;
/
--Al crear una asignatura, comprobar que no sobrepasa el numero maximo de asignaturas por clases

CREATE OR REPLACE TRIGGER maximasasignaturasporclase BEFORE
    INSERT ON asignaturas
    FOR EACH ROW
DECLARE
    new_oid_cl         NUMBER(12,2) :=:new.oid_cl;
    new_numerodeasig   NUMBER(12,2);
BEGIN
    SELECT
        COUNT(*)
    INTO new_numerodeasig
    FROM
        asignaturas
    WHERE
        oid_cl = new_oid_cl;

    IF new_numerodeasig > 10 THEN
        raise_application_error(-20600,'Una clase solo puede tener 10 asignaturas');
    END IF;
END;
/



-------------------------------------------------------------------------------
-- Desactivar triggers
-------------------------------------------------------------------------------
ALTER TABLE usuariofamilias DISABLE ALL TRIGGERS;
ALTER TABLE usuarioprofesores DISABLE ALL TRIGGERS;
ALTER TABLE avisos DISABLE ALL TRIGGERS;
ALTER TABLE clases DISABLE ALL TRIGGERS;
ALTER TABLE alumnos DISABLE ALL TRIGGERS;
ALTER TABLE asignaturas DISABLE ALL TRIGGERS;
ALTER TABLE calificaciones DISABLE ALL TRIGGERS;