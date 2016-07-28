-- Function: admin_editcompetition2(integer, character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, text)

-- DROP FUNCTION admin_editcompetition2(integer, character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, text);

CREATE OR REPLACE FUNCTION admin_editcompetition2(
    "in_CompetitionId" integer,
    "in_CompetitionName" character varying,
    "in_CompetitionLogoUrl" character varying,
    "in_HotRewards" integer,
    "in_CompetitionRewards" character varying,
    "in_Description" text,
    "in_BeginTime" timestamp without time zone,
    "in_EndTime" timestamp without time zone,
    "in_TermAndCondition" text,
    "in_InitPoint" integer,
    in_groupids integer[])
  RETURNS void AS
$BODY$
DECLARE
   groupId   integer;
BEGIN
	DELETE FROM fb_competition_group WHERE "CompetitionId" = "in_CompetitionId";
    FOREACH groupId IN ARRAY in_groupids
    LOOP
        INSERT INTO fb_competition_group ("GroupId", "CompetitionId") VALUES (groupId, "in_CompetitionId");
    END LOOP;
	UPDATE fb_competition SET 
			"CompetitionName" = "in_CompetitionName", 
			"CompetitionLogoUrl" = "in_CompetitionLogoUrl", 
			"HotRewards" = "in_HotRewards",
			"CompetitionRewards" = "in_CompetitionRewards",
			"Description" = "in_Description", 
			"BeginTime" = "in_BeginTime", 
			"EndTime" = "in_EndTime",
			"TermAndCondition"= "in_TermAndCondition",
			"InitPoint" = "in_InitPoint"
	WHERE "CompetitionId" = "in_CompetitionId";
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION admin_editcompetition2(integer, character varying, character varying, integer, character varying, text, timestamp without time zone, timestamp without time zone, text)
  OWNER TO postgres;
  -- Test
 select * from admin_editcompetition2(11,'Test competition','dragonfly2.jpg',1,'Cookies','','2016-05-10 00:00:00','2016-05-09 15:49:45','',1, array[1,2,3,4]);
