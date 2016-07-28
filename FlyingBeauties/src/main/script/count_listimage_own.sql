-- Function: count_listimage_own(integer, integer)

-- DROP FUNCTION count_listimage_own(integer, integer);

CREATE OR REPLACE FUNCTION count_listimage_own(
    IN in_userid integer,
    IN in_competitionid integer)
  RETURNS TABLE("imageNum" bigint) AS
$BODY$
BEGIN
RETURN QUERY SELECT  "count"(1) as "imageNum"
		FROM fb_image_info		
		WHERE fb_image_info."UserId" = in_userId AND fb_image_info."CompetitionId" = in_competitionId;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION count_listimage_own(integer, integer)
  OWNER TO postgres;
