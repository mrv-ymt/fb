$(document).ready(function(){

	var lang = $("#lang").val();

	/* Error Message */
	ERRMSG01_alphabetNumber = (lang == "vi") ?  "Hãy nhập chữ và số." : "Please use only alphanumeric or alphabetic characters.";
	ERRMSG02_alphabet = (lang == "vi") ?  "Hãy nhập chữ cái." : "Please use only alphabetic characters.";
	ERRMSG03_phonenumber = (lang == "vi") ?  "Hãy nhập số." : "Please use only number characters.";
	ERRMSG04_invalidUserNameEmail = (lang == "vi") ?  "Tài khoản/Email chưa đúng." : "Invalid Username/Email or Password.";
	ERRMSG05_duplicateUserNameEmail = (lang == "vi") ?  "Tài khoản hoặc Email đã tồn tại." : "Username or Email is duplicated.";
	ERRMSG06_notExistsmail = (lang == "vi") ?  "Email không tồn tại." : "Email is not exists.";
	ERRMSG07_duplicateEmail = (lang == "vi") ?  "Email đã tồn tại." : "Email is duplicated.";
	ERRMSG08_incorrectPass = (lang == "vi") ?  "Mật khẩu cũ chưa đúng." : "Old Password is incorrect.";
	ERRMSG09_requiredLocation = (lang == "vi") ?  "Vui lòng chọn địa điểm." : "Please Choose Location.";
	ERRMSG10_requiredFile = (lang == "vi") ?  "Vui lòng chọn hình ảnh để tải." : "Please Choose Image To Upload.";
	ERRMSG11_requiredTerm = (lang == "vi") ?  "Bạn phải đồng ý với Điều khoản Dịch vụ của Flying Beauties để đăng kí." : "You must agree to FlyingBeauties' Term of Service for registration.";
	ERRMSG12_sendMailError = (lang == "vi") ?  "Không thể gửi thông tin về thay đổi mật khẩu ở thời điểm hiện tại. Vui lòng thử lại sau!" : "Can't reset password at the moment. Please try again later!";
	ERRMSG13_requiredTermCompetition = (lang == "vi") ?  "Bạn phải đồng ý với Điều khoản Dịch vụ của cuộc thi." : "You must agree to Competition' Term of Service.";
	
	/* Message */
	ALRMSG01_deleteImage = (lang == "vi") ?  "Hình ảnh đã được xóa!" : "Image Has Been Deleted!";
	ALRMSG02_deleteComment = (lang == "vi") ?  "Bình luận đã được xóa!" : "Comment Has Been Deleted!";
	ALRMSG03_joinedCompetition = (lang == "vi") ?"Bạn đã tham gia vào cuộc thi thành công" : "You have joined on competition!";
	ALRMSG04_editAvatarSuccess = (lang == "vi") ?"Thay đổi ảnh đại diện thành công!" : "Your Avatar is edited!";
	ALRMSG05_changePassSuccess = (lang == "vi") ?"Đổi mật khẩu thành công!" : "Change Password success!";
	ALRMSG06_editProfileSuccess = (lang == "vi") ?"Thay đổi thông tin thành công!" : "Edit Profile success!";

	INFMSG01_noComment = (lang == "vi") ?  "Chưa có bình luận!" : "No Comment Yet!";
	SSMSG01_sendMailSuccess = (lang == "vi") ?  "Mật khẩu mới đã được gửi tới email của bạn. Vui lòng kiếm tra email và đăng nhập lại." : "A new password is sent to your email. Please check mail and login again.";


	/* Add Jquery Validation Method with Alphabet Number*/
	jQuery.validator.addMethod("alphabetNumber", function( value, element ) {
        var regex = new RegExp("^[a-zA-Z0-9_]+$");
        var key = value;

        if (!regex.test(key)) {
           return false;
        }
        return true;
    }, ERRMSG01_alphabetNumber);

	/* Add Jquery Validation Method with Alphabet*/
	jQuery.validator.addMethod("alphabet", function( value, element ) {
        var regex = new RegExp("^[^±!@£$%^&*_+§¡€#¢§¶•ªº«\\/<>?:;|=.,0-9]{1,30}$");
        var key = value;

        if (!regex.test(key)) {
           return false;
        }
        return true;
    }, ERRMSG02_alphabet);

	/* Add Jquery Validation Method with Phone Number*/
	jQuery.validator.addMethod("phonenumber", function( value, element ) {
        var regex = new RegExp("^[0-9]+$");
        var key = value;

        if (!regex.test(key) && value != "") {
           return false;
        }
        return true;
    }, ERRMSG03_phonenumber);

/*-------------- USER MANAGEMENT START---------------- */
	/* LoginForm Validation */
	$("#loginForm").validate({
		rules: {
			username: {
				required: true,
				minlength: 5
			},
			password: {
				required: true,
				minlength: 6
			},
		}
	});


	/* Register Form Validation */
	$("#registerForm").validate({
		rules: {
			firstName: {
				required: true,
				alphabet: true
			},
			lastName: {
				required: true,
				alphabet: true
			},
			username1: {
				required: true,
				minlength: 5,
				maxlength: 20,
				alphabetNumber: true
			},
			email: {
				required: true,
				email: true
			},
			password1: {
				required: true,
				minlength: 6,
				maxlength: 20,
				alphabetNumber: true
			},
			confirmPassword: {
				required: true,
				minlength: 6,
				maxlength: 20,
				alphabetNumber: true,
				equalTo: "#password1"
			},
			phoneNumber: {
				minlength: 7,
				maxlength: 15,
				phonenumber: true
			},
			country: {
				required: true
			},

			groupId: {
				required: true
			},
			confirmTerm: {
				required: true
			}
		},
		messages: {
			confirmTerm: {
				required: ERRMSG11_requiredTerm
			}
		},
		errorPlacement: function (error, element) {

			// Add the `help-block` class to the error element
			error.addClass("help-block");

			if ( element.prop("type") === "checkbox") {
				error.insertAfter(element.parent(".squaredThree"));
			} else {
				error.insertAfter(element.parent().find(".mandatory-asterix"));
			}

		},
		submitHandler: function() {

			var phoneNumber = $("#phoneNumber").val();

			if(phoneNumber != "") {
				countryWithPhoneCode = $("#countryPhoneCode").val();
				phoneCodeTemp = countryWithPhoneCode.split("\(")[1];
				phoneCode = phoneCodeTemp.split("\)")[0];

				for(i = 0; i < phoneNumber.length; i++) {
					if(phoneNumber.charAt(i) == '0') {
						phoneNumber = phoneNumber.substring(1);
						i--;
					} else {
						break;
					}
				}

				phoneNumber = phoneCode + " " + phoneNumber;
			}

			/* Register Handler */
			var userInfo = {
				firstName : $("#firstName").val(),
				lastName : $("#lastName").val(),
				username : $("#username1").val(),
				password : $("#password1").val(),
				email : $("#email").val(),
				phoneNumber : phoneNumber,
				country : $("#country").val(),
				city : $("#city").val(),
				birthday : $("#birthday").val()
			}

			$(".error-msg").remove();

			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : contextUrl + "/user/register",
				data : JSON.stringify(userInfo),
				success: function(data){

					if(data == 1) {
						window.location.href=contextUrl + "/competition";
					} else {
						var html = "<div class='error-msg'><span>" + ERRMSG05_duplicateUserNameEmail + "</span></div>";
						$("#firstName").parent().before(html);
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				}
			});
		}
	});


	/* Reset Password Form Validation */
	$("#resetPasswordForm").validate({
		rules: {
			emailReset: {
				required: true,
				email: true
			}
		},
		submitHandler: function() {

			$(".error-msg").remove();

			/* Register Handler */
			var url =
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : contextUrl + "/user/resetpasswd",
				data: $("#emailReset").val(),
				success: function(data){

					if(data == 1) {
						var html = "<div class='success-msg'><span>" + SSMSG01_sendMailSuccess + "</span></div>";
					} else if(data == 0){
						var html = "<div class='error-msg'><span>" + ERRMSG06_notExistsmail + "</span></div>";
					} else {
						var html = "<div class='error-msg'><span>" + ERRMSG12_sendMailError + "</span></div>";
					}

					$("#emailReset").before(html);
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				}
			});
		}
	});


	/* Edit Profile Form Validation */
	$("#editProfileForm").validate({
		rules: {
			firstName: {
				required: true,
				alphabet: true
			},
			lastName: {
				required: true,
				alphabet: true
			},
			email: {
				required: true,
				email: true
			},
			phoneNumber: {
				minlength: 7,
				maxlength: 15,
				phonenumber: true
			}
		},
		errorPlacement: function (error, element) {

			// Add the `help-block` class to the error element
			error.addClass("help-block");

			error.insertAfter(element.parent().find(".mandatory-asterix"));

		},
		submitHandler: function() {

			var phoneNumber = $("#phoneNumber").val();

			if(phoneNumber != "") {
				countryWithPhoneCode = $("#countryPhoneCode").val();
				phoneCodeTemp = countryWithPhoneCode.split("\(")[1];
				phoneCode = phoneCodeTemp.split("\)")[0];

				for(i = 0; i < phoneNumber.length; i++) {
					if(phoneNumber.charAt(i) == '0') {
						phoneNumber = phoneNumber.substring(1);
						i--;
					} else {
						break;
					}
				}

				phoneNumber = phoneCode + " " + phoneNumber;
			}

			/* Edit Handler */
			var userInfo = {
				userId : $("#loginUserId").val(),
				firstName : $("#firstName").val(),
				lastName : $("#lastName").val(),
				email : $("#email").val(),
				phoneNumber : phoneNumber,
				country : $("#country").val(),
				city : $("#city").val(),
				birthday : $("#birthday").val(),
				roleId: $("#roleId").val()
			}

			$(".error-msg").remove();

			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : contextUrl + "/user/profile",
				data : JSON.stringify(userInfo),
				success: function(data){

					if(data == 1) {
						alert(ALRMSG06_editProfileSuccess);
						window.location.href=contextUrl + "/user/profile";
					} else {
						var html = "<div class='error-msg'><span>" + ERRMSG07_duplicateEmail + "</span></div>";
						$("#firstName").parent().before(html);
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				}
			});
		}
	});


	/* Change Password Form Validation */
	$("#changePasswordForm").validate({
		rules: {

			oldPassword: {
				required: true,
				minlength: 6,
				maxlength: 20,
				alphabetNumber: true
			},
			newPassword: {
				required: true,
				minlength: 6,
				maxlength: 20,
				alphabetNumber: true
			},
			confirmPassword: {
				required: true,
				minlength: 6,
				maxlength: 20,
				alphabetNumber: true,
				equalTo: "#newPassword"
			},
			phoneNumber: {
				minlength: 10,
				maxlength: 15,
				phonenumber: true
			}
		},
		submitHandler: function() {

			/* Change Password Handler */
			var userInfo = {
				userId : $("#loginUserId").val(),
				oldPassword : $("#oldPassword").val(),
				newPassword : $("#newPassword").val(),
			}

			$(".error-msg").remove();

			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : contextUrl + "/user/changepasswd",
				data : JSON.stringify(userInfo),
				success: function(data){

					if(data == 1) {
						alert(ALRMSG05_changePassSuccess);
						window.location.href=contextUrl + "/user/profile";
					} else {
						var html = "<div class='error-msg'><span>" + ERRMSG08_incorrectPass + "</span></div>";
						$("#oldPassword").before(html);
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				}
			});
		}
	});

	/* Call Ajax for saving new avatar */
	$(".save-avatar").click(function(){

		var userInfo = {
			userId: $("#loginUserId").val(),
			fileToBase64: $("#fileToBase64").val()
		}

		$.ajax({
			type: "POST",
			url: contextUrl + "/user/editavatar",
			contentType : "application/json; charset=utf-8",
			data: JSON.stringify(userInfo),
			success: function(data) {
				if(data != null) {
					alert(ALRMSG04_editAvatarSuccess);
					$("#header-avatar").attr("src", contextUrl + "/avatars/" + data);
				}
			}
		});
	});
