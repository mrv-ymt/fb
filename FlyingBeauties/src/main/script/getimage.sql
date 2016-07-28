-- Function: getimage(integer)

-- DROP FUNCTION getimage(integer);

CREATE OR REPLACE FUNCTION getimage(IN in_imageid integer)
  RETURNS TABLE(imageid integer, competitionname character varying, "competitionId" integer, uploadtime timestamp without time zone, point integer, imageurl character varying, lng character varying, lat character varying, locationname character varying, speciesid integer, description text, userid integer, firstname character varying, avatarurl character varying) AS
$BODY$
BEGIN
	RETURN QUERY SELECT "ImageId", "CompetitionName", fb_image_info."CompetitionId", "UploadTime", "Point",
			"ImageUrl", "Lng", "Lat", "LocationName", "SpeciesId", fb_image_info."Description", fb_user."UserId", fb_user."FirstName",
			fb_user."AvatarUrl"	
		FROM fb_image_info
		JOIN fb_competition ON fb_competition."CompetitionId" = fb_image_info."CompetitionId"
		JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
		WHERE "ImageId" = in_imageId
		LIMIT 1;

END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getimage(integer)
  OWNER TO postgres;
