// $(document).ready(function(){
//   $.getJSON("http://localhost:4567/report",function(data){
//     var report_data = '';
//     $.each(data,function(key,value){
//         report_data += '<tr>';
//         report_data += '<td>'+value.reportID+'</td>';
//         report_data += '<td>'+value.reportHandler+'</td>';
//         report_data += '<td>'+value.reportLiaison+'</td>';
//         report_data += '<td>'+value.liaisonPass+'</td>';
//         report_data += '<td>'+value.crisisType+'</td>';
//         report_data += '<td>'+value.reportStatus+'</td>';
//         report_data += '<td>'+value.locationLat+'</td>';
//         report_data += '<td>'+value.locationLong+'</td>';
//         report_data += '<td>'+value.description1+'</td>';
//         report_data += '<td>'+value.description2+'</td>';
//         report_data += '<td>'+value.description3+'</td>';
//         report_data += '<td>'+value.teamID+'</td>';
//         report_data += '<td>'+value.timestamp+'</td>';
//         report_data += '<td>'+value.deleted+'</td>';
//         report_data += '</tr>'
//     });
//     $('#report_table').append(report_data);
//   });
// });
var app = angular.module('reportTable', []);

app.controller('reportCtrl', function($scope, $http, $interval){
  $scope.reload = function () {
    $http.get("http://localhost:4567/report")
    .then(function(response) {
      $scope.data = response.data;
    });
  };
  $scope.reload();
  $interval($scope.reload, 5000);
});
angular.element(document).ready(function() {
angular.bootstrap(document.getElementById("App1"), ['reportTable']);
});
