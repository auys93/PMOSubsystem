var app = angular.module('updateTable', []);

app.controller('updateCtrl', function($scope, $http, $interval) {
  $scope.reload = function() {
    $http.get("http://localhost:4567/reportupdate")
      .then(function(response) {
        $scope.data = response.data;
      });
  };
  $scope.reload();
  $interval($scope.reload, 5000);
});
angular.element(document).ready(function() {
angular.bootstrap(document.getElementById("App4"), ['updateTable']);
});
