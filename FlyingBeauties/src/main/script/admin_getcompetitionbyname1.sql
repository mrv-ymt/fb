-- Function: admin_getcompetitionbyname(character varying)

-- DROP FUNCTION admin_getcompetitionbyname(character varying);

CREATE OR REPLACE FUNCTION admin_getcompetitionbyname(IN "in_competitionName" character varying)
  RETURNS TABLE("CompetitionId" integer) AS
$BODY$
BEGIN
	RETURN QUERY
		SELECT fb_competition."CompetitionId"
		FROM fb_competition
		WHERE fb_competition."CompetitionName" = "in_competitionName";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION admin_getcompetitionbyname(character varying)
  OWNER TO postgres;
