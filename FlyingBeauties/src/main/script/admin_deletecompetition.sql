-- Function: admin_deletecompetition(integer)

-- DROP FUNCTION admin_deletecompetition(integer);

CREATE OR REPLACE FUNCTION admin_deletecompetition("in_CompetitionId" integer)
  RETURNS void AS
$BODY$
BEGIN
	UPDATE fb_competition SET "CompetitionStatus" = 0
			
	WHERE "CompetitionId" = "in_CompetitionId";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_deletecompetition(integer)
  OWNER TO postgres;
