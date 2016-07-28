-- Function: editprofile(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying)

-- DROP FUNCTION editprofile(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION editprofile(
    in_userid integer,
    in_firstname character varying,
    in_lastname character varying,
    in_email character varying,
    in_phonenumber character varying,
    in_country character varying,
    in_city character varying,
    in_birthday character varying)
  RETURNS void AS
$BODY$

	UPDATE fb_user SET "FirstName" = in_firstname, "LastName" = in_lastname, "Email" = in_email, 
	"PhoneNumber" = in_phonenumber, "Country" = in_country, "City" = in_city, "Birthday" = in_birthday
	WHERE "UserId" = in_userId	
$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION editprofile(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying)
  OWNER TO postgres;
