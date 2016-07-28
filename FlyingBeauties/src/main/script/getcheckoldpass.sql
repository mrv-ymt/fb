-- Function: getcheckoldpass(integer, character varying)

-- DROP FUNCTION getcheckoldpass(integer, character varying);

CREATE OR REPLACE FUNCTION getcheckoldpass(
    "in_userId" integer,
    in_password character varying)
  RETURNS fb_user AS
$BODY$
		select * from fb_user		
    where (fb_user."UserId" = "in_userId" AND fb_user."Password" = "in_password") 
$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION getcheckoldpass(integer, character varying)
  OWNER TO postgres;
