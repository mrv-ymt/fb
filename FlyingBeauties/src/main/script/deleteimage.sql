-- Function: deleteimage(integer)

-- DROP FUNCTION deleteimage(integer);

CREATE OR REPLACE FUNCTION deleteimage(in_imageid integer)
  RETURNS void AS
$BODY$
BEGIN
	
	DELETE FROM fb_comment where "ImageId" = in_imageid;
	DELETE FROM fb_image_info where "ImageId" = in_imageid;
	EXCEPTION WHEN OTHERS THEN
	ROLLBACK;
	COMMIT;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION deleteimage(integer)
  OWNER TO postgres;
