-- Function: admin_addcompetition(character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, timestamp without time zone, text)

-- DROP FUNCTION admin_addcompetition(character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, timestamp without time zone, text);

CREATE OR REPLACE FUNCTION admin_addcompetition(
    "in_CompetitionName" character varying,
    "in_CompetitionLogoUrl" character varying,
    "in_HotRewards" integer,
    "in_CompetitionRewards" character varying,
    "in_Description" text,
    "in_BeginTime" timestamp without time zone,
    "in_EndTime" timestamp without time zone,
    "in_InsertTime" timestamp without time zone,
    "in_TermAndCondition" text)
  RETURNS void AS
$BODY$
BEGIN
	insert into fb_competition("CompetitionName", "CompetitionStatus", "CompetitionLogoUrl", "HotRewards",
 "CompetitionRewards", "Description", "BeginTime", "EndTime", "InsertTime", "TermAndCondition")
	VALUES ("in_CompetitionName", 1, "in_CompetitionLogoUrl", "in_HotRewards", "in_CompetitionRewards",
"in_Description", "in_BeginTime", "in_EndTime", "in_InsertTime", "in_TermAndCondition");
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_addcompetition(character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, timestamp without time zone, text)
  OWNER TO postgres;
