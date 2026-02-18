DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'marketplacedb') THEN
        PERFORM dblink_exec('dbname=postgres user=postgres password=root',
                            'CREATE DATABASE marketplacedb');
END IF;
END $$;
