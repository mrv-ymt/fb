-- Function: admin_getcompetitionbyname(character varying, integer)

-- DROP FUNCTION admin_getcompetitionbyname(character varying, integer);

CREATE OR REPLACE FUNCTION admin_getcompetitionbyname(
    IN "in_competitionName" character varying,
    IN "in_competitionId" integer)
  RETURNS TABLE("CompetitionId" integer) AS
$BODY$
BEGIN
	RETURN QUERY
		SELECT fb_competition."CompetitionId"
		FROM fb_competition
		WHERE fb_competition."CompetitionName" = "in_competitionName" AND fb_competition."CompetitionId" <> "in_competitionId";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION admin_getcompetitionbyname(character varying, integer)
  OWNER TO postgres;
