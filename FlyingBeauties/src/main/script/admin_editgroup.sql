-- Function: admin_editgroup(integer, character varying, character varying, text)

-- DROP FUNCTION admin_editgroup(integer, character varying, character varying, text);

CREATE OR REPLACE FUNCTION admin_editgroup(
    "in_GroupId" integer,
    "in_GroupName" character varying,
    "in_GroupLogoUrl" character varying,
    "in_Description" text)
  RETURNS void AS
$BODY$
BEGIN
	UPDATE fb_group SET 
			"GroupName" = "in_GroupName", 
			"GroupLogoUrl" = "in_GroupLogoUrl", 
			"Description" = "in_Description"
	WHERE "GroupId" = "in_GroupId";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_editgroup(integer, character varying, character varying, text)
  OWNER TO postgres;
