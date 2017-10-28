

var app = angular.module('postStrategy', []);

app.controller('postCtrl', function($scope, $http) {

  $(document).on("click", ".btn.btn-default", function () {
       var stratId = $(this).data('stratno');
       var stratDescrip1 = $(this).data('descrip1');
       var stratDescrip2 = $(this).data('descrip2');
       var stratDescrip3 = $(this).data('descrip3');
       var stratrepId = $(this).data('repid');
       var stratUser = $(this).data('user');
       var stratStatus = "Approve";
       var index =  $(this).closest('td').parent()[0].sectionRowIndex;


       $scope.strategyID = stratId;
       $scope.description1 = stratDescrip1;
       $scope.description2 = stratDescrip2;
       $scope.description3 = stratDescrip3;
       $scope.strategyApprove = "Approve";
       $scope.strategyReject = "Reject";
       $scope.reportID = stratrepId;
       $scope.username = stratUser;
       $scope.lblMsg = null;
       $scope.postApprove = function(strategyID, description1, description2, description3, strategyApprove, reportID, username) {
         var data = {
           strategyID: strategyID,
           description1: description1,
           description2: description2,
           description3: description3,
           strategyStatus: strategyApprove,
           reportID: reportID,
           username: username
         };
         console.log(strategyID);
         console.log(strategyApprove);
         $http.post('http://localhost:4567/strategy/update', JSON.stringify(data)).then(function(response) {
           if (response.data)
             alert("Authorization Successful!");
             document.getElementsByTagName("button")[index+1].innerHTML = "Approved";
             //$("#testing").toggleClass("approve");
         });
       };

       $scope.postReject = function(strategyID, description1, description2, description3, strategyReject, reportID, username) {
         var data = {
           strategyID: strategyID,
           description1: description1,
           description2: description2,
           description3: description3,
           strategyStatus: strategyReject,
           reportID: reportID,
           username: username
         };
         console.log(strategyID);
         console.log(strategyReject);
         $http.post('http://localhost:4567/strategy/update', JSON.stringify(data)).then(function(response) {
           if (response.data)
             alert("Authorization Successful!");
             document.getElementsByTagName("button")[index+1].innerHTML = "Rejected";
             //console.log(index);

              //$(".testing").index().css("backgroundColor", "red");
              //$(".testing").parent("td").css("backgroundColor", "red");

         });
       };
  });



});
angular.element(document).ready(function() {
angular.bootstrap(document.getElementById("App3"), ['postStrategy']);
});

function clearForm() {
   // Get the first form with the name
   // Usually the form name is not repeated
   // but duplicate names are possible in HTML
   // Therefore to work around the issue, enforce the correct index
   var frm = document.getElementsByName('strategy-form')[0];
   frm.submit(); // Submit the form
   frm.reset();  // Reset all form data
   return false; // Prevent page refresh
}

$('#myModal').on('show', function (e) { // stops modal from being shown when page load
   if (!data) return e.preventDefault()
});

$('.submit.btn.btn-default').eq(0).attr('id', 'approve');
$('.submit.btn.btn-primary').eq(0).attr('id', 'reject');
