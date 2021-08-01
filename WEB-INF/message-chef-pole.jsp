<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Message Chef de Pôle</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/fonts/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/styleaccueil.css">
	<link rel="stylesheet" type="text/css" href="css/don - Copie.css">
	<link rel="stylesheet" type="text/css" href="css/message-chef-pole.css">
	<link rel="icon" type="image/png" href="img/logo.png" />
</head>
<body>

	<%@ include file="header.jsp" %><br><br><br><br><br><br>

	<div class="row">
       	<div class="col-sm-9 col-md-11 col-lg-11 col-xl-11"><span class="type-urgent"></span></div>
       	<div class="col-sm-3 col-md-1 col-lg-1 col-xl-1">
       		<form method="post" action="message-chef-pole">
				<button type="submit" class="btn btn-secondary actualiser">Actualiser</button>
			</form> 		
       	</div>
	</div><br>
	<c:forEach items="${ listeIntervention }" var="liste" >
	
		<div class="container-fluid intervention">
			<div class= "row">
				<div class="col title-intervention">
	    			<h2>Intervention n°<c:out value="${ liste.key }" /></h2>
	    		</div>
	    		<div class="col-2 details">
	    			<form method="post" action="DetailIntervention"><button class="btn btn-primary btn-details">Details <i class="fa fa-chevron-right fa-sm" aria-hidden="true"></i></button><input type="number" name="demandeid" value="<c:out value="${ liste.key }" />" hidden></form>
	    		</div>
			</div>
			
			<div class="row contact">
	    		<div class="col"><p><span class="title-contact">Demandeur : </span><c:out value="${ listeUtilisateur.get(liste.value.matricule).prenom}" />  <c:out value="${ listeUtilisateur.get(liste.value.matricule).nom}" /></p></div>
	    		<div class="col"><p><span class="title-contact">Contact : </span><c:out value="${ listeUtilisateur.get(liste.value.matricule).telephone }" /></p></div>
	    		<div class="col"><p><span class="title-defaillance">Type de defaillance : </span><c:out value="${ liste.value.tdp }" /></p></div>
	    		<div class="col"><p><span class="title-defaillance">Cause de la défaillance : </span><c:out value="${ liste.value.cdd }" /></p></div>
			</div>
		<div class="row">
			<div class="col title-mat"><h4>Matériaux utilisés </h4></div>
		</div><br>
		<div class="row">
			<div class="col-10">
				<div class="row">
					<c:forEach items="${ listeMateriel }" var="listeM" >
						<div class="col"><p>${ aide.contient(listeMaterielU.get(liste.key),listeM.value)?(listeM.value.quantite>0?listeM.value.nom:''):'' } <span class="badge badge-primary">${ aide.contient(listeMaterielU.get(liste.key),listeM.value)?(listeM.value.quantite>0?aide.quantite(listeMaterielU.get(liste.key),listeM.value):''):'' }</span></p></div>
						<div class="col"><p class="epuise">${ aide.contient(listeMaterielU.get(liste.key),listeM.value)?(listeM.value.quantite<0?listeM.value.nom:''):'' } <span class="badge badge-secondary">${ aide.contient(listeMaterielU.get(liste.key),listeM.value)?(listeM.value.quantite<0?aide.quantite(listeMaterielU.get(liste.key),listeM.value):''):'' }</span></p></div>
					</c:forEach>
				</div>
			</div>
			
    			<div class="col"><p class="title-date"><c:out value="${ liste.value.date }" /></p></div>
		</div>
	    </div>
	
	</c:forEach>  
	
		
    

</body>
</html>