$(document).ready(function(){
  $.getJSON("http://localhost:4567/staff",function(data){
    var staff_data = '';
    $.each(data,function(key,value){
        staff_data += '<tr>';
        staff_data += '<td>'+value.staffID+'</td>';
        staff_data += '<td>'+value.firstName+'</td>';
        staff_data += '<td>'+value.lastName+'</td>';
        staff_data += '<td>'+value.username+'</td>';
        staff_data += '<td>'+value.password+'</td>';
        staff_data += '</tr>'
    });
    $('#staff_table').append(staff_data);
  });
});
