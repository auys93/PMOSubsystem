var app = angular.module('loginStaff', []);

app.controller('loginCtrl', function($scope, $http) {
  $scope.staffID = null;
  $scope.firstName = null;
  $scope.lastName = null;
  $scope.username = null;
  $scope.password = null;
  $scope.lblMsg = null;
  $scope.test = [];

  //console.log(data);
  $http.get("http://localhost:4567/staff")
    .then(function(response) {
      $scope.data = response.data;
    });


  $scope.checkStaffData = function(username, password) {
    var data = {
      username: username,
      password: password
    };

    var loggedin = "";
    angular.forEach($scope.data, function(item) {

      if ((item.username == data.username) && (item.password = data.password)) {
        console.log("Success");
        loggedin = "1";
        window.location.href = 'index.html';
      }
      message = 'Username or password is incorrect';
    });

    if (loggedin != "1")
      alert("Your username or password is incorrect.\nPlease try again.");
  };


});
//angular.element(document).ready(function() {
//angular.bootstrap(document.getElementById("App1"), ['loginStaff']);
//});

function clearForm() {
  // Get the first form with the name
  // Usually the form name is not repeated
  // but duplicate names are possible in HTML
  // Therefore to work around the issue, enforce the correct index
  var frm = document.getElementsByName('staff-form')[0];
  frm.submit(); // Submit the form
  frm.reset(); // Reset all form data
  return false; // Prevent page refresh
}