/*-------------- USER MANAGEMENT END---------------- */


/*-------------- IMAGE MANAGEMENT START ---------------- */
	/* Upload Image Form Validation */
	$("#uploadImageForm").validate({
		ignore: [],
		rules: {
			locationName: {
				required: true
			},
			fileBase64Code:{
				required: true
			}
		},
		messages: {
			fileBase64Code: {
				required: ERRMSG10_requiredFile
			},

			locationName: {
				required: ERRMSG09_requiredLocation
			},
			lngHdn: {
				required: ERRMSG09_requiredLocation
			},
			latHdn: {
				required: ERRMSG09_requiredLocation
			}
		},
		errorPlacement: function ( error, element ) {

			// Add the `help-block` class to the error element
			error.addClass( "help-block" );

			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
			} else {

				if ($(element).is("#locationName")) {
					element = $(element).parent();
				}
					error.insertAfter( element );
			}
		},
		highlight: function ( element, errorClass, validClass ) {
			$(".error-msg").remove();

			if ($(element).is("#locationName")) {
				$(element).parent().parent().addClass("has-error");
			} else{
				$(element).parent().addClass("has-error");
			}
		},
		unhighlight: function (element, errorClass, validClass) {

			if ($(element).is("#locationName")) {
				$(element).parent().parent().removeClass("has-error");
			} else{
				$(element).parent().removeClass("has-error");
			}
		},
		submitHandler: function() {

			$(".error-msg").remove();

			var lng = $("#lngHdn").val();
			var lat = $("#latHdn").val();

			if(lng == "" || lat == "") {
				$("#locationName").parent().parent().addClass("has-error");
				$("#locationName").parent().parent().append("<span id='description-error' class='error help-block'>" + ERRMSG09_requiredLocation + "</span>");

				return false;
			}


			/* Upload Handler */
			var imageInfo = {

				user: {
					userId: $("#loginUserId").val()
				},

				description : $("#description").val(),
				competitionId : $("#competitionId").val(),
				speciesId : $("#speciesId").val(),
				lng : lng,
				lat : lat,
				locationName : $("#locationName").val(),
				fileToBase64 : $("#fileBase64Code").val(),
				originalImageName: $("#originalImageName").val()
			}


			var imageInfoList = [];
			if($('#batch-upload').is(":checked")){
				var uploadingfiles = $("#fileBase64Code").data('uploadingfiles');
				 
				uploadingfiles.forEach(function(file){
					var imageInfoClone = $.extend({}, imageInfo);
					imageInfoClone.fileToBase64 = file.fileToBase64;
					imageInfoClone.originalImageName = file.originalImageName;
					imageInfoList.push(imageInfoClone);
				})
			}else{
				imageInfoList.push(imageInfo);
			}
			debugger;
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : contextUrl + "/image/upload",
				data : JSON.stringify(imageInfoList),
				success: function(data){

					if(data == 1) {
						window.location.href=contextUrl + "/ownlistimage/" + $("#competitionId").val();
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				}
			});
		}
	});

	/* Edit Image Form Validation */
	$("#editImageForm").validate({
		rules: {
			locationName: {
				required: true
			}
		},
		messages: {
			locationName: {
				required: ERRMSG09_requiredLocation
			}
		},
		errorPlacement: function ( error, element ) {

			// Add the `help-block` class to the error element
			error.addClass( "help-block" );

			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
			} else {

				if ($(element).is("#locationName")) {
					element = $(element).parent();
				}
					error.insertAfter( element );
			}
		},
		highlight: function ( element, errorClass, validClass ) {
			$(".error-msg").remove();

			if ($(element).is("#locationName")) {
				$(element).parent().parent().addClass("has-error");
			} else{
				$(element).parent().addClass("has-error");
			}
		},
		unhighlight: function (element, errorClass, validClass) {

			if ($(element).is("#locationName")) {
				$(element).parent().parent().removeClass("has-error");
			} else{
				$(element).parent().removeClass("has-error");
			}
		},
		submitHandler: function() {

			$(".error-msg").remove();

			var lng = $("#lngHdn").val();
			var lat = $("#latHdn").val();

			if(lng == "" || lat == "") {
				$("#locationName").parent().parent().addClass("has-error");
				$("#locationName").parent().parent().append("<span id='description-error' class='error help-block'>" + ERRMSG09_requiredLocation + "</span>");

				return false;
			}

			/* Edit Image Handler */
			var imageInfo = {

				user: {
					userId: $("#loginUserId").val()
				},

				imageId : $("#imageIdHdn").val(),
				description : $("#description").val(),
				speciesId : $("#speciesId").val(),
				lng : lng,
				lat : lat,
				locationName : $("#locationName").val()
			}

			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : contextUrl + "/image/edit",
				data : JSON.stringify(imageInfo),
				success: function(data){

					if(data == 1) {
						window.location.href=contextUrl + "/ownlistimage/" + $("#competitionId").val();
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				}
			});
		}
	});
