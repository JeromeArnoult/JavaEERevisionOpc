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
	<h1>Bienvenue sur la Page des tests</h1>
	
		 <p>
			Bonjour ${ auteur.prenom}, ${ auteur.nom }
			
		</p>
		
		<c:if test="${ !empty sessionScope.prenom && !empty sessionScope.nom }">
	
			<p>Vous êtes ${sessionScope.prenom } ${sessionScope.nom} !</p>
	
	
		</c:if>
		
			<!--   JSTL et variable -->
			 <p><c:out value="${ variable }"> Valeur par defaut </c:out></p>
			<c:set var="pseudo" value="Jejar25" scope="page" />
			<p><c:out value="${ pseudo }"/></p>
			<!-- Pour supprimer une variable de la memoire -->
			<c:remove var="pseudo" scope="page"/>
			<!-- JSTL interaction avec un bean(object) -->
			<c:set target="${ auteur }" property="prenom" value="Jerome"/>
			 <p><c:out value="${auteur.prenom }"/></p>
			<!-- JSTL Conditionel -->
				<!-- Une condition -->
					<c:if test="${ 50 > 10 }" var="autreVariable" scope ="application">
						C'est vrai !
					</c:if> 
				<!-- Condition multiples -->
			<c:choose>
			    <c:when test="${ variable }">Du texte</c:when>
			    <c:when test="${ autreVariable }">Un autre texte</c:when>
			    <c:when test="${ encoreUneAutreVariable }">Encore un autre texte :-)</c:when>
			    <c:otherwise>Si rien ce texte s'affiche !</c:otherwise>
			</c:choose>
			
			<!-- Les boucles -->
			
			<c:forEach var="i" begin="0" end="10" step="2">
    			<p>Un message n°<c:out value="${ i }" /> !</p>
			</c:forEach>
			
			<c:forEach items="${ titres }" var="titre" varStatus="status">
    			<p>N°<c:out value="${ status.count }" /> : <c:out value="${ titre }" /> !</p>
			</c:forEach>
			
			<c:forTokens var="morceau" items="Un élément/Encore un autre élément/Un dernier pour la route" delims="/ ">
    			<p>${ morceau }</p>
			</c:forTokens>
			
		<!-- EL -->
		<p>
			${ auteur.actif ? 'Vous êtes très actif ! ' : 'Vous êtes inactif !'}
		</p>
		
		
		<p>
			Bonjour ${ noms[1]}
		</p>
		<p>
			<%
				for (int i = 0 ; i < 20 ; i++) {
					out.println("Bonjour ! <br>");
				}
			%>
		</p>
		
		
		
		

</body>
</html>