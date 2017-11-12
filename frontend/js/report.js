var app = angular.module('reportTable', []);

app.controller('reportCtrl', function($scope, $http, $interval){
  $scope.reload = function () {
    $http.get("http://localhost:4567/report")
    .then(function(response) {
      $scope.data = response.data;
    });
  };
  $scope.reload();
  $interval($scope.reload, 3000);
});
angular.element(document).ready(function() {
angular.bootstrap(document.getElementById("App1"), ['reportTable']);
});
