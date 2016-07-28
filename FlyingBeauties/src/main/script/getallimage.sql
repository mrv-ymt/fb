CREATE OR REPLACE FUNCTION getallimage(IN in_ishotimage boolean, IN in_pagenumber integer, IN in_pagesize integer) RETURNS TABLE(imageid integer, competitionname character varying, "competitionId" integer, uploadtime timestamp without time zone, point integer, imageurl character varying, lng character varying, lat character varying, locationname character varying, speciesid integer, description text, userid integer, firstname character varying, avatarurl character varying, "commentNum" bigint, "GroupName" character varying, speciesname character) AS
$BODY$
BEGIN
IF in_isHotImage = TRUE THEN 
  RETURN QUERY SELECT "ImageId", "CompetitionName", fb_image_info."CompetitionId", "UploadTime", "Point",
   "ImageUrl","Lng", "Lat", "LocationName", "SpeciesId", fb_image_info."Description", fb_user."UserId", fb_user."FirstName",
   fb_user."AvatarUrl", (SELECT count(*) FROM fb_comment WHERE fb_comment."ImageId" = fb_image_info."ImageId") AS commentNumber,
   fb_group."GroupName",
   (select fb_specieslist."taxonName" from fb_specieslist where fb_specieslist.langiso3='qqq' 
   and fb_specieslist."taxonId" = fb_image_info."SpeciesId"
   and fb_specieslist."CompetitionId" = fb_image_info."CompetitionId") as taxonName
  FROM fb_image_info
  JOIN fb_competition ON fb_competition."CompetitionId" = fb_image_info."CompetitionId"
  JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
  JOIN fb_group ON fb_group."GroupId" = fb_user."GroupId"
  ORDER BY "Point" DESC
  LIMIT in_pageSize OFFSET in_pagenumber*in_pageSize;
ELSE
 RETURN QUERY SELECT "ImageId", "CompetitionName", fb_image_info."CompetitionId", "UploadTime", "Point",
   "ImageUrl","Lng", "Lat", "LocationName", "SpeciesId", 
   fb_image_info."Description", fb_user."UserId", fb_user."FirstName",
   fb_user."AvatarUrl", (SELECT "count"(*) FROM fb_comment WHERE fb_comment."ImageId" = fb_image_info."ImageId") AS commentNumber,
   fb_group."GroupName",
   (select fb_specieslist."taxonName" from fb_specieslist where fb_specieslist.langiso3='qqq' 
   and fb_specieslist."taxonId" = fb_image_info."SpeciesId"
   and fb_specieslist."CompetitionId" = fb_image_info."CompetitionId") as taxonName
  FROM fb_image_info
  JOIN fb_competition ON fb_competition."CompetitionId" = fb_image_info."CompetitionId"
  JOIN fb_user ON fb_user."UserId" = fb_image_info."UserId"
  JOIN fb_group ON fb_group."GroupId" = fb_user."GroupId"
  ORDER BY "UploadTime" DESC
  LIMIT in_pageSize OFFSET in_pagenumber*in_pageSize;
END IF;
END;
$BODY$
LANGUAGE plpgsql VOLATILE NOT LEAKPROOF
COST 100
ROWS 1000;
