/**
 * 
 */
package com.s4you.flybeau.utils;

/**
 * 
 * ConstantUtil 
 * Date: 11/05/2016 
 * ThienMV
 * 
 * */
public class ConstantUtil {
	
	public static String INIT_STRING = "";
	public static String ZERO_STRING = "0";
	public static int ZERO = 0;
	public static int INIT_PAGING = 1;
	public static int DISABLE_PAGING_FLAG = 0;
	public static int PAGE_SIZE_1 = 15;
	public static int PAGE_SIZE_2 = 12;
	public static int PAGE_SIZE_3 = 10;
	public static int PAGE_SIZE_MAX = 1000;
	public static int POINT_INIT = 0;
	public static int MAXPAGEDISPLAY = 5;	
	
	/* USER Status */
	public static final int DEACTIVATE_USER = 0;
	public static final int ACTIVATE_USER = 1;
	
	/* Joined Code */
	public static final int NOT_JOIN = 0;
	public static final int JOINED = 1;
	
	/* Status Check API Token */
	public static final int ACCESS_DENIED = 0;
	public static final int ACCESS_ACCEPTED = 1;
	
	
	/* Return Code */
	public static final int RETCODE_NORMAL = 1;
	public static final int RETCODE_ABNORMAL = 0;
	public static final int RETCODE_NOAUTHENCATION = 3;
	public static final int RETCODE_ERRORDATE = 2;
	public static final int RETCODE_SENDMAIL_ERROR = 4;
	
	/* Role Access Site */
	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_USER = 2;
	public static final int ROLE_MOD = 3;
	
	/* Key Properties to send mail  */
	public static final String PROPERTIES_HOST = "HOST";
	public static final String SYSTEM_PROPERTIES_FILE_NAME = "conf-application";
	public static final String PATH_NONE_AVATAR_IMG = "none-avatar.png";
	public static final String PATH_NONE_LOGO_IMG = "nophoto.jpg";
	public static final String PROPERTY_MAIL_TITLE_RESETPASSWD = "mail.title.resetpassword";
	public static final String PROPERTY_MAIL_ADDRESS = "mail.mailAddress";
	public static final String PROPERTY_MAIL_PASSWORD = "mail.password";
	public static final String PROPERTY_MAIL_SMTP_AUTH = "mail.smtp.auth";
	public static final String PROPERTY_MAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
	public static final String PROPERTY_MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String PROPERTY_MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String PROPERTY_MAIL_CONTENT = "mail.content.resetpassword";
	
	/* Key Properties path save image */
	public static final String PROPERTIES_LOCAL_PATH_PARENT_FOLDER = "LOCAL_PATH_PARENT_FOLDER";
	public static final String PROPERTIES_LOCAL_PATH_PREVIEW_FOLDER = "LOCAL_PATH_PREVIEW_FOLDER";
	public static final String PROPERTIES_LOCAL_PATH_RAW_FOLDER = "LOCAL_PATH_RAW_FOLDER";
	public static final String PROPERTIES_LOCAL_PATH_AVATAR = "LOCAL_PATH_AVATAR";
	public static final String PROPERTIES_PATH_IMAGEMAGICK = "PATH_IMAGEMAGICK"; 
	
	/* Size of image properties */
	public static final String PROPERTIES_THUMBNAIL_IMAGE_SIZE = "thumbnail.size";
	public static final String PROPERTIES_MEDIUM_IMAGE_SIZE = "mediumimage.size";
	public static final String PROPERTIES_BIG_IMAGE_SIZE = "bigimage.size";
	public static final String PROPERTIES_ICON_IMAGE_SIZE = "icon.size";
	public static final String PROPERTIES_AVARTAR_SIZE = "avatar.size";
	
	/* Type to get list image */
	public static final int TYPE_ALLIMAGE = 1;
	public static final int TYPE_HOTIMAGE = 2;
	public static final int TYPE_OWNIMAGE_COMPETITION = 3;
	public static final int TYPE_COMPETITONIMAGE = 4;
	public static final int TYPE_GROUPIMAGE = 5;
	public static final int TYPE_ALLOWNIMAGE = 6;
	
	/* Order by ID */
	public static final int ORDERBY_DATE = 1;
	public static final int ORDERBY_SPECIES = 2;
	public static final int ORDERBY_LOCATION = 3;
	
