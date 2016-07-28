-- Function: editcomment(integer, text)

-- DROP FUNCTION editcomment(integer, text);

CREATE OR REPLACE FUNCTION editcomment(
    in_commentid integer,
    in_comment text)
  RETURNS void AS
$BODY$
BEGIN
	UPDATE fb_comment SET "Comment" = in_comment
	WHERE "CommentId" = in_commentid;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION editcomment(integer, text)
  OWNER TO postgres;
