-- Function: getuser(character varying, character varying)

-- DROP FUNCTION getuser(character varying, character varying);

CREATE OR REPLACE FUNCTION getuser(
    in_username character varying,
    in_email character varying)
  RETURNS fb_user AS
$BODY$
		select * from fb_user		
    where (fb_user."Username" = in_username OR fb_user."Email" = in_email) 
$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION getuser(character varying, character varying)
  OWNER TO postgres;
