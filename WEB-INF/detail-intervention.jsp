<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Détails Intervention</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/fonts/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/styleaccueil.css">
	<link rel="stylesheet" type="text/css" href="css/don - Copie.css">
	<link rel="stylesheet" type="text/css" href="css/details-intervention.css">
	<link rel="icon" type="image/png" href="img/logo.png" />
</head>
<body>

	<%@ include file="header.jsp" %><br><br><br><br>

	<div class="row">
		<div class="col-11">
			<div class="row">
				<div class="container-fluid intervention">
					<div class= "row">
						<div class="col title-intervention">
			    			<h2>Intervention n°${ intervention.id }</h2>
			    		</div>
					</div>
					
					<div class="row contact">
			    		<div class="col"><p><span class="title-contact">Demandeur : </span>${ utilisateur.prenom } ${ utilisateur.nom }</p></div>
			    		<div class="col"><p><span class="title-contact">Contact : </span>${ utilisateur.telephone }</p></div>
			    		<div class="col"><p><span class="title-contact">Fonction : </span>${ utilisateur.fonction }</p></div>
					</div>
					
					<div class="row defaillance">
			    		<div class="col"><p><span class="title-defaillance">Type de maintenance : </span>${ interventionT.tpm }</p></div>
			    		<div class="col"><p><span class="title-defaillance">Type de défaillance : </span>${ intervention.tdp }</p></div>
			    		<div class="col"><p><span class="title-defaillance">Cause de la défaillance : </span>${ intervention.cdd }</p></div>
					</div>
					
					<div class="row intervenants">
						<div class="col">
							<h3>Intervenant(s)</h3>
							<ul class="liste-intervenants">
								${ interventionT.intervenants }
							</ul>
						</div>
						<div class="col">
							<div class="row">
								<div class="col"><p class="title-visa">Visa demandeur <i class="fa fa-check fa-2x checked" aria-hidden="true"></i></p></div>
							</div>
						</div>
						<div class="col">
							<p><span class="title-lieu">Lieu de l'intervention : </span> ${ intervention.service }</p>
						</div>
						<div class="col">
							<p><span class="title-duree">Durée de l'intervention : </span> ${ interventionT.duree }</p>
						</div>
					</div>
					
					<div class="row materiaux-use">
						<div class="col-12 mat-use-content">
							<h3>Pièces de rechange et consommables</h3>
							<ul>
								<c:forEach items="${ listeMateriel }" var="listeM" >
									<li><i class="fa fa-check fa-lg checked" aria-hidden="true"></i> ${ listeM.value.quantite } - ${ listeM.value.nom } - ${ listeM.value.marque }</li>
								</c:forEach> 
							
							</ul>
						</div>
					</div>
					
					<div class="row status">
<!-- 			    		<div class="col"><span class="title-status-ok">OK</span></div> -->
			    		<div class="col"><p class="title-date">${ intervention.date }</p></div>
					</div>
			    </div>
			</div>
		</div>
		
<!-- 		<div class="col-1"> -->
<!-- 			<div class="row status-bar"> -->
<!-- 				<div class="col-12 termine">Terminé</div> -->
<!-- 				<div class="col-12 en-cours">En Cours...</div> -->
<!-- 				<div class="col-12 non-fait">Non Fait</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</div>   
    
   

</body>
</html>