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
<title>Test SQL</title>
</head>
<body>
<h1>Test Sql</h1>
	
	  <form method="post" action="sql">
        <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" />
        </p>
        <p>
            <label for="prenom">Prénom : </label>
            <input type="text" name="prenom" id="prenom" />
        </p>
        
        <input type="submit" />
    </form>
    
    <ul>
        <c:forEach var="utilisateur" items="${ utilisateurs }">
            <li><c:out value="${ utilisateur.prenom }" /> <c:out value="${ utilisateur.nom }" /></li>
        </c:forEach>
    </ul>  


</body>
</html>