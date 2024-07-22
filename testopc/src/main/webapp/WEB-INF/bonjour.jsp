<%@ page language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
%>
    		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
</head>
<body>

	<%@ include file = "menu.jsp" %>
	
		<p>
			Hello Mojito & Margaritas !! 
		</p>
		
		<p>
			<%
				String variable = (String) request.getAttribute("variable");
				out.println(variable);
			%>
		</p>
		
		<p>
			<%
				for (int i = 0 ; i < 20 ; i++) {
					out.println("Une margaritas ! <br>");
				}
			%>
		</p>

</body>
</html>