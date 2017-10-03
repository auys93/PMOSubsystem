$(document).ready(function(){
  $.getJSON("http://localhost:4567/strategy",function(data){
    var strategy_data = '';
    $.each(data,function(key,value){
        strategy_data += '<tr>';
        strategy_data += '<td>' + value.strategyID + '</td>';
        strategy_data += '<td>' + value.description1 + '</td>';
        strategy_data += '<td>' + value.description2 + '</td>';
        strategy_data += '<td>' + value.description3 + '</td>';
        strategy_data += '<td>' + value.strategyStatus + '</td>';
        strategy_data += '<td>' + value.reportID + '</td>';
        strategy_data += '<td>' + value.username + '</td>';
        strategy_data += '</tr>'
    });
    $('#strategy_table').append(strategy_data);
  });
});
