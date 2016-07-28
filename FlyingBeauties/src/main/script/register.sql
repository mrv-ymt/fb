-- Function: register(character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, timestamp without time zone, character varying, character varying)

-- DROP FUNCTION register(character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, timestamp without time zone, character varying, character varying);

CREATE OR REPLACE FUNCTION register(
    in_username character varying,
    in_password character varying,
    in_country character varying,
    in_city character varying,
    in_roleid integer,
    in_groupid integer,
    in_phonenumber character varying,
    in_firstname character varying,
    in_lastname character varying,
    in_email character varying,
    in_registime timestamp without time zone,
    in_birthday character varying,
    "in_avatarUrl" character varying)
  RETURNS fb_user AS
$BODY$

	INSERT INTO fb_user("Username", "Password", "Country", "City", "RoleId", 
	"GroupId", "PhoneNumber", "FirstName", "LastName", "Email", "RegistrationTime", "Birthday", "AvatarUrl")
	VALUES(in_username, in_password, in_country, in_city, in_roleId, in_groupid, in_phonenumber, in_firstname, in_lastname, in_email,
in_regisTime, in_birthday, "in_avatarUrl");

	select * from fb_user		
    where fb_user."Username" = in_username


$BODY$
  LANGUAGE sql VOLATILE
  COST 100;
ALTER FUNCTION register(character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, timestamp without time zone, character varying, character varying)
  OWNER TO postgres;
