-- Function: getlistcomment(integer)

-- DROP FUNCTION getlistcomment(integer);

CREATE OR REPLACE FUNCTION getlistcomment(IN in_imageid integer)
  RETURNS TABLE("commentId" integer, comment text, "commentTime" timestamp without time zone, "imageId" integer, userid integer, firstname character varying, avatarurl character varying) AS
$BODY$
BEGIN
	RETURN QUERY 
		SELECT "CommentId", "Comment", "CommentTime", "ImageId",
						fb_user."UserId", "FirstName", "AvatarUrl" 
		FROM fb_comment
		JOIN fb_user ON fb_user."UserId" = fb_comment."UserId"
		WHERE "ImageId" = in_imageid
		ORDER BY "CommentTime" ASC;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getlistcomment(integer)
  OWNER TO postgres;
