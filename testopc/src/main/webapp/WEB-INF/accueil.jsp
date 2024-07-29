<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>

	<%@ include file = "menu.jsp" %>
	<h1>Bienvenu sur ma page d'accueil !</h1>
	
		<h3>Ici on peut upload un fichier sur le server ^^</h3>
	
	<!-- Formulaire -->
    <c:if test="${ !empty fichier }">
	    <p>
	    	<c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" />
	    </p>
    </c:if>
    
    <c:if test="${ !empty sessionScope.prenom && !empty sessionScope.nom }">
	
		<p>Vous êtes ${sessionScope.prenom } ${sessionScope.nom} !</p>
	
	
	</c:if>
    
    <form method="post" action="accueil" enctype="multipart/form-data">
         <p>
            <label for="description">Description du fichier : </label>
            <input type="text" name="description" id="description" />
        </p>
        <p>
            <label for="fichier">Fichier à envoyer : </label>
            <input type="file" name="fichier" id="fichier" />
        </p>
        
        <input type="submit" />
    </form>

</body>
</html>