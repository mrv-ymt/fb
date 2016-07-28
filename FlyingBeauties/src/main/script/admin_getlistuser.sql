-- Function: admin_getlistuser(integer, integer)

-- DROP FUNCTION admin_getlistuser(integer, integer);

CREATE OR REPLACE FUNCTION admin_getlistuser(
    IN in_pagenumber integer,
    IN in_pagesize integer)
  RETURNS TABLE("userId" integer, "Firstname" character varying, "Lastname" character varying, "avatarUrl" character varying, "imageNum" bigint, "SumPoint" bigint, "GroupName" character varying, "Status" smallint, "Email" character varying, "Username" character varying, "Country" character varying) AS
$BODY$
BEGIN
RETURN QUERY
		SELECT fb_user."UserId", "FirstName" , "LastName", "AvatarUrl", "count"(fb_image_info."ImageId") as "ImageNum",
		COALESCE ("sum"("Point"), 0) as "SumPoint", fb_group."GroupName", fb_user."Status", 
		fb_user."Email", fb_user."Username", fb_user."Country"
		from fb_image_info
		RIGHT JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		LEFT JOIN fb_group ON fb_group."GroupId" = fb_user."GroupId"
		GROUP BY fb_user."UserId", "FirstName", "LastName", "AvatarUrl", fb_group."GroupName", 
		fb_user."Status", fb_user."Email", fb_user."Username"
		ORDER BY "FirstName" ASC, "LastName" ASC
		LIMIT in_pageSize OFFSET in_pagenumber*in_pageSize;

END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION admin_getlistuser(integer, integer)
  OWNER TO postgres;
