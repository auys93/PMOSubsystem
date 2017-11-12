var notifyApp = angular.module('notifyApp', ['angular-growl']);
notifyApp.config(['growlProvider', '$httpProvider', function(growlProvider, $httpProvider) {
  $httpProvider.interceptors.push(growlProvider.serverMessagesInterceptor);
}]);
angular.element(document).ready(function() {
angular.bootstrap(document.getElementById("App6"), ['notifyApp']);
});
