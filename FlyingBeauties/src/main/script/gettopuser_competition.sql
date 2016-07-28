-- Function: gettopuser_competition(integer)

-- DROP FUNCTION gettopuser_competition(integer);

CREATE OR REPLACE FUNCTION gettopuser_competition(IN in_competitionid integer)
  RETURNS TABLE("userId" integer, "Firstname" character varying, "avatarUrl" character varying, "imageNum" bigint, "SumPoint" bigint, "GroupName" character varying) AS
$BODY$
BEGIN
RETURN QUERY
		SELECT fb_image_info."UserId", "FirstName", "AvatarUrl", "count"(fb_image_info."ImageId") as "ImageNum",
		COALESCE ("sum"("Point"), 0) as "SumPoint", fb_group."GroupName" 
		from fb_image_info
		JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		JOIN fb_group ON fb_group."GroupId" = fb_user."GroupId"
		WHERE fb_image_info."CompetitionId" = in_competitionid
		GROUP BY fb_image_info."UserId", "FirstName", "AvatarUrl", fb_group."GroupName" 
		ORDER BY "SumPoint" DESC, "ImageNum" DESC
		LIMIT 3 OFFSET 0;		
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION gettopuser_competition(integer)
  OWNER TO postgres;
