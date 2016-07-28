-- Function: getuserbyid(integer)

-- DROP FUNCTION getuserbyid(integer);

CREATE OR REPLACE FUNCTION getuserbyid(IN in_userid integer)
  RETURNS TABLE("userId" integer, "Firstname" character varying, "Lastname" character varying, "Country" character varying, "City" character varying, "Email" character varying, "PhoneNumber" character varying, "AvatarUrl" character varying, "Birthday" character varying, "ImageNum" bigint, "SumPoint" bigint, "Username" character varying) AS
$BODY$ 
BEGIN
RETURN QUERY
		select fb_user."UserId", fb_user."FirstName", fb_user."LastName",
				fb_user."Country", fb_user."City", fb_user."Email", fb_user."PhoneNumber",
				fb_user."AvatarUrl", fb_user."Birthday", 
				"count"(fb_image_info."ImageId") as "ImageNum", COALESCE ("sum"("Point"), 0) as "SumPoint",
				fb_user."Username"
 from fb_user	
left join fb_image_info on fb_image_info."UserId" = fb_user."UserId"
where fb_user."UserId" = "in_userid"
GROUP BY fb_user."UserId",  fb_user."FirstName", fb_user."LastName",
				fb_user."Country", fb_user."City", fb_user."Email", fb_user."PhoneNumber",
				fb_user."AvatarUrl", fb_user."Birthday",fb_user."Username";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getuserbyid(integer)
  OWNER TO postgres;
