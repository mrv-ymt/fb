-- Function: savelang(integer, character varying)

-- DROP FUNCTION savelang(integer, character varying);

CREATE OR REPLACE FUNCTION savelang(
    in_userid integer,
    in_lang character varying)
  RETURNS void AS
$BODY$
		UPDATE fb_user SET "PreferredLanguage" = in_lang
		WHERE "UserId" = in_userId
$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION savelang(integer, character varying)
  OWNER TO postgres;
