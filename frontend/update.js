$(document).ready(function(){
  $.getJSON("http://localhost:4567/reportupdate",function(data){
    var update_data = '';
    $.each(data,function(key,value){
        update_data += '<tr>';
        update_data += '<td>'+value.reportHandler+'</td>';
        update_data += '<td>'+value.reportLiaison+'</td>';
        update_data += '<td>'+value.liaisonPass+'</td>';
        update_data += '<td>'+value.locationLat+'</td>';
        update_data += '<td>'+value.locationLong+'</td>';
        update_data += '<td>'+value.description1+'</td>';
        update_data += '<td>'+value.description2+'</td>';
        update_data += '<td>'+value.description3+'</td>';
        update_data += '<td>'+value.timestamp+'</td>';
        update_data += '<td>'+value.reportID+'</td>';
        update_data += '</tr>'
    });
    $('#update_table').append(update_data);
  });
});
