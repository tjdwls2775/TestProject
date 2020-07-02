<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
    
<html>
<head>


<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript" src="../javascript/calendar.js">
</script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
       body > div.container{
        	border: 3px solid #D6CDB7;
            margin-top: 10px;
        }
    </style>
<script type="text/javascript">
$(function() {
    $("#imgInp").on('change', function(){
        readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
    }
}
</script>
</head>
<body>

<div class="container">


<h1 class="bg-primary text-center">피드작성TEST</h1>

<form class="form-horizontal" name="detailForm" id="form" runat="server">

	<div class="form-group">
		    <label for="fileName" class="col-sm-offset-1 col-sm-3 control-label">이미지</label>
		    <div class="col-sm-4">
		     
		      <input		type='file' id="imgInput" class="form-control" />
							<img id="image_section" src="#" alt="your image"/>
		    </div>
		  </div>
	

		

</form>


<form class="form-horizontal" id="form" runat="server">
   <input type='file' id="imgInput"/>
   <img id="image_section" src="#" alt="your image"/>
</form>
</div>
</body>
</html>