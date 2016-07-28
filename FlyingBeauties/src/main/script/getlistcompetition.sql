-- Function: getlistcompetition(integer)

-- DROP FUNCTION getlistcompetition(integer);

CREATE OR REPLACE FUNCTION getlistcompetition(IN in_userid integer)
  RETURNS TABLE("CompetitionId" integer, "CompetitionName" character varying, "CompetitionLogoUrl" character varying, "Description" text, "BeginTime" timestamp without time zone, "EndTime" timestamp without time zone, "HotRewards" integer, "Participants" bigint, "Joined" integer, "imageNum" bigint, "groupNum" bigint, "ownImageNum" bigint) AS
$BODY$

BEGIN
	IF in_userid > 0 THEN
		RETURN QUERY SELECT fb_competition."CompetitionId", fb_competition."CompetitionName",
				fb_competition."CompetitionLogoUrl", fb_competition."Description",
				fb_competition."BeginTime", fb_competition."EndTime", fb_competition."HotRewards",
				(SELECT "count"(*) FROM fb_competition_user WHERE fb_competition."CompetitionId" = fb_competition_user."CompetitionId") as participant,
				(CASE when (SELECT "count"(*) from fb_competition_user where fb_competition_user."UserId" = in_userid 
				 and fb_competition_user."CompetitionId" = fb_competition."CompetitionId") > 0 THEN 1 else 0 end) as joined,
				(SELECT "count"(*) FROM fb_image_info WHERE fb_image_info."CompetitionId" = fb_competition."CompetitionId") as imageNum,
				(SELECT "count"(*) FROM fb_competition_group WHERE fb_competition_group."CompetitionId" = fb_competition."CompetitionId") as groupNum,
				(SELECT "count"(*) FROM fb_image_info WHERE fb_image_info."CompetitionId" = fb_competition."CompetitionId" and fb_image_info."UserId" = in_userId) as ownImageNum
				,fb_competition."TermAndCondition"	
	FROM fb_competition
			WHERE fb_competition."CompetitionStatus" = 1
			ORDER BY imageNum DESC;
	ELSE
		RETURN QUERY SELECT fb_competition."CompetitionId", fb_competition."CompetitionName",
				fb_competition."CompetitionLogoUrl", fb_competition."Description",
				fb_competition."BeginTime", fb_competition."EndTime", fb_competition."HotRewards",
				(SELECT count(*) FROM fb_competition_user WHERE fb_competition."CompetitionId" = fb_competition_user."CompetitionId") as participant,
				0 as joined,
				(SELECT "count"(*) FROM fb_image_info WHERE fb_image_info."CompetitionId" = fb_competition."CompetitionId") as imageNum,
				(SELECT "count"(*) FROM fb_competition_group WHERE fb_competition_group."CompetitionId" = fb_competition."CompetitionId") as groupNum,
				0::bigint as ownImageNum, fb_competition."TermAndCondition"
			FROM fb_competition
			WHERE fb_competition."CompetitionStatus" = 1
			ORDER BY imageNum DESC;
	END IF;

END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getlistcompetition(integer)
  OWNER TO postgres;
