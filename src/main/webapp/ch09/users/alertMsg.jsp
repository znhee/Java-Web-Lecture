<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	String msg = (String) request.getAttribute("msg");
	String url = (String) request.getAttribute("url"); 
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Alert Message</title>
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
<script src="http://cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
</head>
<body>
	<script>
		let msg = '<%= msg %>';
		let url = '<%= url %>';
        alertify.alert(msg, function() {
            alertify.message('OK');
            location.href = url;
        });
    </script>
	
</body>
</html>

