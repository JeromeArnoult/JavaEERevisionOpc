<%@ page language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
    		
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

    		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
</head>
<body>

	<%@ include file = "menu.jsp" %>
	
		<p>
			Bonjour ${ auteur.prenom}, ${ auteur.nom }
			<p><c:out value="Bonjour ! jstl" /></p>
		</p>
		<p>
			${ auteur.actif ? 'Vous êtes très actif ! ' : 'Vous êtes inactif !'}
		</p>
		
		<!--
		<p>
			Bonjour ${ noms[1]}
		</p>
		<p>
			<%
				for (int i = 0 ; i < 20 ; i++) {
					out.println("Bonjour ! <br>");
				}
			%>
		</p>-->

</body>
</html>