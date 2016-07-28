-- Function: getlistgroup_competition(integer)

-- DROP FUNCTION getlistgroup_competition(integer);

CREATE OR REPLACE FUNCTION getlistgroup_competition(IN in_competitionid integer)
  RETURNS TABLE("groupId" integer, "groupName" character varying, "groupLogoUrl" character varying, "Description" text, participants bigint, "ImageNum" bigint, "TotalPoint" bigint) AS
$BODY$
BEGIN
RETURN QUERY
		SELECT fb_group."GroupId", fb_group."GroupName", fb_group."GroupLogoUrl", 
		fb_group."Description",			
		(SELECT "count"(*) FROM fb_user 
		JOIN fb_competition_user ON fb_competition_user."UserId" = fb_user."UserId"
		WHERE fb_user."GroupId" = fb_group."GroupId" AND fb_competition_user."CompetitionId" = in_competitionid) as participants,
		(SELECT "count"(*) FROM fb_image_info 		
		JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		WHERE fb_group."GroupId" = fb_user."GroupId" and fb_image_info."CompetitionId" = in_competitionid) as "ImageNum",
		(SELECT "sum"("Point") FROM fb_image_info 		
		JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		WHERE fb_group."GroupId" = fb_user."GroupId" and fb_image_info."CompetitionId" = in_competitionid) as "TotalPoint"
		FROM fb_group
		JOIN fb_competition_group ON fb_competition_group."GroupId" = fb_group."GroupId"
		WHERE fb_competition_group."CompetitionId" = in_competitionid
		ORDER BY "TotalPoint" DESC, "ImageNum" DESC;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getlistgroup_competition(integer)
  OWNER TO postgres;
