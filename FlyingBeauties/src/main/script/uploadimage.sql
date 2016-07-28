-- Function: uploadimage(integer, integer, character varying, character varying, character varying, character varying, integer, text, integer, integer, timestamp without time zone, character varying)

-- DROP FUNCTION uploadimage(integer, integer, character varying, character varying, character varying, character varying, integer, text, integer, integer, timestamp without time zone, character varying);

CREATE OR REPLACE FUNCTION uploadimage(
    in_userid integer,
    in_competitionid integer,
    in_imageurl character varying,
    in_lng character varying,
    in_lat character varying,
    in_locationname character varying,
    in_speciesid integer,
    in_description text,
    in_point integer,
    in_imagestatus integer,
    in_uploadtime timestamp without time zone,
    in_originalimagename character varying)
  RETURNS void AS
$BODY$
BEGIN
	insert into fb_image_info("UserId", "CompetitionId", "ImageUrl", "Lng", "Lat",
	"LocationName", "SpeciesId",  "Description", "Point", "ImageStatus", "UploadTime", "OriginalImageName")
	VALUES (in_userid, in_competitionId, in_imageurl, in_lng, in_lat, 
	in_locationname, in_speciesid, in_description, in_point, in_imageStatus, in_uploadtime, in_originalimagename);
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION uploadimage(integer, integer, character varying, character varying, character varying, character varying, integer, text, integer, integer, timestamp without time zone, character varying)
  OWNER TO postgres;
