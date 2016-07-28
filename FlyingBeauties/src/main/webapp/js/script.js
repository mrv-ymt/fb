$(document).ready(function(){	
	
	lang = $("#lang").val();
	
	/* Set Active Link */
	$("#welcome-page .menu-home").addClass("active");
	$("#hot-image-page .menu-hot-image").addClass("active");
	$("#list-competition-page .menu-competition").addClass("active");
	$("#hot-image-a-page .menu-hot-image-a").addClass("active");
	$("#upload-image-page .menu-upload-image").addClass("active");
	$("#home-page .menu-home").addClass("active");
	$("#edit-image-page .menu-upload-image").addClass("active");
	$("#profile-page .menu-profile").addClass("active");	
	$("#species-list-page .menu-species-list").addClass("active");
	$("#own-list-image-page .current-cptt-mypic").addClass("active");	
	$("#cptt-list-image-page .current-cptt").addClass("active");
	
	
	$('[data-toggle="tooltip"]').tooltip();
	
	/* Setting loading icon when call AJAX */
	$(document).ajaxStart(function(){
	    $(".loading").show();
	    $("input[type='submit'], button").prop("disabled", true);
	    
	 }).ajaxStop(function(){
	    $(".loading").hide();
	    $("input[type='submit'], button").prop("disabled", false);
	 });
	
	/* Add See more if description is too long */ 
	$(".description").each(function(){
		if($(this).height() > 120) {
			$(this).height(90);
			$(this).parent().find("a").css("display","block");
		}
	});
	
	$(".lifeTime").each(function(){
		var lifeTime = $(this).text().trim();
		
		if(lang == "vi") {
			lifeTime = lifeTime.replace("Just", "Vừa xong");
			lifeTime = lifeTime.replace("hrs", "giờ");
			lifeTime = lifeTime.replace("hr", "giờ");
			lifeTime = lifeTime.replace("mins", "phút");
			lifeTime = lifeTime.replace("min", "phút");
		}
		
		$(this).text(lifeTime);
		
	});
	
	/* Add tooltip */
	$('[data-toggle="tooltip"]').tooltip();   
	$(".nav-bar-toggle").click(function(){
		$(".reponsive").toggle();
	});	
	
	$(".list-comment").mCustomScrollbar();

	/* Tab LoginArea on Home page */
	$(".nav-tabs a").click(function(){
		$(this).tab('show');
	});
	
	/* Footer Download app */
	$(".footer-close-btn").click(function(){
		$(".footer-area-fixed").hide();
	});
	
	$(".download-app").click(function(){
		$(".footer-area-fixed").show();
	});	
	$("#forgot-pass-link").click(function(){
		$(".forgot-pass-tab").show();
		$(".forgot-pass-tab a").click();
	});
	
	$("#term-flyingbeauties-link").click(function(){
		$(".term-flyingbeauties-tab").show();
		$(".term-flyingbeauties-tab a").click();
	});
	
	$("#back-register").click(function(){
		$(".register-tab a").click();
		$(".term-flyingbeauties-tab").hide();
	});
	
	/* Date Time Picker */		
	if(lang == "vi") {
		$.fn.datetimepicker.dates['vi'] = {
			    days: ["Chủ Nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"],
			    daysShort: ["CN", "T2", "T3", "T4", "T5", "T6", "T7", "CN"],
			    daysMin: ["CN", "T2", "T3", "T4", "T5", "T6", "T7", "CN"],
			    months: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
			    monthsShort: ["T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"],
			    today: "Hôm nay",
			};
	}
	
	$('#date_picker').datetimepicker({
		 	pickTime: false,
		 	language: lang,
		 	format: (lang == "vi") ? 'dd-MM-yyyy' : 'MM-dd-yyyy'
		}).on('changeDate', function(ev){                 
			$('#date_picker').datetimepicker('hide');
	});
	
	$('#date_picker1').datetimepicker({
		 	pickTime: false,
		 	language: lang,
		 	format: (lang == "vi") ? 'dd-MM-yyyy' : 'MM-dd-yyyy'
		}).on('changeDate', function(ev){                 
			$('#date_picker').datetimepicker('hide');
	});
	
	$('#date_picker2').datetimepicker({
	 	pickTime: false,
	 	language: lang,
	 	format: (lang == "vi") ? 'dd-MM-yyyy' : 'MM-dd-yyyy'
	}).on('changeDate', function(ev){                 
		$('#date_picker').datetimepicker('hide');
});


	 /* Date time picker customer */
	$("#date_picker input").focus(function(){
		$(this).parent().find(".fa-calendar").click();
	});	
	
	
	/* Set Competition Id, Species select box when edit image */
	var competitionId = $("#competitionIdHdn").val();
	
	if(competitionId != null) {
		$("#competitionId").val(competitionId);
	} else {
		$("#competitionId").val(1);
	}
	
	/* Convert File To Base 64 when change Image Upload */
	$("#filer_input2").on('change', handleFileSelectImage);
	
	/* Edit Avatar */
	$(".camera-edit").click(function(){
		$("#file-upload").click();
	});
	
	/* Show Save Avatar Button when Upload File */
	$("#file-upload").change(function(){
		$(".save-avatar").css("display","inline-block");
	});
	
	/* Set Value for country select box */
	if($("#countryHdn").val() != null) {
		$("#country").val($("#countryHdn").val());
	}
	
	
	/* Login Error Handler */
	if($("#login-error").val()) {
		$("#login-register-popup").modal("show");
		
		var html = "<div class='error-msg'><span>" + ERRMSG04_invalidUserNameEmail + "</span></div>";					
		$("#username").before(html);
		$("#username").focus();
	}	
	
	if(!$(".nav-bar .image .sub-ul").has(".cptt").length) {
		$(".no-image-uploaded").removeClass("hidden");
	}
	
	if(!$(".nav-bar .cptt-title .sub-ul").has(".cptt").length) {
		$(".no-cptt-joined").removeClass("hidden");
	}
	
	/* Show Icon View Full Image and View Map */
	$(".list-picture .main-image").hover(
		function(){
			$(this).find(".zoom-view-location").show();
		}, function() {
			$(this).find(".zoom-view-location").hide();
		}
	);
});

