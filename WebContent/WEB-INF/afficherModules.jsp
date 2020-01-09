<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<link href="Bon/prism.css" rel="stylesheet">
<link href="Bon/ghpages-materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="Bon/css.css" rel="stylesheet" type="text/css">
<link href="Bon/icon.css" rel="stylesheet">
<script src="Bon/cbgapi.loaded_1" async=""></script>
<script src="Bon/cbgapi.loaded_0" async=""></script>
<script async="" src="Bon/analytics.js"></script>
<script id="twitter-wjs" src="Bon/widgets.js"></script>
<script>
	window.liveSettings = {
		api_key : "a0b49b34b93844c38eaee15690d86413",
		picker : "bottom-right",
		detectlang : true,
		dynamic : true,
		autocollect : true
	};
</script>
<script src="Bon/live.js"></script>
<style type="text/css">
.txlive {
	display: none;
}
</style>
<link href="Bon/nouislider.css" rel="stylesheet">
<script type="text/javascript" src="Bon/manifest.jsonp"></script>
<script id="_carbonads_projs" type="text/javascript"
	src="Bon/C6AILKT.json"></script>
<style type="text/css">
.txlive-langselector {
	position: fixed;
	z-index: 999999;
	min-width: 120px;
	line-height: 32px;
	background-color: rgba(0, 0, 0, 0.75);
	box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
	color: #fff;
	font-size: 14px;
	font-family: inherit;
}

.txlive-langselector * {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
	border-radius: 0;
	-moz-border-radius: 0;
	-webkit-border-radius: 0;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-border-radius: 0;
	opacity: 1;
}

.txlive-langselector.txlive-langselector-topleft {
	top: 0;
	left: 0;
	right: auto;
	bottom: auto;
	border-radius: 0 0 2px 0;
	-moz-border-radius: 0 0 2px 0;
	-webkit-border-radius: 0 0 2px 0;
}

.txlive-langselector.txlive-langselector-topright {
	top: 0;
	left: auto;
	right: 0;
	bottom: auto;
	border-radius: 0 2px 0 0;
	-moz-border-radius: 0 2px 0 0;
	-webkit-border-radius: 0 2px 0 0;
}

.txlive-langselector.txlive-langselector-bottomleft {
	top: auto;
	left: 0;
	right: auto;
	bottom: 0;
	border-radius: 0 2px 0 0;
	-moz-border-radius: 0 2px 0 0;
	-webkit-border-radius: 0 2px 0 0;
}

.txlive-langselector.txlive-langselector-bottomright {
	top: auto;
	left: auto;
	right: 0;
	bottom: 0;
	border-radius: 2px 0 0 0;
	-moz-border-radius: 2px 0 0 0;
	-webkit-border-radius: 2px 0 0 0;
}

.txlive-langselector .txlive-langselector-toggle {
	overflow: hidden;
	display: block;
	padding: 2px 16px;
	width: 100%;
	height: 36px;
	cursor: pointer;
	cursor: hand;
}

.txlive-langselector.txlive-langselector-topleft .txlive-langselector-toggle
	{
	overflow: hidden;
	display: block;
	border-top: 2px solid #006f9f;
	padding: 2px 16px;
	height: 36px;
	line-height: 32px;
	cursor: pointer;
	cursor: hand;
}

.txlive-langselector.txlive-langselector-topright .txlive-langselector-toggle
	{
	overflow: hidden;
	display: block;
	border-top: 2px solid #006f9f;
	padding: 2px 16px;
	height: 36px;
	line-height: 32px;
	cursor: pointer;
	cursor: hand;
}

.txlive-langselector.txlive-langselector-bottomleft .txlive-langselector-toggle
	{
	overflow: hidden;
	display: block;
	border-bottom: 2px solid #006f9f;
	padding: 2px 16px;
	height: 36px;
	line-height: 32px;
	cursor: pointer;
	cursor: hand;
}

.txlive-langselector.txlive-langselector-bottomright .txlive-langselector-toggle
	{
	overflow: hidden;
	display: block;
	border-bottom: 2px solid #006f9f;
	padding: 2px 16px;
	height: 36px;
	line-height: 32px;
	cursor: pointer;
	cursor: hand;
}

.txlive-langselector .txlive-langselector-current {
	float: left;
	padding: 0;
	max-width: 200px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}

.txlive-langselector .txlive-langselector-marker {
	float: right;
	display: block;
	position: relative;
	width: 0;
	height: 0;
	margin-left: 8px;
	margin-top: 13px;
	border-right: 4px dashed transparent;
	border-left: 4px dashed transparent;
}

.txlive-langselector-topright .txlive-langselector-marker,.txlive-langselector-topleft .txlive-langselector-marker
	{
	border-top: 4px solid #fff;
}