/*-------------- IMAGE MANAGEMENT END---------------- */

	/* Change Orderby START */
	/* OrderBy in Home Page */
	$("#orderBy-homePage").val($("#orderByHidden").val());
	$("#orderBy-homePage").change(function(){

		var pageNum = $("#currentPage").val();
		var orderBy = $("#orderBy-homePage").val();
		var urlString = contextUrl + "/home/" + pageNum + "/" + orderBy;

		window.location.href = urlString;
	});

	/* OrderBy in Own List Picture Page Start */
	$("#orderBy-ownList").val($("#orderByHidden").val());
	$("#orderBy-ownList").change(function(){

		var competitionId = $(".competitionHdn").val();
		var orderBy = $("#orderBy-ownList").val();
		var urlString = contextUrl + "/ownlistimage/" + competitionId +"/" + orderBy;

		window.location.href = urlString;
	});

	/* OrderBy in Competition List Picture Page Start */
	$("#orderBy-cptt-list-image").val($("#orderByHidden").val());
	$("#orderBy-cptt-list-image").change(function(){

		var competitionId = $(".competitionHdn").val();
		var orderBy = $("#orderBy-cptt-list-image").val();
		var currentPage = $("#currentPage").val();
		var urlString = contextUrl + "/competition/" + currentPage + "/" + orderBy + "?id=" + competitionId;

		window.location.href = urlString;
	});
	/* Change Orderby END */
});

