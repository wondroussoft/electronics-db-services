DO
$body$
BEGIN
    IF NOT EXISTS(
            SELECT *
            FROM pg_catalog.pg_user
            WHERE usename = 'intuit')
    THEN
        CREATE ROLE intuit WITH PASSWORD 'Intuit!2d7D2f3B';
    END IF;
END
$body$;

ALTER ROLE intuit NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION LOGIN;
