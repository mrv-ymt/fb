-- Function: joincompetition2(integer, integer, integer)

-- DROP FUNCTION joincompetition2(integer, integer, integer);

CREATE OR REPLACE FUNCTION joincompetition2(
    in_competitionid integer,
    in_userid integer,
    in_groupid integer)
  RETURNS void AS
$BODY$
BEGIN
	INSERT INTO fb_competition_user VALUES(in_competitionId, in_userId, in_groupId);

END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION joincompetition2(integer, integer, integer)
  OWNER TO postgres;