.txlive-langselector-bottomright .txlive-langselector-marker,.txlive-langselector-bottomleft .txlive-langselector-marker
	{
	border-bottom: 4px solid #fff;
}

.txlive-langselector-list {
	position: absolute;
	width: 100%;
	margin: 0;
	padding: 10px 0;
	display: none;
	background-color: #eaf1f7;
	box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
	color: #666;
	list-style-type: none;
}

.txlive-langselector-list.txlive-langselector-list-opened {
	display: block;
}

.txlive-langselector-list>li {
	padding: 0 16px;
	width: 100%;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}

.txlive-langselector-list>li:hover {
	background-color: #b0b9c1;
	color: #fff;
	cursor: pointer;
	cursor: hand;
}

.txlive-langselector-topright>.txlive-langselector-list {
	top: 40px;
	left: auto;
	right: 0;
	bottom: auto;
	border-bottom: 1px solid #f4f7f9;
}

.txlive-langselector-topleft>.txlive-langselector-list {
	top: 40px;
	left: 0;
	right: auto;
	bottom: auto;
	border-bottom: 1px solid #f4f7f9;
}

.txlive-langselector-bottomright>.txlive-langselector-list {
	top: auto;
	left: auto;
	right: 0;
	bottom: 40px;
	border-top: 1px solid #f4f7f9;
}

.txlive-langselector-bottomleft>.txlive-langselector-list {
	top: auto;
	left: 0;
	right: auto;
	bottom: 40px;
	border-top: 1px solid #f4f7f9;
}

.txlive-langselector-topright>.txlive-langselector-list,.txlive-langselector-bottomright>.txlive-langselector-list
	{
	border-radius: 2px 0 0 2px;
	-moz-border-radius: 2px 0 0 2px;
	-webkit-border-radius: 2px 0 0 2px;
}

.txlive-langselector-topleft>.txlive-langselector-list,.txlive-langselector-bottomleft>.txlive-langselector-list
	{
	border-radius: 0 2px 2px 0;
	-moz-border-radius: 0 2px 2px 0;
	-webkit-border-radius: 0 2px 2px 0;
}
.djina{
    margin-left: auto;
    margin-right: auto;
/*     width: 6em */
}
</style>
<style media="screen">
  .djina{
    margin-left: 25%;
    margin-right: 25%;
    margin-bottom: 0%;
  }
  .djina2{
    margin-bottom: 30%;
}

  }
</style>
<script type="text/javascript" src="Bon/a"></script>
<script type="text/javascript" charset="utf-8" async=""
	src="Bon/button.js"></script>
</head>
<body>

	<c:import url="menu_thiam.jsp" />
	
	
	
      <ul class="collection with-header">
        <li class="collection-header"><h4>Liste des Modules.</h4></li>
        
        <c:forEach items="${modules}" var="news">
        	<li class="collection-item">
        		<div>${news.nom_module}
        		
        			<a href="SupprimerModule?val=${news.id_module }" class="secondary-content">
	        			<i class="material-icons">
	        				delete
	        			</i>
        			</a>
        			
        			<a href="ModifierModule?val=${news.id_module }" class="secondary-content">
	        			<i class="material-icons">
	        				mode_edit
	        			</i>
        			</a>
        			
        			<a href="ListeModule?val=${news.id_module }" class="secondary-content">
	        			<i class="material-icons">
	        				visibility
	        			</i>
        			</a>       
        			
        		</div>
        	</li>
        
        
        </c:forEach>
        
        
        
        
      
      </ul>
            
	
<!-- 	<div class="center"> -->
<!--         <h1>Liste des enseignants.</h1> -->
<!--     </div> -->
<%-- 	<c:forEach items="${enseignants}" var="news"> --%>
<!-- 		<ul class="collapsible" data-collapsible="accordion"> -->
<!-- 		    <li class="hoverable"> -->
<!-- 				<div class="collapsible-header"> -->
<!-- 					<i class="material-icons"> -->
<!-- 						filter_drama -->
<!-- 					</i> -->
<%-- 					<p>Nom : ${news.nom_enseignant }</p> --%>
<%-- 					<p>Prénom : ${news.prenom_enseignant }</p> --%>
<!-- 				</div> -->
<!-- 				<div class="collapsible-body"> -->
<%-- 					<p>Matricule : ${news.id }</p> --%>
<%-- 					<p>Nom : ${news.nom_enseignant }</p> --%>
<%-- 					<p>Prénom : ${news.prenom_enseignant }</p> --%>
<%-- 					<p>Adresse : ${news.adresse_enseignant }</p> --%>
<%-- 					<p>Telephone : ${news.telephone_enseignant }</p> --%>
<%-- 					<p>email : ${news.email_enseignant }</p> --%>
<%-- 					<p>Titre : ${news.titre_enseignant }</p> --%>
<%-- 					<p>Discipline : ${news.discipline_enseignant }</p> --%>
<%-- 					<p>Grade : ${news.grade_enseignant }</p> --%>
<%-- 					<a href="SupprimerEnseignant?val=${news.id }" ><i class="medium material-icons ">delete</i></a> --%>
<%-- 					<a href="ModifierEnseignant?val=${news.id }" ><i class="medium material-icons right">mode_edit</i></a> --%>
<!-- 				</div> -->
<!-- 			</li> -->
			
