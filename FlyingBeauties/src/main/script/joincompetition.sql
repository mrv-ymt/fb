-- Function: joincompetition(integer, integer)

-- DROP FUNCTION joincompetition(integer, integer);

CREATE OR REPLACE FUNCTION joincompetition(
    in_competition integer,
    in_userid integer)
  RETURNS void AS
$BODY$
BEGIN
	INSERT INTO fb_competition_user VALUES(in_competition, in_userId);

END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION joincompetition(integer, integer)
  OWNER TO postgres;
