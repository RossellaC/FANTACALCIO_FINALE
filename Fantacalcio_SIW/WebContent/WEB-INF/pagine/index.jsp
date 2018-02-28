<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html  lang="it">

<head>
	<title>Gioco_Fantacalcio</title>
	<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	<meta charset="utf-8" />
	<!--  <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
  <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<LINK rel="stylesheet" href="${pageContext.request.contextPath}/css/indexCss.css" type="text/css">
 -->
 <style type="text/css">

 body {
    
    background-image: url("images/palla.jpg");
	max-width: 100%;

}
	</style> 
	</head>

<body >
<script>

$(document).ready(
function(){
	myTime();
	setTimeout("myTime()",1000);
 });
 
function myTime(){
	var x=new Date();
	h=x.getHours();
	m=x.getMinutes();
	s=x.getSeconds();
	if(s<=9) s="0"+s;
	if(m<=9) m="0"+m;
	if(h<=9) h="0"+h;
	time=h+":"+m+":"+s;
	$("#rtime").val(time);
	setTimeout("myTime()",1000);
	 };
</script>


 <!--  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
       <button type="button" class="navbar-toggle" data-toggle="collapse" 
      data-target="#myNavbar" aria-expanded="false">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">FANTACALCIO</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Info <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="TutorialGioco.jsp">TutorialGioco</a></li>
            <li><a href="DoveSiamo.jsp">DoveSiamo</a></li>
          </ul>
        </li>
 </ul>
       <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
        <a  href="Utente_jsp.jsp"><span class="glyphicon glyphicon-user"> </span> Registrati</a>
        
          </li>
          
        <li><a href="Accedi.jsp"><span class="glyphicon glyphicon-log-in"></span> Accedi</a></li>
      </ul>
    </div>
  </div>
</nav>
-->

 <br /><br />
		<!-- Page Content -->
	<div class="container">
	
	<div  class="row">

	<div class="col-md-4 col-lg-4">
	<figure style=" text-align: center" class="col-lg-3">		
	<a href="images/pT.jpg"><img class="img-thumbnails" src="${pageContext.request.contextPath}/images/pT.jpg"  width="300" /></a>
	</figure>
	</div>
	<div class="col-md-8 col-lg-8">
	<hgroup>
	<h1>Fantacalcio</h1>
	<h2>&quot;Il vero vincitore di un gioco non &egrave; chi arriva primo ma chi si diverte di pi&ugrave;&quot;</h2>
	</hgroup>	
	</div>
	</div>
	
		<div class="row">
			<div class="col-md-2 col-lg-2">
				
			</div>

			<div class="col-md-10 col-lg-10" style="margin-top:20px">
				<div class="row carousel-holder">
					<div class="col-md-10 col-lg-10">
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
							<!-- Wrapper for slides -->
							<div class="carousel-inner" role="listbox">
								<div class="item active">
									<img class="slide-image" src="images/atalanta.jpg" alt="atalanta">
									<div class="carousel-caption">
									</div>
								</div>
								<div class="item">
									<img class="slide-image" src="images/benevento.jpg" alt="benevento">
									<div class="carousel-caption">
									</div>
								</div>
								<div class="item">
									<img class="slide-image" src="images/bologna.jpg" alt="bologna">
									<div class="carousel-caption">
									</div>
								</div>
								<div class="item">
									<img class="slide-image" src="images/cagliari.jpg" alt="cagliari">
									<div class="carousel-caption">
								</div>
								</div>
									<div class="item">
									<img class="slide-image" src="images/chievo_header.jpg" alt="chievo_header">
									<div class="carousel-caption">
									</div>
								</div>
									<div class="item">
									<img class="slide-image" src="images/crotone.jpg" alt="crotone">
									<div class="carousel-caption">
									</div>
								</div>
									<div class="item">
									<img class="slide-image" src="images/fiorentina.jpg" alt="fiorentina">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/genoa.jpg" alt="genoa">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/hellas.jpg" alt="hellas">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/inter.jpg" alt="inter">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/juventus.jpg" alt="juventus">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/lazio.jpg" alt="lazio">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/milan.jpg" alt="milan">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/napoli.jpg" alt="napoli">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/roma.jpg" alt="roma">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/sampdoria.jpg" alt="sampdoria">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/sassuolo.jpg" alt="sassuolo">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/spal.jpg" alt="spal">
									<div class="carousel-caption">
									</div>
								</div>
									<div class="item">
									<img class="slide-image" src="images/torino.jpg" alt="torino">
									<div class="carousel-caption">
									</div>
									</div>
									<div class="item">
									<img class="slide-image" src="images/udinese.jpg" alt="udinese">
									<div class="carousel-caption">
									</div>
									</div>
								</div>
							</div>
							
						</div>
					</div>
					</div>
				</div>	
				
				<div class="row" id="news">
				<div class="col-md-3 col-lg-3">
				<article>
				<header class="articleHeader">
					<big>NEWS</big>
				</header>
					<script src="//rss.bloople.net/?url=https%3A%2F%2Frss.fantagazzetta.com%2F&limit=3&showtitle=false&type=js"></script>
				</article>
				</div>
				
				<div id="orologio" class="col-md-6 col-lg-6">
					<div class="row" id="titleOr">
						<h3>SONO LE ORE   </h3>
					</div>
				<div class="row">
				<div class="col-md-6 col-lg-6" id="or">
						<input type="text" name="rtime" id="rtime" size="5" disabled>
				</div>
				</div>
				<div class="row" id="buttonOr">
					<button type="button" class="btn btn-success" onclick="document.getElementById('or').style.display='none';" value="nascondi">Nascondi</button>
					<button type="button" class="btn btn-success" onclick="document.getElementById('or').style.display='block';" value="mostra">Mostra</button>
				</div >
				</div>
				<div class="col-md-3 col-lg-3" id="divClassifica">
					<iframe class="classifica" src="http://sharing.iamcalcio.it/classifiche/3/serie-a/2017-2018/8.html" frameborder="0" scrolling="no" height="530" width="270"></iframe>
				</div>
				</div>
</div>


<div class="row footer" ><div class="col-md-12 col-lg-12"><p>Sito creato da Rossella & Rosita</div></p></div>
</body>
</html>