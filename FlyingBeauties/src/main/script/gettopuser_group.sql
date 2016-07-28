-- Function: gettopuser_group(integer)

-- DROP FUNCTION gettopuser_group(integer);

CREATE OR REPLACE FUNCTION gettopuser_group(IN in_groupid integer)
  RETURNS TABLE("userId" integer, "Firstname" character varying, "avatarUrl" character varying, "imageNum" bigint, "SumPoint" bigint) AS
$BODY$
BEGIN
RETURN QUERY

		SELECT fb_image_info."UserId", "FirstName", "AvatarUrl", "count"(fb_image_info."ImageId") as "ImageNum",
		COALESCE ("sum"("Point"), 0) as "SumPoint" from fb_image_info
		JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		WHERE fb_user."GroupId" = in_groupid
		GROUP BY fb_image_info."UserId", "FirstName", "AvatarUrl"
		ORDER BY "SumPoint" DESC, "ImageNum" DESC
		LIMIT 3 OFFSET 0;		
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION gettopuser_group(integer)
  OWNER TO postgres;
