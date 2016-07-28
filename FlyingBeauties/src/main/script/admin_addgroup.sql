-- Function: admin_addgroup(character varying, character varying, text, timestamp without time zone)

-- DROP FUNCTION admin_addgroup(character varying, character varying, text, timestamp without time zone);

CREATE OR REPLACE FUNCTION admin_addgroup(
    "in_GroupName" character varying,
    "in_GroupLogoUrl" character varying,
    "in_Description" text,
    "in_InsertTime" timestamp without time zone)
  RETURNS void AS
$BODY$
BEGIN
	INSERT INTO fb_group ("GroupName", "GroupLogoUrl", "Description", "InsertTime", "Status")
	VALUES ("in_GroupName", "in_GroupLogoUrl", "in_Description", "in_InsertTime", 1);
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_addgroup(character varying, character varying, text, timestamp without time zone)
  OWNER TO postgres;
