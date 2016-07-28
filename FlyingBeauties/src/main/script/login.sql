-- Function: login(character varying, character varying)

-- DROP FUNCTION login(character varying, character varying);

CREATE OR REPLACE FUNCTION login(
    IN in_username character varying,
    IN in_password character varying)
  RETURNS TABLE("userId" integer, "Username" character varying, "Firstname" character varying, "Lastname" character varying, "Country" character varying, "City" character varying, "Email" character varying, "PhoneNumber" character varying, "RoleId" integer, "GroupId" integer, "AvatarUrl" character varying, "Birthday" character varying, "ImageNum" bigint, "SumPoint" bigint) AS
$BODY$ 
BEGIN
RETURN QUERY
		select fb_user."UserId", fb_user."Username", fb_user."FirstName", fb_user."LastName",
				fb_user."Country", fb_user."City", fb_user."Email", fb_user."PhoneNumber",
				fb_user."RoleId", fb_user."GroupId", fb_user."AvatarUrl", fb_user."Birthday", 
				"count"(fb_image_info."ImageId") as "ImageNum", COALESCE ("sum"("Point"), 0) as "SumPoint",
				fb_user."PreferredLanguage"
 from fb_user	
		left join fb_image_info on fb_image_info."UserId" = fb_user."UserId"
    where ((fb_user."Username" = in_username OR fb_user."Email" = in_username) 
			AND fb_user."Password" = in_password)
      OR (fb_user."FacebookId" = in_username AND fb_user."FacebookToken" = in_password)
			OR (fb_user."GoogleId" = in_username AND fb_user."GoogleToken" = in_password)
GROUP BY fb_user."UserId", fb_user."Username", fb_user."FirstName", fb_user."LastName",
				fb_user."Country", fb_user."City", fb_user."Email", fb_user."PhoneNumber",
				fb_user."RoleId", fb_user."GroupId", fb_user."AvatarUrl", fb_user."Birthday";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION login(character varying, character varying)
  OWNER TO postgres;
