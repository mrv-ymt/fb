-- Function: getgroupinfo(integer)

-- DROP FUNCTION getgroupinfo(integer);

CREATE OR REPLACE FUNCTION getgroupinfo(IN in_groupid integer)
  RETURNS TABLE("groupId" integer, "groupName" character varying, "groupLogoUrl" character varying, "Description" text, participants bigint, "ImageNum" bigint, "TotalPoint" bigint) AS
$BODY$
BEGIN
RETURN QUERY
		SELECT fb_group."GroupId", fb_group."GroupName", fb_group."GroupLogoUrl", 
		fb_group."Description",	(SELECT "count"(*) FROM fb_user WHERE fb_user."GroupId" = in_groupId) as participants,
		(SELECT count(*) FROM fb_image_info JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		WHERE fb_user."GroupId" = in_groupId) as "ImageNum",
		(SELECT sum("Point") FROM fb_image_info JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		WHERE fb_user."GroupId" = in_groupId) as "TotalPoint"
		FROM fb_group
		WHERE "GroupId" = in_groupId;
		
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getgroupinfo(integer)
  OWNER TO postgres;
