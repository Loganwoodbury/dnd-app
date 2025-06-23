-- **************************************************************
-- This script destroys the database
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'DungeonsAndDragons';

DROP DATABASE "DungeonsAndDragons";

