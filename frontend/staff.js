// $(document).ready(function(){
//   $.getJSON("http://localhost:4567/staff",function(data){
//     var staff_data = '';
//     $.each(data,function(key,value){
//         staff_data += '<tr>';
//         staff_data += '<td>'+value.staffID+'</td>';
//         staff_data += '<td>'+value.firstName+'</td>';
//         staff_data += '<td>'+value.lastName+'</td>';
//         staff_data += '<td>'+value.username+'</td>';
//         staff_data += '<td>'+value.password+'</td>';
//         staff_data += '</tr>'
//     });
//     $('#staff_table').append(staff_data);
//   });
// });

//This is the angular version implmentation
var app = angular.module('staffTable', []);

app.controller('staffCtrl', function($scope, $http){
  $http.get("http://localhost:4567/staff")
  .then(function(response) {
    $scope.data = response.data;
  });
});
