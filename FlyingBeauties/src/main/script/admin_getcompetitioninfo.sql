-- Function: admin_getcompetitioninfo(integer)

-- DROP FUNCTION admin_getcompetitioninfo(integer);

CREATE OR REPLACE FUNCTION admin_getcompetitioninfo(IN "in_competitionId" integer)
  RETURNS TABLE("CompetitionId" integer, "CompetitionName" character varying, "CompetitionLogoUrl" character varying, "Description" text, "BeginTime" timestamp without time zone, "EndTime" timestamp without time zone, "HotRewards" integer, "Participants" bigint, "imageNum" bigint, "groupNum" bigint, "CompetitionStatus" integer, "CompetitionRewards" character varying, "TermAndCondition" text) AS
$BODY$
BEGIN
	RETURN QUERY
		SELECT fb_competition."CompetitionId", fb_competition."CompetitionName",
			fb_competition."CompetitionLogoUrl", fb_competition."Description",
			fb_competition."BeginTime", fb_competition."EndTime", fb_competition."HotRewards",
			(SELECT "count"(*) FROM fb_competition_user WHERE fb_competition."CompetitionId" = fb_competition_user."CompetitionId") as participant,
			(SELECT "count"(*) FROM fb_image_info WHERE fb_image_info."CompetitionId" = fb_competition."CompetitionId") as imageNum,
			(SELECT "count"(*) FROM fb_competition_group WHERE fb_competition_group."CompetitionId" = fb_competition."CompetitionId") as groupNum,
			fb_competition."CompetitionStatus",fb_competition."CompetitionRewards", fb_competition."TermAndCondition"		
		FROM fb_competition
		WHERE fb_competition."CompetitionId" = "in_competitionId";

END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION admin_getcompetitioninfo(integer)
  OWNER TO postgres;
