-- Function: editimage(integer, character varying, character varying, character varying, text, integer)

-- DROP FUNCTION editimage(integer, character varying, character varying, character varying, text, integer);

CREATE OR REPLACE FUNCTION editimage(
    in_imageid integer,
    in_lng character varying,
    in_lat character varying,
    in_locationname character varying,
    in_description text,
    "in_speciesId" integer)
  RETURNS void AS
$BODY$
BEGIN
	UPDATE fb_image_info SET "Lng" = in_lng, "Lat" = in_lat, 
		"LocationName" = in_locationName, "Description" = in_description, "SpeciesId" = "in_speciesId"
	WHERE "ImageId" = in_imageid;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION editimage(integer, character varying, character varying, character varying, text, integer)
  OWNER TO postgres;
