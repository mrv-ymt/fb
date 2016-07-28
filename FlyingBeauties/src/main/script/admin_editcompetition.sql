-- Function: admin_editcompetition(integer, character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, text)

-- DROP FUNCTION admin_editcompetition(integer, character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, text);

CREATE OR REPLACE FUNCTION admin_editcompetition(
    "in_CompetitionId" integer,
    "in_CompetitionName" character varying,
    "in_CompetitionLogoUrl" character varying,
    "in_HotRewards" integer,
    "in_CompetitionRewards" character varying,
    "in_Description" text,
    "in_BeginTime" timestamp without time zone,
    "in_EndTime" timestamp without time zone,
    "in_TermAndCondition" text)
  RETURNS void AS
$BODY$
BEGIN
	UPDATE fb_competition SET 
			"CompetitionName" = "in_CompetitionName", 
			"CompetitionLogoUrl" = "in_CompetitionLogoUrl", 
			"HotRewards" = "in_HotRewards",
			"CompetitionRewards" = "in_CompetitionRewards",
			"Description" = "in_Description", 
			"BeginTime" = "in_BeginTime", 
			"EndTime" = "in_EndTime",
			"TermAndCondition"= "in_TermAndCondition"
	WHERE "CompetitionId" = "in_CompetitionId";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_editcompetition(integer, character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, text)
  OWNER TO postgres;
