$(document).ready(function(){


	/* Administrator Page */
	$(".card-list .card").hover(
		function(){
			$(this).find(".modify-cptt-area").show();
		}, function() {
			$(this).find(".modify-cptt-area").hide();
		}
	);

	$("#cancelBtn").click(function(){
		window.location.href= $(this).data('gobackurl');
	});

	var filterValueHotReward = "[value="+ $("#hotRewardsHdn").val() + "]";
	$('input:radio[name="hotRewards"]').filter(filterValueHotReward).attr('checked', true);

/*-------------- USER MANAGEMENT START---------------- */
	/* Edit Profile Form Validation */
	$("#editProfileAdminForm").validate({
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
				birthday : $("#birthday").val()
			}

			$(".error-msg").remove();

			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : contextUrl + "/admin/profile",
				data : JSON.stringify(userInfo),
				success: function(data){

					if(data == 1) {
						alert(ALRMSG06_editProfileSuccess);
						window.location.href=contextUrl + "/admin/profile";
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

	/* Add group form */
	$("#addGroupForm, #editGroupForm").validate({
		rules: {
			groupName: {
				required: true
			},
		},
		errorPlacement: function ( error, element ) {

			// Add the `help-block` class to the error element
			error.addClass( "help-block" );
			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
			} else {
				if ($(element).is(".datepicker")) {
					element = $(element).parent();
				}
					error.insertAfter( element );
			}
		},
		highlight: function ( element, errorClass, validClass ) {
			$(".error-msg").remove();
			$(element).parent().addClass("has-error");
		},
		unhighlight: function (element, errorClass, validClass) {
			$(element).parent().removeClass("has-error");
		},
		submitHandler: function(form, event) {
			/* Competition Info Update Handler */
			event.preventDefault();
			var group = {
				groupId: $("#groupId").val(),
				groupLogoUrl : "",
				groupName : $("#groupName").val(),
				description : $("#groupDescription").val(),
				fileToBase64: $('#fileToBase64').val(),
				//groupLogoUrl : $("#groupLogoUrl").val(),
				//status: $('#status').is(':checked'),
			}
			$(".error-msg").remove();
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : $(form).prop('action'),
				data : JSON.stringify(group),
				success: function(data){
					if(data == 1) {
						$.notify("Group has been saved successfully!", "success");
						window.location.href=contextUrl + "/admin/group";
					} else if (data == 0) {
						var html = "<div class='error-msg'><span>Group name is Duplicate.</span></div>";
						$("#groupName").after(html);
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});
		}
	});


	/* Change Password Form Validation */
	$("#changePasswordAdminForm").validate({
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
				url : contextUrl + "/admin/changepasswd",
				data : JSON.stringify(userInfo),
				success: function(data){

					if(data == 1) {
						alert(ALRMSG05_changePassSuccess);
						window.location.href=contextUrl + "/admin/profile";
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
			url: contextUrl + "/admin/editavatar",
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
	
	// Convert to select2
	$("select").select2({
	  tags: "true",
	  placeholder: "Select an option",
	  allowClear: true
	});
});

function deactiveUser(userId, currentPage) {

	$.ajax({
		type: "POST",
		url: contextUrl + "/admin/deactiveuser/" + userId,
		success: function(data) {
			if(data == 1) {
				alert("You have just block user successfully!");
				window.location.href = contextUrl + "/admin/listuser/" + currentPage;
			}
		}
	});
}

function activeUser(userId, currentPage) {

	$.ajax({
		type: "POST",
		url: contextUrl + "/admin/activeuser/" + userId,
		success: function(data) {
			if(data == 1) {
				alert("You have just active user successfully!");
				window.location.href = contextUrl + "/admin/listuser/" + currentPage;
			}
		}
	});
}
/*-------------- USER MANAGEMENT END---------------- */
$(document).ready(function(){

	/* editCompetitionForm Form Validation */
	$("#addCompetitionForm, #editCompetitionForm").validate({
		rules: {
			competitionName: {
				required: true
			},
			beginTime: {
				required: true
			},
			endTime: {
				required: true
			}

		},
		errorPlacement: function ( error, element ) {

			// Add the `help-block` class to the error element
			error.addClass( "help-block" );

			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
			} else {

				if ($(element).is(".datepicker")) {
					element = $(element).parent();
				}
					error.insertAfter( element );
			}
		},
		highlight: function ( element, errorClass, validClass ) {
			$(".error-msg").remove();

			if ($(element).is(".datepicker")) {
				$(element).parent().parent().addClass("has-error");
			} else{
				$(element).parent().addClass("has-error");
			}
		},
		unhighlight: function (element, errorClass, validClass) {

			if ($(element).is(".datepicker")) {
				$(element).parent().parent().removeClass("has-error");
			} else{
				$(element).parent().removeClass("has-error");
			}
		},
		submitHandler: function(form) {
			event.preventDefault();
			/* Competition Info Update Handler */
			var competition = {
				fileToBase64 : $("#fileToBase64").val(),
				competitionId : $("#competitionIdEdit").val(),
				competitionName : $("#competitionName").val(),
				competitionRewards : $("#competitionRewards").val(),
				beginTime : $("#beginTime").val(),
				endTime : $("#endTime").val(),
				description : $("#description").val(),
				competitionLogoUrl: $("#competitionLogoUrl").val(),
				hotRewards: $('input[name=hotRewards]:checked').val(),
				initPoint: $('#initPoint').val(),
				listGroupId: $('#groups').val(),
				termAndCondition: $("#termAndCondition").val()
			}

			$(".error-msg").remove();

			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : $(form).prop('action'),
				data : JSON.stringify(competition),
				success: function(data){

					if(data == 1) {
						$.notify("Competition has been saved successfully!", "success")
						window.location.href=contextUrl + "admin/competition";
					} else if (data == 0) {
						var html = "<div class='error-msg'><span>Competition Name Is Duplicate.</span></div>";
						$("#competitionName").after(html);
					} else if (data == 2) {
						var html = "<div class='error-msg'><span>Begin Time is Greater Than End Time.</span></div>";
						$("#beginTime").parent().after(html);
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				}
			});
		}
	});

	/* editCompetitionForm Form Validation */
//	$("#editCompetitionForm").validate({
//		rules: {
//			competitionName: {
//				required: true
//			},
//			beginTime: {
//				required: true
//			},
//			endTime: {
//				required: true
//			}
//
//		},
//		errorPlacement: function ( error, element ) {
//
//			// Add the `help-block` class to the error element
//			error.addClass( "help-block" );
//
//			if ( element.prop( "type" ) === "checkbox" ) {
//				error.insertAfter( element.parent( "label" ) );
//			} else {
//
//				if ($(element).is(".datepicker")) {
//					element = $(element).parent();
//				}
//					error.insertAfter( element );
//			}
//		},
//		highlight: function ( element, errorClass, validClass ) {
//			$(".error-msg").remove();
//
//			if ($(element).is(".datepicker")) {
//				$(element).parent().parent().addClass("has-error");
//			} else{
//				$(element).parent().addClass("has-error");
//			}
//		},
//		unhighlight: function (element, errorClass, validClass) {
//
//			if ($(element).is(".datepicker")) {
//				$(element).parent().parent().removeClass("has-error");
//			} else{
//				$(element).parent().removeClass("has-error");
//			}
//		},
//		submitHandler: function() {
//
//			/* Competition Info Update Handler */
//			var competition = {
//				competitionId : $("#competitionIdEdit").val(),
//				fileToBase64 : $("#fileToBase64").val(),
//				competitionName : $("#competitionName").val(),
//				competitionRewards : $("#competitionRewards").val(),
//				beginTime : $("#beginTime").val(),
//				endTime : $("#endTime").val(),
//				description : $("#description").val(),
//				competitionLogoUrl: $("#competitionLogoUrl").val(),
//				hotRewards: $('input[name=hotRewards]:checked').val(),
//				initPoint: $('#initPoint').val(),
//				termAndCondition: $("#termAndCondition").val()
//			}
//
//			$(".error-msg").remove();
//
//			$.ajax({
//				type : "POST",
//				contentType : "application/json; charset=utf-8",
//				url : contextUrl + "/admin/editcompetition",
//				data : JSON.stringify(competition),
//				success: function(data){
//
//					if(data == 1) {
//						alert("Competition has been edited successfully!")
//						window.location.href=contextUrl + "/admin/competition";
//					} else if (data == 0) {
//						var html = "<div class='error-msg'><span>Competition Name Is Duplicate.</span></div>";
//						$("#competitionName").after(html);
//					} else if (data == 2) {
//						var html = "<div class='error-msg'><span>Begin Time is Greater Than End Time.</span></div>";
//						$("#beginTime").parent().after(html);
//					}
//				},
//				error : function(e) {
//					console.log("ERROR: ", e);
//					display(e);
//				}
//			});
//		}
//	});
});

function deleteCompetition(competitionId) {

	$.ajax({
		type: "GET",
		url: contextUrl + "/admin/deletecompetition/" + competitionId ,
		success: function(data) {
			if(data == 1) {

				alert("Delete Competition Successfully!");
				window.location.href = contextUrl + "admin/competition";
			}

		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
	});
}
function deletegroup(groupId) {

	$.ajax({
		type: "GET",
		url: contextUrl + "/admin/group/" + groupId + "/delete" ,
		method: "post",
		success: function(data) {
			if(data == 1) {
				$.notify("Delete group successfully!", "success");
				window.location.href = contextUrl + "/admin/group";
			}

		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
	});
}



/*----------------- COMPETITION MANAGEMENT START ----------------*/

/*----------------- COMPETITION MANAGEMENT END ----------------*/
