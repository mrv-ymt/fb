-- Function: admin_removecompetitionoutgroup(integer, integer)

-- DROP FUNCTION admin_removecompetitionoutgroup(integer, integer);

CREATE OR REPLACE FUNCTION admin_removecompetitionoutgroup(
    "in_GroupId" integer,
    "in_CompetitionId" integer)
  RETURNS void AS
$BODY$
BEGIN
	DELETE FROM fb_competition_group 
	WHERE "GroupId" = "in_GroupId" AND "CompetitionId" = "in_CompetitionId";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_removecompetitionoutgroup(integer, integer)
  OWNER TO postgres;
