<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ taglib 
    prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core"  
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Cookie</title>
</head>
<body>

<%@ include file = "menu.jsp" %>

<h1>Test Cookie</h1>

	<c:out value ="${ prenom}" />

<form method="post" action="cookie">
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