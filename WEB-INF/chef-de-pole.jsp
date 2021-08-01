<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Chef de pole</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/fonts/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/styleaccueil.css">
	<link rel="stylesheet" type="text/css" href="css/don - Copie.css">
	<link rel="stylesheet" type="text/css" href="css/chef-de-pole.css">
	<link rel="icon" type="image/png" href="img/logo.png" />
	
	<link rel="stylesheet" href="css/Hover-master/css/hover-min.css">
	<script src="js/jQuery.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>

	<%@ include file="header-chef-de-pole.jsp" %>
	<div class="banniere" style="background-image: url('img/ban.PNG');">
       	<div class="row">       	
        	<div class="col-sm-12 col-md-11 col-lg-11 col-xl-11"><h1>  ${user.fonction } </h1></div>
    		<div class="col-sm-12 col-md-1 col-lg-1 col-xl-1">
   				<form method="post" action="ChefDePole">
					<input type="text" name="name" value="<c:out value="${user.fonction }"/>" hidden   >
	    			<button type="submit" class="btn btn-secondary">Actualiser</button>
				</form> 		
   			</div>
   		</div>
    </div><br><br>
    
    <span> ${empty tf.erreurs ? tf.resultat: tf.erreurs }</span>
    
    
    <c:forEach items="${ listeIntervention }" var="liste" >

	<div class="container-fluid intervention">
		<div class= "row">
			<div class="col title-intervention">
    			<h2>Intervention n°<c:out value="${ liste.key }" /></h2>
    		</div>
		</div>
		
		<div class="row contact">
    		<div class="col"><p><span class="title-contact">Demandeur : </span><c:out value="${ listeUtilisateur.get(liste.value.matricule).prenom}" />  <c:out value="${ listeUtilisateur.get(liste.value.matricule).nom}" /></p></div>
    		<div class="col"><p><span class="title-contact">Contact : </span><c:out value="${ listeUtilisateur.get(liste.value.matricule).telephone }" /></p></div>
    		<div class="col"><p><span class="title-contact">Fonction : </span><c:out value="${ listeUtilisateur.get(liste.value.matricule).fonction }" /></p></div>
		</div>
		
		<div class="row defaillance">
    		<div class="col"><p><span class="title-defaillance">Type de defaillance : </span><c:out value="${ liste.value.tdp }" /></p></div>
    		<div class="col"><p><span class="title-defaillance">Service : </span><c:out value="${ liste.value.service }" /></p></div>
    		<div class="col"><p><span class="title-defaillance">Cause de la défaillance : </span><c:out value="${ liste.value.cdd }" /></p></div>
		</div>
		
		<div class="row status">
    		<div class="col">
    			<button type="button" class="btn btn-primary hvr-box-shadow-outset title-termine" data-toggle="modal" data-target="#T<c:out value="${ liste.key }" />">Terminé</button>
    		</div>
    		<div class="col">
    			<button type="button" class="btn btn-primary hvr-grow-shadow" id="title-specifier" data-toggle="modal" data-target="#S<c:out value="${ liste.key }" />">
  					Spécifier les matériaux à utiliser
				</button>
    		</div>
    		<div class="col"><p class="title-date"><c:out value="${ liste.value.date }" /></p></div>
		</div>
    </div>
    	<div class="modal fade" id="S<c:out value="${ liste.key }" />" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered" role="document">
			<div class="modal-content">
			
		    	<div class="modal-header">
		        	<h5 class="modal-title" id="exampleModalLongTitle">Veuillez spécifier les matériaux à utiliser</h5>
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		         		<span aria-hidden="true">&times;</span>
		        	</button>
		     	</div>
		     	
		     	<form method="post" action="materiaux-utilises">
					<div class="modal-body">
                   		<div class="form-group">
							<h3 style="text-align: center">Matériaux à utiliser</h3>
							<ul class="title-cat"><li><h4>Menuiserie</h4></li></ul>
							<div class="row materiaux">
							<c:forEach items="${ listeMateriel }" var="listeM" >
								<div class="col-lg-4 col-md-6 col-sm-6 col-materiaux">
									<input class="form-check-input" name="n<c:out value="${ listeM.key}" />" type="checkbox" value="<c:out value="${ listeM.value.nom }" />" id="bois">
							  		<label class="form-check-label" for="bois" class="label-bois"><c:out value="${ listeM.value.nom }" /></label>
							  		<input type="number" name="q<c:out value="${ listeM.key }" />" value="0" min="0" max="100" class="quantite">
									<input type="number" name="materielid<c:out value="${ listeM.key }" />" value="<c:out value="${ listeM.key }" />" hidden>
									<input type="text" name="nom<c:out value="${ listeM.key }" />" value="<c:out value="${ listeM.value.nom }" />" hidden>
									<input type="text" name="marque<c:out value="${ listeM.key }" />" value="<c:out value="${ listeM.value.marque }" />" hidden>
									<input type="text" name="reference<c:out value="${ listeM.key }" />" value="<c:out value="${ listeM.value.reference }" />" hidden>
								</div>
							</c:forEach> 
							</div>
						</div>
					</div>
					<input type="text" name="name" value="<c:out value="${user.fonction }"/>" hidden   >
					<input type="number" name="demandeid" value="<c:out value="${ liste.key }" />" hidden>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Quitter</button>
					  	<button type="submit" class="btn btn-primary">Enregistrer</button>
					</div>
				</form>
				
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	<div class="modal fade" id="T<c:out value="${ liste.key }" />" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered" role="document">
			<div class="modal-content">
			
		    	<div class="modal-header">
		        	<h2 class="modal-title" id="exampleModalLongTitle">Intervention terminée</h2>
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		         		<span aria-hidden="true">&times;</span>
		        	</button>
		     	</div>
		     	
		     	<form method="post" action="intervention-terminee">
					<div class="modal-body">
                   		<div class="form-group">
							<div class="row">
								<div class="col-12">
									<p>Veuillez renseigner les différents intervenants</p>
							  		<div class="input-group name">
									 	<div class="input-group-prepend">
									    	<span class="input-group-text">Prenom & Nom</span>
									  	</div>
									  	<textarea name="intervenants" class="form-control" aria-label="With textarea" rows="10" cols="50"></textarea>
									</div>
							  	</div>	
							</div><br>
							  	
						  	<div class="row">
						  		<div class="col-12">
							  		<label>Lieu de l'intervention : </label>
							  		 <input name="lieu" disabled value="<c:out value="${ liste.value.service }" />">
						  		</div><br><br>
						  	
							  	<div class="col-12">
							  		<label>Durée de l'intervention : </label>
							  		<input type="text" name="duree">
							  	</div>
						  	</div>
						  	
						  	<div class="row">					  			
					  			<div class="col-12">
					  				<div class="row">
					  					<div class="col"><p>Type de maintenance : </p></div>
					  					<div class="col">
					  						<input type="radio" id="correctif" value="correctif" name="type-maintenance" checked>
					  						<label for="correctif">Correctif</label>
					  					</div>
					  					<div class="col">
					  						<input type="radio" id="preventif" value="preventif" name="type-maintenance">
					  						<label for="preventif">Preventif</label>
					  					</div>
					  				</div>
					  			</div>
					  		</div>
						</div>
					</div>
					
					<input type="text" name="name" value="<c:out value="${user.fonction }"/>" hidden   >
					<input type="number" name="demandeid" value="<c:out value="${ liste.key }" />" hidden>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Quitter</button>
					  	<button type="submit" class="btn btn-primary">Enregistrer</button>
					</div>
				</form>
				
			</div>
		</div>
	</div>
    
</c:forEach>  
	




            
            
    
    
    

</body>
</html>
