 (function staffData(){
        var obj, dbParam, xmlhttp, myObj, x, txt = "";
        obj = {
          "table": "Staff",
          "limit": 20
        };
        dbParam = JSON.stringify(obj);
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            myObj = JSON.parse(this.responseText);

            for (x in myObj) {
              txt += "<tr><td>" + myObj[x].staffID + "</td>" + "<td>" + myObj[x].firstName + "</td>\n"
               + "<td>" + myObj[x].lastName + "</td>" + "<td>" + myObj[x].username + "</td>\n"
               + "<td>" + myObj[x].password + "</td></tr>";
            }

            document.getElementById("staffData").innerHTML = txt;
          }
        };
        xmlhttp.open("GET", "http://localhost:4567/staff", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("x=" + dbParam);

    })();

    (function reportData(){
           var obj, dbParam, xmlhttp, myObj, x, txt = "";
           obj = {
             "table": "Report",
             "limit": 20
           };
           dbParam = JSON.stringify(obj);
           xmlhttp = new XMLHttpRequest();
           xmlhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
               myObj = JSON.parse(this.responseText);

               for (x in myObj) {
                 txt += "<tr><td>" + myObj[x].reportID + "</td>" + "<td>" + myObj[x].reportHandler + "</td>"
                  + "<td>" + myObj[x].reportLiaison + "</td>" + "<td>" + myObj[x].liaisonPass + "</td>"
                  + "<td>" + myObj[x].crisisType + "</td>" + "<td>" + myObj[x].reportStatus + "</td>"
                  + "<td>" + myObj[x].locationLat + "</td>" + "<td>" + myObj[x].locationLong + "</td>"
                  + "<td>" + myObj[x].description1 + "</td>" + "<td>" + myObj[x].description2 + "</td>"
                  + "<td>" + myObj[x].description3 + "</td>" + "<td>" + myObj[x].teamID + "</td>"
                  + "<td>" + myObj[x].timestamp + "</td>"+ "<td>" + myObj[x].deleted + "</td></tr>";
               }


               document.getElementById("reportData").innerHTML = txt;
             }
           };
           xmlhttp.open("GET", "http://localhost:4567/report", true);
           xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
           xmlhttp.send("x=" + dbParam);

       })();

       (function strategyData(){
              var obj, dbParam, xmlhttp, myObj, x, txt = "";
              obj = {
                "table": "Report",
                "limit": 20
              };
              dbParam = JSON.stringify(obj);
              xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                  myObj = JSON.parse(this.responseText);

                  for (x in myObj) {
                    txt += "<tr><td>" + myObj[x].strategyID + "</td>"
                     + "<td>" + myObj[x].description1 + "</td>" + "<td>" + myObj[x].description2 + "</td>"
                     + "<td>" + myObj[x].description3 + "</td>" + "<td>" + myObj[x].strategyStatus + "</td>"
                     + "<td>" + myObj[x].reportID + "</td>"+ "<td>" + myObj[x].username + "</td></tr>";
                  }


                  document.getElementById("strategyData").innerHTML = txt;
                }
              };
              xmlhttp.open("GET", "http://localhost:4567/strategy", true);
              xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
              xmlhttp.send("x=" + dbParam);

          })();

          (function updateData(){
                 var obj, dbParam, xmlhttp, myObj, x, txt = "";
                 obj = {
                   "table": "Report",
                   "limit": 20
                 };
                 dbParam = JSON.stringify(obj);
                 xmlhttp = new XMLHttpRequest();
                 xmlhttp.onreadystatechange = function() {
                   if (this.readyState == 4 && this.status == 200) {
                     myObj = JSON.parse(this.responseText);

                     for (x in myObj) {
                       txt += "<tr><td>" + myObj[x].updateID + "</td>" + "<td>" + myObj[x].reportHandler + "</td>"
                        + "<td>" + myObj[x].reportLiaison + "</td>" + "<td>" + myObj[x].liaisonPass + "</td>"
                        + "<td>" + myObj[x].locationLat + "</td>" + "<td>" + myObj[x].locationLong + "</td>"
                        + "<td>" + myObj[x].description1 + "</td>" + "<td>" + myObj[x].description2 + "</td>"
                        + "<td>" + myObj[x].description3 + "</td>"
                        + "<td>" + myObj[x].timestamp + "</td>"+ "<td>" + myObj[x].reportID + "</td></tr>";
                     }


                     document.getElementById("updateData").innerHTML = txt;
                   }
                 };
                 xmlhttp.open("GET", "http://localhost:4567/reportupdate", true);
                 xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                 xmlhttp.send("x=" + dbParam);

             })();
/*$.getJSON("http://localhost:4567/staff", function(data) {
   $.each(data.entries, function(i, f) {
      var tblRow = "<tr>" + "<td>" + f.staffID + "</td>" + "<td>" + f.user.username + "</td>" + "<td>" + f.message + "</td>" + "<td> " + f.location + "</td>" +  "<td>" + f.at + "</td>" + "</tr>"
       $(tblRow).appendTo("#staffData");
 });

});*/
