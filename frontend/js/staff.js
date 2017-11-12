var app = angular.module('staffTable', []);

app.controller('staffCtrl', function($scope, $http, $interval){
  $scope.reload = function () {
    $http.get("http://localhost:4567/staff")
    .then(function(response) {
      $scope.data = response.data;
    });
  };
  $scope.reload();
  $interval($scope.reload, 5000);
});
// app.config(['growlProvider', '$httpProvider', function(growlProvider, $httpProvider) {
//   $httpProvider.interceptors.push(growlProvider.serverMessagesInterceptor);
// }]);
angular.element(document).ready(function() {
angular.bootstrap(document.getElementById("App1"), ['staffTable']);
});
