-- Function: deletecomment(integer)

-- DROP FUNCTION deletecomment(integer);

CREATE OR REPLACE FUNCTION deletecomment(in_commentid integer)
  RETURNS void AS
$BODY$
BEGIN
	DELETE FROM fb_comment 
	WHERE "CommentId" = in_commentid;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION deletecomment(integer)
  OWNER TO postgres;
