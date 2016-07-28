-- Function: resetpassword(integer, character varying)

-- DROP FUNCTION resetpassword(integer, character varying);

CREATE OR REPLACE FUNCTION resetpassword(
    in_userid integer,
    in_newpassword character varying)
  RETURNS void AS
$BODY$
		UPDATE fb_user SET "Password" = in_newpassword
		WHERE "UserId" = in_userId
$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION resetpassword(integer, character varying)
  OWNER TO postgres;
