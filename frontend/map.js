function crisisLocation(){
        var singapore = {lat: 1.3521, lng: 103.8198};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 12,
          mapTypeId: google.maps.MapTypeId.HYBRID,
          center: singapore
        });
        //var marker;
        var geocoder = new google.maps.Geocoder;
        //var infowindow = new google.maps.InfoWindow;
$(document).ready(function(){
	$.getJSON('http://localhost:4567/report',function(getLatLng){
			for(var i = 0; i < getLatLng.length; i++) {
			    var obj = getLatLng[i];
				// var lat = obj.locationLat;
		  		// var lng = obj.locationLong;
		        //var latlng = {lat: obj.locationLat, lng: obj.locationLong};
		        //var latlng = lat,lng;
		        //document.write(obj.locationLat);
		        var marker = new google.maps.Marker({
		          position: new google.maps.LatLng(obj.locationLat,obj.locationLong),
		          map: map,
		        });
			}
	});
});
        // document.getElementById('submit').addEventListener('click', function() {
        //   geocodeLatLng(geocoder, map, infowindow);
        // });
}

// function geocodeLatLng(geocoder, map, infowindow){
//         var lat = obj.locationLat;
//         var lng = obj.locationLong;
//         var latlng = {lat: parseFloat(lat), lng: parseFloat(lng)};
//         geocoder.geocode({'location': latlng}, function(results, status) {
//         if (status === 'OK') {
//           if (results[0]) {
//             map.setZoom(11);
//             var marker = new google.maps.Marker({
//               position: latlng,
//               map: map
//             });
//             infowindow.setContent(results[0].formatted_address);//set crisis information too
//             infowindow.open(map, marker);
//           } else {
//             window.alert('No results found');
//           }
//         } 
//         else {
//           window.alert('Geocoder failed due to: ' + status);
//         }
//    		});
// }
// retrieving crisis locations


    // for (i = 0; i < locations.length; i++) {  
    //   marker = new google.maps.Marker({
    //     position: new google.maps.LatLng(locations[i][1], locations[i][2]),
    //     map: map
    //   });

    //   google.maps.event.addListener(marker, 'click', (function(marker, i) {
    //     return function() {
    //       infowindow.setContent(locations[i][0]);
    //       infowindow.open(map, marker);
    //     }
    //   })(marker, i));
    // }
    // var locations = [
    //   ['Bondi Beach', -33.890542, 151.274856, 4],
    //   ['Coogee Beach', -33.923036, 151.259052, 5],
    //   ['Cronulla Beach', -34.028249, 151.157507, 3],
    //   ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
    //   ['Maroubra Beach', -33.950198, 151.259302, 1]
    // ];

    // var map = new google.maps.Map(document.getElementById('map'), {
    //   zoom: 10,
    //   center: new google.maps.LatLng(-33.92, 151.25),
    //   mapTypeId: google.maps.MapTypeId.ROADMAP
    // });

    // var infowindow = new google.maps.InfoWindow();

    // var marker, i;

    // for (i = 0; i < locations.length; i++) {  
    //   marker = new google.maps.Marker({
    //     position: new google.maps.LatLng(locations[i][1], locations[i][2]),
    //     map: map
    //   });

    //   google.maps.event.addListener(marker, 'click', (function(marker, i) {
    //     return function() {
    //       infowindow.setContent(locations[i][0]);
    //       infowindow.open(map, marker);
    //     }
    //   })(marker, i));
    // }
