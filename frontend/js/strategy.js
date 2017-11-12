var app = angular.module('strategyTable', []);

app.controller('strategyCtrl', function($scope, $http) {
    $http.get("http://localhost:4567/strategy")
      .then(function(response) {
        $scope.data = response.data;
      });
});
angular.element(document).ready(function() {
angular.bootstrap(document.getElementById("App2"), ['strategyTable']);
});