	/* Key in Map */
	public static final String KEY_TYPE = "type";
	public static final String KEY_BEGINNUM = "beginNum";
	public static final String KEY_PAGESIZE = "pageSize";
	public static final String KEY_COMPETITIONID = "competitionId";
	public static final String KEY_ORDERBY = "orderBy";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_USERID = "userId";
	public static final String KEY_LANG = "lang";
	public static final String KEY_OLDPASSWORD = "oldPassword";
	public static final String KEY_NEWPASSWORD = "newPassword";
	public static final String KEY_USERDTO = "userDTO";
	public static final String KEY_IMAGEINFOR2S = "imageInfoR2S";
	public static final String KEY_IMAGEINFO = "imageInfo";
	public static final String KEY_LISTIMAGE = "listImage";
	public static final String KEY_LISTGROUP = "listGroup";
	public static final String KEY_LIST_SELECTED_GROUP = "listSelectedGroup";
	public static final String KEY_LISTCOMPETITION = "listCompetition";
	public static final String KEY_LISTSPECIES = "listSpecies";
	public static final String KEY_GROUPINFO = "groupInfo";
	public static final String KEY_COMPETITIONINFO = "competitionInfo";
	public static final String KEY_ERROR = "error";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_NOTJOINTCPTT = "notJoinCptt";	
	public static final String KEY_LISTCOUNTRY = "listCountry";
	public static final String KEY_LISTCOUNTRYPHONECODE = "listCountryPhoneCode";
	
	/* Local path of image and avatar */
	public static final String SUFFIX_IMAGE = ".jpg";
	public static final String SUFFIX_BIG_IMAGE = "_b.jpg";
	public static final String SUFFIX_MEDIUM_IMAGE = "_m.jpg";
	public static final String SUFFIX_THUMBNAIL_IMAGE = "_t.jpg";
	public static final String SUFFIX_ICON_IMAGE = "_i.jpg";
	
	public static final String SESSION_KEY = "userInfo";
	public static final String SESSION_APITOKEN_KEY = "apiToken";
	
	/* Bean ID to configuration on dispatcher-servlet.xml*/
	public static final String BEAN_ID_DATASOURCEBEAN = "dataSource";
	
	/* User Management API URL*/
	public static final String URL_GETUSERINFO = "/api/user/";
	public static final String URL_LOGIN = "/api/user/login";
	public static final String URL_LOGIN_FB = "/api/user/loginfb";
	public static final String URL_REGISTER = "/api/user/register";
	public static final String URL_EDITPROFILE = "/api/user/editprofile";
	public static final String URL_EDITAVATAR = "/api/user/editavatar";
	public static final String URL_RESETPASSWORD = "/api/user/resetpasswd/";
	public static final String URL_CHANGEPASSWORD= "/api/user/changepasswd";
	public static final String URL_GETTOPUSERGROUP = "/api/user/gettopusergroup/";
	public static final String URL_GETTOPUSERCOMPETITION = "/api/user/gettopusercompetition/";
	public static final String URL_GETLISTCOUNTRY = "/api/user/listCountry";
	public static final String URL_GETLISTCOUNTRYPHONECODE = "/api/user/listCountryPhoneCode";
	public static final String URL_SAVELANG = "/api/user/savelang";
	
	/* Image Management API URL */
	public static final String URL_GETLISTIMAGE = "/api/image/getlistimage";
	public static final String URL_UPLOADIMAGE = "/api/image/uploadimage";
	public static final String URL_EDITIMAGE = "/api/image/editimage";
	public static final String URL_DELETEIMAGE = "/api/image/delete/";
	public static final String URL_GETIMAGE = "/api/image/";	
	public static final String URL_GETIMAGENUMBER = "/api/image/getimagepagenumber";
	
	
	/* Competition Management API URL */
	public static final String URL_GETLISTCPTT = "/api/competition/getlist/";
	public static final String URL_JOINCPTT = "/api/competition/join/";
	public static final String URL_GETLISTGROUP = "/api/competition/getlistgroup";
	public static final String URL_GETTOPGROUP = "/api/competition/gettopgroup/";
	public static final String URL_GETGROUPINFO = "/api/competition/getgroupinfo/";
	public static final String URL_GETCOMPETITIONINFO = "/api/competition/getcompetitioninfo/";
	
	/* Comment Management API URL */
	public static final String URL_GETLISTCOMMENT = "/api/comment/getlist/";
	public static final String URL_ADDCOMMENT = "/api/comment/add";
	public static final String URL_EDITCOMMENT = "/api/comment/edit";
	public static final String URL_DELETECOMMENT = "/api/comment/delete/";
	
	/* Species Management API URL */
	public static final String URL_GETLISTSPECIES = "/api/species/list";
}