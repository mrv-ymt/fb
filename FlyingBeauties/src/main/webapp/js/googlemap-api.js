var map;
var marker;
var lat;
var lng;
var geocoder;
var infowindow
var openedMap = false;
var locationName;

$(document).ready(function(){	
	
	/* Upload Image Page: Show Google maps when modal is showed */
	$("#myModal").on("shown.bs.modal", function () {			
			
		lat = $("#latHdn").val();
		lng = $("#lngHdn").val();
		if(lat != 0 && lng != 0) {
			locationName = $("#locationName").val();
			initialize(lat, lng, true);	
		} else {
			initialize(16.0544061,108.2015483, false);	
		}
	});
	
	/* Edit Image Page: Show Google maps when modal is showed */
	$("#myModalEditImage").on("shown.bs.modal", function () {
		if(!openedMap) {
			
			lat = $("#latHdn").val();
			lng = $("#lngHdn").val();
			locationName = $("#locationName").val();
			initialize(lat, lng, true);	
		}
	});
	
	$("#savePositionBtn").click(function(){
		$("#locationName").val(locationName);
		$("#lngHdn").val(lng);
		$("#latHdn").val(lat);
	});
});

function initialize(lat, lng, isEditImage){
	var myCenter = new google.maps.LatLng(lat, lng);
	var mapProp = {
  		center:myCenter,
  		zoom:5,
  		mapTypeId:google.maps.MapTypeId.ROADMAP,
  		streetViewControl:false
  	};

 	map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
 	
 	if(isEditImage) {
 		marker=new google.maps.Marker({
 	 		position:myCenter,
 	 		animation:google.maps.Animation.BOUNCE
 		});

 		marker.setMap(map);
 		
 		infowindow = new google.maps.InfoWindow;
 		infowindow.setContent(locationName);
	    infowindow.open(map, marker);
 	} 	

 	google.maps.event.addListener(map, 'click', function(event) {
 		
 		/* Clear marker if exists */
 		if (marker) {	  		
			marker.setMap(null);	
 		}

 		/* Create new marker */
	  	placeMarker(event.latLng, map);
	});
}

function placeMarker(location, map) {
	
	/* Set Lng Lat */
	lng = location.lng();
  	lat = location.lat();	  	
	  		
  	/* Initialization GEO and Infowindow */
  	geocoder = new google.maps.Geocoder;
 	infowindow = new google.maps.InfoWindow;
 	
 	/* Set Map is opened */
 	openedMap = true;
 	var latlng = {lat: lat, lng: lng};
  		
	geocoder.geocode({'location': latlng}, function(results, status) {
	    if (status === google.maps.GeocoderStatus.OK) {
	    	if (results[1]) {
				marker = new google.maps.Marker({
		        	position: latlng,
		        	map: map,
		        	animation: google.maps.Animation.BOUNCE
		        });		
	        
				locationName = results[1].formatted_address;
				
		        infowindow.setContent(results[1].formatted_address);
		        infowindow.open(map, marker);
	        
	     	} else {
	     		locationName = "";
	      	}
	    } else {
	    	locationName = "";
	    }
	});	  	
}	