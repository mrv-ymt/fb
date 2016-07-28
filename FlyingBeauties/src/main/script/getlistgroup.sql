-- Function: getlistgroup()

-- DROP FUNCTION getlistgroup();

CREATE OR REPLACE FUNCTION getlistgroup()
  RETURNS TABLE("groupId" integer, "groupName" character varying, "groupLogoUrl" character varying, "Description" text, participants bigint, "ImageNum" bigint, "TotalPoint" bigint) AS
$BODY$
BEGIN
RETURN QUERY
		SELECT fb_group."GroupId", fb_group."GroupName", fb_group."GroupLogoUrl", 
		fb_group."Description",			
		(SELECT "count"(*) FROM fb_user 
		JOIN fb_competition_user ON fb_competition_user."UserId" = fb_user."UserId"
		WHERE fb_user."GroupId" = fb_group."GroupId") as participants,
		(SELECT "count"(*) FROM fb_image_info 		
		JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		WHERE fb_group."GroupId" = fb_user."GroupId") as "ImageNum",
		(SELECT COALESCE("sum"("Point"), 0) FROM fb_image_info 		
		JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		WHERE fb_group."GroupId" = fb_user."GroupId") as "TotalPoint"
		FROM fb_group 
		WHERE fb_group."Status" = 1
		ORDER BY "TotalPoint" DESC, "ImageNum" DESC;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getlistgroup()
  OWNER TO postgres;