<!-- 	 	</ul> -->
<%-- 	</c:forEach> --%>
  
<!--   <ul class="collapsible" data-collapsible="accordion"> -->
<%--   	<c:choose> --%>
<%--   		<c:when test="${ empty requestScope.enseignants }"> --%>
<!-- 			<p class="erreur">Aucun enseignant enregistré.</p> -->
<%-- 		</c:when> --%>
<%-- 		<c:otherwise> --%>
<%-- 			<c:forEach items="${ requestScope.enseignants }" var="mapEnseignants" varStatus="boucle"> --%>
<!-- 			    <li> -->
<!-- 					<div class="collapsible-header"> -->
<!-- 						<i class="material-icons"> -->
<!-- 							filter_drama -->
<!-- 						</i> -->
<%-- 						<p>Nom : ${mapEnseignants.nom_enseignant }</p> --%>
<%-- 						<p>Prénom : ${mapEnseignants.prenom_enseignant }</p> --%>
<!-- 					</div> -->
<!-- 			      	<div class="collapsible-body"> -->
<%-- 			      		<p>Matricule : ${mapEnseignants.id_enseignant }</p> --%>
<%-- 			      		<p>Nom : ${mapEnseignants.nom_enseignant }</p> --%>
<%-- 			      		<p>Prénom : ${mapEnseignants.prenom_enseignant }</p> --%>
<%-- 			      		<p>Adresse : ${mapEnseignants.adresse_enseignant }</p> --%>
<%-- 			      		<p>Telephone : ${mapEnseignants.telephone_enseignant }</p> --%>
<%-- 			      		<p>email : ${mapEnseignants.email_enseignant }</p> --%>
<%-- 			      		<p>Titre : ${mapEnseignants.titre_enseignant }</p> --%>
<%-- 			      		<p>Discipline : ${mapEnseignants.discipline_enseignant }</p> --%>
<%-- 			      		<p>Grade : ${mapEnseignants.grade_enseignant }</p> --%>
			      		
<!-- 			      	</div> -->
<!-- 			    </li> -->
<%-- 			</c:forEach> --%>
<%-- 		</c:otherwise> --%>
<%--     </c:choose> --%>
<!--   </ul> -->
        
		
    <c:import url="footer.jsp" />




























	<!--  Scripts-->
	<script src="Bon/jquery-2.js"></script>
	<script>
		if (!window.jQuery) {
			document.write('<script src="bin/jquery-2.1.1.min.js"><\/script>');
		}
	</script>
	<script src="Bon/jquery.js"></script>
	<script src="Bon/prism.js"></script>
	<script src="Bon/lunr.js"></script>
	<script src="Bon/search.js"></script>
	<script src="Bon/materialize.js"></script>
	<script src="Bon/init.js"></script>
	<!-- Twitter Button -->
	<script>
		!function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
					.test(d.location) ? 'http' : 'https';
			if (!d.getElementById(id)) {
				js = d.createElement(s);
				js.id = id;
				js.src = p + '://platform.twitter.com/widgets.js';
				fjs.parentNode.insertBefore(js, fjs);
			}
		}(document, 'script', 'twitter-wjs');
	</script>

	<!-- Google Plus Button-->
	<script src="Bon/platform.js" async="" defer="defer" gapi_processed="true"></script>

	<!--  Google Analytics  -->
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-56218128-1', 'auto');
		ga('require', 'displayfeatures');
		ga('send', 'pageview');
	</script>

	<script src="Bon/nouislider.js"></script>
	<iframe name="oauth2relay815921183" id="oauth2relay815921183"
		src="Bon/postmessageRelay.htm"
		style="width: 1px; height: 1px; position: absolute; top: -100px;"
		tabindex="-1" aria-hidden="true"></iframe>
	<script type="text/javascript">
		var slider = document.getElementById('range-input');
		noUiSlider.create(slider, {
			start : [ 20, 80 ],
			connect : true,
			step : 1,
			range : {
				'min' : 0,
				'max' : 100
			},
			format : wNumb({
				decimals : 0
			})
		});
	</script>

	<script type="text/javascript">
		$('.fixed-action-btn').openFAB();
		$('.fixed-action-btn').closeFAB();
		$('.fixed-action-btn.toolbar').openToolbar();
		$('.fixed-action-btn.toolbar').closeToolbar();
	</script>
</body>
</html>