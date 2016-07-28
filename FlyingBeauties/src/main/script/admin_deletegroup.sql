-- Function: admin_deletegroup(integer)

-- DROP FUNCTION admin_deletegroup(integer);

CREATE OR REPLACE FUNCTION admin_deletegroup("in_GroupId" integer)
  RETURNS void AS
$BODY$
BEGIN
	UPDATE fb_group SET "Status" = 0
	WHERE "GroupId" = "in_GroupId";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_deletegroup(integer)
  OWNER TO postgres;
