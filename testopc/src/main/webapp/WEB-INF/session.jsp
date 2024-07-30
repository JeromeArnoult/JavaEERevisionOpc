<%@ page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test de session</title>
</head>
<body>
<%@ include file = "menu.jsp" %>


	<c:if test="${ !empty sessionScope.prenom && !empty sessionScope.nom }">
	
		<p>Vous êtes ${sessionScope.prenom } ${sessionScope.nom} !</p>
	
	
	</c:if>
	<h1>Test de session</h1>
   <form method="post" action="session">
         <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" />
        </p>
        <p>
            <label for="prenom">Prénom : </label>
            <input type="text" name="prenom" id="prenom" />
        </p>
        
        <button type="submit">Envoyer</button>
    </form>




</body>
</html>