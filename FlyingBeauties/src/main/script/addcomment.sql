-- Function: addcomment(integer, text, integer, timestamp without time zone)

-- DROP FUNCTION addcomment(integer, text, integer, timestamp without time zone);

CREATE OR REPLACE FUNCTION addcomment(
    in_imageid integer,
    in_comment text,
    in_userid integer,
    in_commenttime timestamp without time zone)
  RETURNS void AS
$BODY$
BEGIN
	insert into fb_comment("ImageId", "Comment", "UserId", "CommentTime")
	VALUES (in_imageid, in_comment, in_userId, in_commenttime);
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION addcomment(integer, text, integer, timestamp without time zone)
  OWNER TO postgres;
