-- Function: count_listimage_group(integer)

-- DROP FUNCTION count_listimage_group(integer);

CREATE OR REPLACE FUNCTION count_listimage_group(IN "in_userId" integer)
  RETURNS TABLE("imageNum" bigint) AS
$BODY$
BEGIN
RETURN QUERY SELECT  "count"(1) as "imageNum"
			FROM fb_image_info
			JOIN fb_competition ON fb_competition."CompetitionId" = fb_image_info."CompetitionId"
			JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
			WHERE fb_user."GroupId" = (SELECT fb_user."GroupId" FROM fb_user WHERE fb_user."UserId" = "in_userId");
		
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION count_listimage_group(integer)
  OWNER TO postgres;
