-- Function: admin_addgroupintocompetition(integer, integer)

-- DROP FUNCTION admin_addgroupintocompetition(integer, integer);

CREATE OR REPLACE FUNCTION admin_addgroupintocompetition(
    "in_GroupId" integer,
    "in_CompetitionId" integer)
  RETURNS void AS
$BODY$
BEGIN
	INSERT INTO fb_competition_group ("GroupId", "CompetitionId")
	VALUES ("in_GroupId", "in_CompetitionId");
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_addgroupintocompetition(integer, integer)
  OWNER TO postgres;
