-- Function: admin_deactivatetoogleuserrole(integer, integer)

-- DROP FUNCTION admin_deactivatetoogleuserrole(integer, integer);

CREATE OR REPLACE FUNCTION admin_deactivatetoogleuserrole(
    "in_userId" integer,
    "in_userStatus" integer)
  RETURNS void AS
$BODY$
BEGIN
	UPDATE fb_user SET "Status" = "in_userStatus"
	WHERE "UserId" = "in_userId";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_deactivatetoogleuserrole(integer, integer)
  OWNER TO postgres;
