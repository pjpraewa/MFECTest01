<html>
<head>
<script type="text/javascript" > </script>
<script src="jquery-3.3.1.min.js" charset="utf-8"></script>
<script>

    $(function(){
    var people = [];

    $.getJSON('report.json', function(data) {
      $.each(data, function(i, f) {
          var tblRow = "<tr>" + "<td>" + f.phoneno + "</td>" +
           "<td>" + f.usedate + "</td>" + "<td><center>" + f.usetime+ "</center></td>" + "<td><center>" + f.expense + "</center></td>" + "</tr>"
           $(tblRow).appendTo("#userdata tbody");
      });

    });

  });
</script>
</head>

<body>

<div class="wrapper">
<div class="profile">
  <br>
  <h3>รายงานค่าใช้บริการของลูกค้า</h3>
  <br>
   <table id= "userdata" border="2">
  <thead>
            <th>เบอร์โทรศัพท์</th>
            <th>วันที่</th>
            <th>เวลาที่ใช้ (นาที)</th>
            <th>ค่าใช้บริการ (บาท)</th>
        </thead>
      <tbody>

       </tbody>
   </table>

</div>
</div>

</body>
</html>
