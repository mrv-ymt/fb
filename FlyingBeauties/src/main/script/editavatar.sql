-- Function: editavatar(integer, character varying)

-- DROP FUNCTION editavatar(integer, character varying);

CREATE OR REPLACE FUNCTION editavatar(
    in_userid integer,
    in_avatarurl character varying)
  RETURNS void AS
$BODY$

	UPDATE fb_user SET "AvatarUrl" = in_AvatarUrl
	WHERE "UserId" = in_userId	
$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION editavatar(integer, character varying)
  OWNER TO postgres;
