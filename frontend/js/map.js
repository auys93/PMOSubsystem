function crisisLocation() {
  var singapore = {
    lat: 1.3521,
    lng: 103.8198
  };
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 12,
    mapTypeId: google.maps.MapTypeId.HYBRID,
    center: singapore
  });
  var geocoder = new google.maps.Geocoder;
  $(document).ready(function() {
    $.getJSON('http://localhost:4567/report', function(getLatLng) {
      setInterval(function() {
        for (var i = 0; i < getLatLng.length; i++) {
          var obj = getLatLng[i];
          var marker = new google.maps.Marker({
            position: new google.maps.LatLng(obj.locationLat, obj.locationLong),
            map: map,
          });
        }
      }, 3000);
    });
  });
  $(document).ready(function() {
    $.getJSON('http://localhost:4567/reportupdate', function(getLatLng) {
      setInterval(function() {
        for (var i = 0; i < getLatLng.length; i++) {
          var obj = getLatLng[i];
          var marker = new google.maps.Marker({
            position: new google.maps.LatLng(obj.locationLat, obj.locationLong),
            map: map,
            icon: 'assets/mapMark/yellow_markerU.png'
          });
        }
      }, 3000);
    });
  });
}
