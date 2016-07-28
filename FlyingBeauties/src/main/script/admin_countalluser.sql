-- Function: admin_countalluser()

-- DROP FUNCTION admin_countalluser();

CREATE OR REPLACE FUNCTION admin_countalluser()
  RETURNS TABLE("UserNumber" bigint) AS
$BODY$
BEGIN
RETURN QUERY
		SELECT "count"(*) FROM fb_user;

END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION admin_countalluser()
  OWNER TO postgres;
