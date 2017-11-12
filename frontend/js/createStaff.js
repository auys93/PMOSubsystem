var app = angular.module('postStaff', []);

app.controller('postCtrl', function($scope, $http) {
  $scope.firstName = null;
  $scope.lastName = null;
  $scope.username = null;
  $scope.password = null;
  $scope.lblMsg = null;
  $scope.postStaffData = function(firstName, lastName, username, password) {
    var data = {
      firstName: firstName,
      lastName: lastName,
      username: username,
      password: password
    };
    $http.post('http://localhost:4567/staff/create', JSON.stringify(data)).then(function(response) {
      if (response.data)
        alert("Staff Submitted Successfully!");
    });
  };
});
angular.element(document).ready(function() {
angular.bootstrap(document.getElementById("App2"), ['postStaff']);
});

function clearForm() {
   // Get the first form with the name
   // Usually the form name is not repeated
   // but duplicate names are possible in HTML
   // Therefore to work around the issue, enforce the correct index
   var frm = document.getElementsByName('staff-form')[0];
   frm.submit(); // Submit the form
   frm.reset();  // Reset all form data
   return false; // Prevent page refresh
}
