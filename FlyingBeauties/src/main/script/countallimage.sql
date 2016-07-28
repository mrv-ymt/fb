-- Function: countallimage()

-- DROP FUNCTION countallimage();

CREATE OR REPLACE FUNCTION countallimage()
  RETURNS TABLE("imageNum" bigint) AS
$BODY$
BEGIN
	  RETURN query SELECT "count"(1) as "imageNum"
		FROM fb_image_info;
		
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION countallimage()
  OWNER TO postgres;