/* Show One more Modal */
$(document).on('show.bs.modal', '.modal', function (event) {
    var zIndex = 1040 + (10 * $('.modal:visible').length);
    $(this).css('z-index', zIndex);
    setTimeout(function() {
        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
    }, 0);
});
   

/**
 * 
 * Open Modal Image Detail
 * @param imageId
 */
function openModal(imageId) {
	var modalId = '#modal' + imageId;	
	
	$("#image-" + imageId).attr("src", $("#pathFullImage-" + imageId).val());
	 
	getListComment(imageId);
	
	/* Show Modal */
	$(modalId).modal("show");
}

/**
 * Handle File For Upload Image
 */
var handleFileSelectImage = function(evt) {
    var files = evt.target.files;
    var file = files[0];
    var locationName;
    var lng;
    var lat;
    
    if (files && file) {
        var reader = new FileReader();

        reader.onload = function(readerEvt) {
            
            EXIF.getData(file, function() {
                
                lat = EXIF.getTag(this, 'GPSLatitude');
                lng = EXIF.getTag(this, 'GPSLongitude');
                
                if(lat != null && lng != null) {
                	
                	 //Convert coordinates to WGS84 decimal
                    var latRef =  EXIF.getTag(this, 'GPSLatitudeRef') || "N";  
                    var lonRef =  EXIF.getTag(this, 'GPSLongitudeRef') || "W";  
                    lat = (lat[0] + lat[1]/60 + lat[2]/3600) * (latRef == "N" ? 1 : -1);  
                    lng = (lng[0] + lng[1]/60 + lng[2]/3600) * (lonRef == "W" ? -1 : 1); 
                    
                    var geocoder = new google.maps.Geocoder;
                    var latlng = {lat: lat, lng: lng};
                    geocoder.geocode({'location': latlng}, function(results, status) {
                	    if (status === google.maps.GeocoderStatus.OK) {
                	    	if (results[1]) {
                				locationName = results[1].formatted_address;
                				$("#locationName").val(locationName);
                				$("#lngHdn").val(lng);
                				$("#latHdn").val(lat);
                	     	}
                	    }
                	});	  	
                }
            });
        };

        reader.readAsBinaryString(file);
    }
};

/* Load Image When upload and Convert To Base-64 */
var loadFile = function(event) {
	
	 var files = event.target.files;
	 var file = files[0];
	 
	 if (files && file) {
	
		 var reader = new FileReader();
		 reader.onload = function(readerEvt){
			 var output = document.getElementById('avatar');  
			 output.src = reader.result;
			 
		 };
		 reader.readAsDataURL(file);
		 
		 var reader1 = new FileReader();
		 reader1.onload = function(readerEvt){
			 var binaryString = readerEvt.target.result;
	         document.getElementById("fileToBase64").value = btoa(binaryString);
		 };
		 reader1.readAsBinaryString(file);		
	 }
};

$(document).ready(function(){
	
	var phoneNumFull = $("#phoneNumberHdn").val();
	var listCountryPhoneCodeString =  $("#listCountryPhoneCodeHdn").val();
	
	/* Remove [] */
	
	if(listCountryPhoneCodeString != null && listCountryPhoneCodeString != "") {
		listCountryPhoneCodeString = listCountryPhoneCodeString.substring(1, listCountryPhoneCodeString.length - 1);
		
		var listCountryPhoneCodeArr = listCountryPhoneCodeString.split(",");
		
		var phoneCode = "(" + phoneNumFull.split(" ")[0] + ")";
		
		for(i = 0; i < listCountryPhoneCodeArr.length; i++) {
			
			countryPhoneCode = listCountryPhoneCodeArr[i].trim();
			if(countryPhoneCode.includes(phoneCode)) {
				break;
			}
		}
		
		$("#countryPhoneCode").val(countryPhoneCode);
		$("#phoneNumber").val(phoneNumFull.split(" ")[1]);
	}
	
	$(".thumbnail-image").click(function(){
		$("#view-full-image-modal").show();
		
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");
		modalImg.src = $(this).parent().find(".pathFullImageFile").val();
	    modalImg.alt = this.alt;
	    captionText.innerHTML = this.alt;
	});
	
	// When the user clicks on <span> (x), close the modal
	$(".close-modal").click(function(){
		$("#view-full-image-modal").hide();
	});
});