/**
 * Delete Image
 * @param imageId
 * @param competitionId
 */
function deleteImage(imageId, competitionId, imageUrl) {

	$.ajax({
		type: "POST",
		url: contextUrl + "/image/delete/"+ imageId + "/" + imageUrl,
		success: function(data) {
			if(data == 1) {
				var orderBy = $("#orderByHidden").val();
				alert(ALRMSG01_deleteImage);
				window.location.href = contextUrl + "/ownlistimage/" + competitionId +"/" + orderBy;
			}

		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
	});
}


/*----------------- COMMENT MANAGEMENT START ----------------*/
/**
 * Get List Comment Logined
 * @param imageId
 */
function getListComment(imageId) {

	var idUlTag = "#list-comment-" + imageId;
	var uploaderUserIdSelector = "#uploader-userId-" + imageId;
	var loginUserId = $("#loginUserId").val();
	var uploaderUserId = $(uploaderUserIdSelector).val();

	/* Call AJAX Get list comment */
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : contextUrl + "/comment/"+ imageId,
		dataType : 'json',
		success : function(data) {
			 console.log("OK");

			 $(idUlTag).empty();

			if(data.length == 0) {

				var html = "<li><span>" + INFMSG01_noComment + "</span></li>";

				$(idUlTag).append(html);
			} else {
				 $.each(data, function( index, value ) {

					 /* Convert Comment time by language VI */
					 var lifeTime = value.lifeTime;

						if(lang == "vi") {
							lifeTime = lifeTime.replace("Just", "Vừa xong");
							lifeTime = lifeTime.replace("hrs", "giờ");
							lifeTime = lifeTime.replace("hr", "giờ");
							lifeTime = lifeTime.replace("mins", "phút");
							lifeTime = lifeTime.replace("min", "phút");
						}

					 var avatarUrl = contextUrl + "/avatars/" + value.user.avatarUrl;
					 var html = "<li>"
					 	 + 			"<div class='comment-group row'>"
					 	 + 				"<div class='col-md-2 col-xs-2'><a href='/web/user/userprofile/" + value.user.userId + "'><img src='" +  avatarUrl + "'/></a></div>"
						 +				"<div class='col-md-10 col-xs-10'>"
						 +					"<div class='name row'>"
						 + 						"<a class='pull-left' href='/web/user/userprofile/" + value.user.userId + "'><h3>" +  value.user.firstName + "</h3></a>"
						 +						"<p class='pull-right lifetime'>" + lifeTime + "</p>"
						 + 					"</div>"
						 +					 "<div class='comment'>"
						 +	             		"<div class='content'>"
						 +            			"<p class='pull-left'>" + value.comment + "</p>";

					 var concatStr = "";

					 if(loginUserId != null) {

						 if(loginUserId == value.user.userId) {

							 concatStr = 		"<div class='pull-right modify-icon'>"
				                         +			"<a class='edit'><i class='glyphicon glyphicon-pencil'></i></a>"
				                         +			"<a onclick='deleteComment(" +  value.commentId + "," + value.imageId + ");' class='delete'>&nbsp;<i class='glyphicon glyphicon-trash'></i></a>"
				                     	 +		"</div>"
									 	 +	"</div>"
								 		 +	"<div class='edit-content'>"
		             					 +		"<input type='text' class='form-control pull-left' onkeypress='editComment(event, " +  value.commentId + "," + value.imageId + ", this);'"
		             					 +			" id='edit-comment-txt' value='" + value.comment + "' placeholder='Writng a comment...'/>"
		             					 +		"<div class='pull-right'>"
						             	 +			"<a class='cancel'><i class='glyphicon glyphicon-remove'></i></a>"
										 +		"</div>";

						 } else if(loginUserId == uploaderUserId) {
							 concatStr = 	"<div class='pull-right modify-icon'>"
								 +				"<a onclick='deleteComment("+  value.commentId + "," + value.imageId + ");' class='delete'>&nbsp;<i class='glyphicon glyphicon-trash'></i></a>"
								 +			"</div>";
						 }
					 }

					 concatStr.concat("</div></div>");
					 $(idUlTag).append(html.concat(concatStr));
					 loadJs();
				 });
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

/**
 * Load script when get list comment
 */
function loadJs() {

	$(".list-comment ul li").hover(
		function(){
			$(this).find(".modify-icon").css("display", "block");
		}, function() {
			$(this).find(".modify-icon").css("display", "none");
		}
	);

	$(".comment-group .edit").click(function(){
		$(".content").css("display", "block");
		$(".edit-content").css("display", "none");

		$(this).parent().parent().parent().find(".edit-content").css("display", "block");

		var comment = $(this).parent().parent().parent().find(".content").find("p").text();
		$(this).parent().parent().parent().find(".edit-content").find("input").val(comment);

		$(this).parent().parent().css("display", "none");
	});

	$(".comment-group .cancel").click(function(){
		$(".content").css("display", "block");
		$(".edit-content").css("display", "none");
	});
}

/**
 * Save Comment
 * @param event
 * @param imageId
 * @param userId
 */
function saveComment(event, imageId, userId, obj) {

	/* Check Enter Keycode */
	if (event.keyCode === 13) {

		/* If comment is not empty */
		if($(obj).val() != "") {
			var commentDTO = {
					imageId : imageId,
					user: {
						userId: userId
					},
					comment : $(obj).val()
				}

			var commentNumId = ".comment-num-" + imageId;
			var commentNumDes = parseInt($(commentNumId).text()) + 1;

			$.ajax({
				type: "POST",
				url: contextUrl + "/comment/add",
				data: JSON.stringify(commentDTO),
				contentType : "application/json; charset=utf-8",
				success: function(data) {
					if(data == 1) {
						getListComment(imageId);
						$(commentNumId).text(commentNumDes);
						$(".comment-num-md-" + imageId).text(commentNumDes);
						$(obj).val("");
						return;
					}

				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
			});
		}
    }
}


/**
 * Edit Comment
 * @param event
 * @param commentId
 */
function editComment(event, commentId, imageId, obj) {

	/* Check Enter Keycode */
	if (event.keyCode === 13) {

		if($(obj).val() == "") {
			deleteComment(commentId, imageId);
		} else {

			commentDTO = {
				commentId: commentId,
				comment: $(obj).val()
			}

			$.ajax({
				type: "POST",
				url: contextUrl + "/comment/edit",
				data: JSON.stringify(commentDTO),
				contentType : "application/json; charset=utf-8",
				success: function(data) {
					getListComment(imageId);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
			});
		}
    }
}

/**
 * Delete Comment
 * @param event
 * @param commentID
 */
function deleteComment(commentId, imageId) {

	html = "<button type='button' id='confirm-detele-btn-" + commentId + "' class='btn btn-primary'>Yes</button>";

	/* Add button confirm to modal confirm delete with commentId */
	$("#confirmDeleteComment .modal-footer .accept-btn").empty();
	$("#confirmDeleteComment .modal-footer .accept-btn").prepend(html);

	$("#confirmDeleteComment").modal("show");

	/* Change Comment Number */
	var commentNumId = ".comment-num-" + imageId;
	var commentNumDes = parseInt($(commentNumId).text()) - 1;

	$("#confirm-detele-btn-" + commentId).click(function(){

		$.ajax({
			type: "GET",
			url: contextUrl + "/comment/delete/" + commentId,
			success: function(data) {

				if(data == 1) {
					$("#confirmDeleteComment").modal("hide");
					getListComment(imageId);
					$(commentNumId).text(commentNumDes);
					$(".comment-num-md-" + imageId).text(commentNumDes);

					alert(ALRMSG02_deleteComment);
				}

				return;
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
		});
	});
}
/*----------------- COMMENT MANAGEMENT END ----------------*/


/*----------------- COMPETITION MANAGEMENT START ----------------*/
/**
 * Handle when click Join Competition
 * @param competitionId
 */
function joinCompetition(competitionId, baseUrl, isAnonymous){
	if(isAnonymous){
		$("#login-register-popup").modal("show");
		return;
	}
//	var html = "<button type='button' class='btn btn-primary' id='confirm-survey-btn-" + competitionId + "'>OK</button>";
//
//	$(".modal-footer .submit-survey-btn").empty();
//	$(".modal-footer .submit-survey-btn").prepend(html);
	
	termAndCondition = $("#termAndCondition-cptt-" + competitionId).val();
	
	$("#cptt-term").text(termAndCondition);
//
	var select2CompetitionGroups = [];
	$.ajax({
		url: baseUrl + "api/competition/getlistgroupcptt/" + competitionId,
		success: function(data){
			// Parse data for select2 format
			data.forEach(function(competitionGroup){
				var select2CompetitionGroup = {};
				select2CompetitionGroup.id = competitionGroup.groupId;
				select2CompetitionGroup.text = competitionGroup.description;
				select2CompetitionGroups.push(select2CompetitionGroup);
			})
			var group = $('.competition-group').select2({
				data: select2CompetitionGroups
			})
			$("#competition-group-modal").modal("show");
			$('#competition-group-modal .btn-group').on('click', function(e){
				
				if($("#confirmTerm").is(":checked")) {
					
					$.ajax({
						type: "POST",
						url: baseUrl + "competition/join/" + competitionId + "/" + group.val(),
						success: function(data) {
							if(data == 1) {
								alert(ALRMSG03_joinedCompetition);
								window.location.href = contextUrl + "/competition";
							}
						},
						error: function(e) {
							console.log("ERROR: ", e);
						}
					});
					
				} else {
					var html = "<div class='error-msg'><span>" + ERRMSG13_requiredTermCompetition + "</span></div>";
					$("#confirmTerm").append(html);
				}
				
			})
		}
	});
	
	
//
//	$("#confirm-survey-btn-" + competitionId).click(function(){
//
//		$.ajax({
//
//			type: "POST",
//			url: contextUrl + "/competition/join/" + competitionId,
//			success: function(data) {
//				if(data == 1) {
//					alert(ALRMSG03_joinedCompetition);
//					window.location.href = contextUrl + "/competition";
//				}
//			},
//			error: function(e) {
//				console.log("ERROR: ", e);
//			}
//		});
//	});
}

function savePrefferdLanguage(lang) {
	
	$.ajax({
		
		type: "POST",
		url: contextUrl + "/user/savelang/" + lang,
		success: function(data) {
			if(data == 1) {
				window.location.href = "?lang=" + lang;
			}
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	});
	
}

/*----------------- COMPETITION MANAGEMENT END ----------------*/
