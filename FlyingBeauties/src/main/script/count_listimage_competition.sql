-- Function: count_listimage_competition(integer)

-- DROP FUNCTION count_listimage_competition(integer);

CREATE OR REPLACE FUNCTION count_listimage_competition(IN in_competitionid integer)
  RETURNS TABLE("imageNum" bigint) AS
$BODY$
BEGIN
	  RETURN query 
			SELECT "count"(1) as "imageNum"
			FROM fb_image_info
			WHERE fb_image_info."CompetitionId" = in_competitionId;
		
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION count_listimage_competition(integer)
  OWNER TO postgres;
