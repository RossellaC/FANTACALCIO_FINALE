<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Lega"%>
<jsp:useBean id="lega" scope="request" type="model.Lega" />
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css"> 
		input:invalid,textarea:invalid {background-color: #FFCBB3;}
   input:valid,textarea:valid {background-color: #CEFFCE;}</style>
		<title>Pannello di controllo</title>
		<jsp:include page="/WEB-INF/pezzi/intestazione-html.jsp"></jsp:include>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.theme.min.css">
	</head>
<body>
	<jsp:include page="/WEB-INF/pezzi/navBar.jsp"></jsp:include>
	<div class="container">
		<header class="row">
			<div class="col-lg-12 col-ms-12 col">
				<h3>Crea la tua squadra</h3>
			</div>
		</header>
		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<form id="datiSquadra">
					<div class="form-group">
						<label for="nomeSquadra1">Nome squadra</label>
						<input value="" name="nome" type="text" class="form-control" id="nomeSquadra1" placeholder="Nome squadra..." required>
					</div>
					<input value="<%= lega.getId() %>" name="lega" type="hidden" id="legaSquadra1">
					<input value="<%= lega.getBudgetIniziale() %>" name="budget" type="hidden" id="budgetSquadra1">
				</form>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12">
				<!--ricerca giocatori -->
				<h3>Scegli giocatori</h3>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<p>Totale: <span id="tot">0</span> fantamilioni.</p>
					</div>
				</div>
				<form>
					<div class="form-group">
						<label for="nomeGiocatore1">Nome</label>
						<input value="" name="g" type="text" class="form-control" id="nomeGiocatore1" placeholder="...">
					</div>
				</form>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Codice</th>
							<th scope="col">Nome</th>
<!-- 							<th scope="col">Squadra</th> -->
							<th scope="col">Ruolo</th>
							<th scope="col">Costo</th>
							<th scope="col">Elimina</th>
						</tr>
					</thead>
					<tbody id="risultati">
					</tbody>
				</table>
				<button id="conferma" type="button" class="btn btn-primary disabled" data-toggle="modal">Conferma</button>
			</div>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">Spiacente</h4>
						</div>
						<div class="modal-body">
							<p id="modalContent">
								Non &egrave; stato possibile inserire la squadra. Riprovare in un secondo momento.
							</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pezzi/footer-html.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
	<script type="text/javascript">
	
		var portiere = 1;
		var difensori = 3;
		var centrocampisti = 4;
		var attaccanti = 3;
		var giocatoreScelto;
		
		var count = 0;
		
		$j = jQuery.noConflict();
		var giocatoriScelti = [];
		var datiGiocatori = [];
		var aggiornaEventi = function() {
			$j('.rimuovi').off('click');
			$j('.rimuovi').on('click', function() {
				let n = $j(this).parent().parent().children().first().text();
				giocatoriScelti.splice(n, 1);
				datiGiocatori.splice(n,1);
				//aggiorna();
				console.log('numero giocatori'+giocatoriScelti.length+" n "+n)
				$j('#risultati').empty();
				datiGiocatori = [];
				count = 0;
				portiere = 1;
				attaccanti = 3;
				centrocampisti = 4;
				difensori = 3;
				for(let i = 0; i < giocatoriScelti.length;i++){
					giocatoreScelto = giocatoriScelti[i];
					aggiorna();
				}
				if( giocatoriScelti.length == 0)
					$j('#tot').text("0");
			});
		};
		
		var aggiornaSomma = function() {
			let somma = 0;
			for (var i = 0; i < datiGiocatori.length; i++) {
				somma += datiGiocatori[i]['costo'];
			}
			$j('#tot').text(""+somma);
		};
		
		
		var aggiorna = function() {
			var g;
			
			var aggiungo = 0;
			for(var i = 0; i < datiGiocatori.length;i++){				
					console.log(giocatoreScelto+' - '+datiGiocatori[i]['codice']);
					if(giocatoreScelto == datiGiocatori[i]['codice']){
						aggiungo = 1;
						break;
					}
				
			}
			if(aggiungo == 1){
				$j('#myModalLabel').text('Giocatore già presente');
				$j('#modalContent').text("Ruoli disponibili: portieri: "+portiere+", difensori: "+difensori+"centrocampisti: "+centrocampisti+" attaccanti: "+attaccanti);
				$j('#myModal').modal({ keyboard: true });
			}else{
			$j.ajax({
				type: 'GET',
				url: context + '/datiCalciatore',
				data: {cod : giocatoreScelto},
				success: function(data, status) {
					if(status === 'success') {
						var obj = JSON.parse(data);
						console.log('ciccio'+data);
						g = obj;
						
						if(g['ruolo'] == 'Portiere' && portiere > 0){
							portiere--;
							datiGiocatori.push(g);
							$j('#risultati').append('<tr><td>' + count + '</td><td>' + g['nome'] + '</td><td>' + g['ruolo'] + '</td><td>' + g['costo'] + '</td><td><button type="button" class="btn btn-danger rimuovi">Elimina</button></td></tr>');
							$j('#nomeGiocatore1').val('');
							aggiornaSomma();
							aggiornaEventi();
							count++;
						} else if (g['ruolo'] == 'Attaccante' && attaccanti > 0){
							attaccanti--;
							datiGiocatori.push(g);
							$j('#risultati').append('<tr><td>' + count + '</td><td>' + g['nome'] + '</td><td>' + g['ruolo'] + '</td><td>' + g['costo'] + '</td><td><button type="button" class="btn btn-danger rimuovi">Elimina</button></td></tr>');
							$j('#nomeGiocatore1').val('');
							aggiornaSomma();
							aggiornaEventi();
							count++;
						} else if (g['ruolo'] == 'Centrocampista' && centrocampisti > 0){
							centrocampisti--;
							datiGiocatori.push(g);
							$j('#risultati').append('<tr><td>' + count + '</td><td>' + g['nome'] + '</td><td>' + g['ruolo'] + '</td><td>' + g['costo'] + '</td><td><button type="button" class="btn btn-danger rimuovi">Elimina</button></td></tr>');
							$j('#nomeGiocatore1').val('');
							aggiornaSomma();
							aggiornaEventi();
							count++;
						} else if (g['ruolo'] == 'Difensore' && difensori > 0){
						difensori--;
						datiGiocatori.push(g);
						$j('#risultati').append('<tr><td>' + count + '</td><td>' + g['nome'] + '</td><td>' + g['ruolo'] + '</td><td>' + g['costo'] + '</td><td><button type="button" class="btn btn-danger rimuovi">Elimina</button></td></tr>');
						$j('#nomeGiocatore1').val('');
						aggiornaSomma();
						aggiornaEventi();
						count++;
					}else {
							$j('#myModalLabel').text('Non puoi aggiungere giocatore');
							$j('#modalContent').text("Ruoli disponibili: portieri: "+portiere+", difensori: "+difensori+"centrocampisti: "+centrocampisti+" attaccanti: "+attaccanti);
							$j('#myModal').modal({ keyboard: true });
						}	
					}
				}
			});
			}
			
			
		};
		
		
		 $j('#nomeSquadra1').on('change',function(){
			 if(giocatoriScelti.length >= 11 &&  $j('#nomeSquadra1').val() != ''){
					$j('#conferma').removeClass('disabled');
					$j('#conferma').on('click', function(){
						console.log( $j('#nomeSquadra1').val());
						let tot = $j('#tot').text();
						let b = $j('#budgetSquadra1').val();
						let bud = parseInt(b);
						if(tot <= bud) {
							let obj = {};
							obj['nomeSquadra'] = $j('#nomeSquadra1').val();
							obj['lega'] = $j('#legaSquadra1').val();
							obj['giocatori'] = giocatoriScelti;
							console.log(obj);
							
							$j.ajax({
								type: 'POST',
								dataType: 'json',
								url: context + '/creazioneSquadra',
								data: JSON.stringify(obj),
								contentType: 'application/json',
								mimeType: 'application/json',
								success: function(status, data) {
									if(data === 'success') {
										console.log("totale"+tot);
										console.log("budget"+bud);
										$j('#myModalLabel').text('Complimenti!');
										$j('#modalContent').text("Squadra creata con successo.");
										$j('#myModal').modal({ keyboard: true });
									} else {
										$j('#myModal').modal({ keyboard: true });
									}
								}
							});
						} else {
							
							$j('#myModalLabel').text('Attenzione!');
							$j('#modalContent').text("Hai superato il budget fissato per questa lega.");
							
							$j('#myModal').modal({ keyboard: true });
						}

					});
				}
			 else{
				 $j('#conferma').addClass('disabled');
				 $j('#conferma').on('click', null);
			 }
		 });
		 
		
		$j('#nomeGiocatore1').autocomplete({
			autoFocus: true, 
			delay: 250, 
			minLength: 2,
			source: context + '/ricercaCalciatori',
			select: function( event, ui ) {
				giocatoriScelti.push(ui.item.value);
				giocatoreScelto = ui.item.value;
				if(giocatoriScelti.length >= 1 &&  $j('#nomeSquadra1').val() != ''){
					$j('#conferma').removeClass('disabled');
					$j('#conferma').on('click', function(){
						console.log( $j('#nomeSquadra1').val());
						let tot = $j('#tot').text();
						let b = $j('#budgetSquadra1').val();
						let bud = parseInt(b);
						if(tot <= bud) {
							let obj = {};
							obj['nomeSquadra'] = $j('#nomeSquadra1').val();
							obj['lega'] = $j('#legaSquadra1').val();
							obj['giocatori'] = giocatoriScelti;
							console.log(obj);
							
							$j.ajax({
								type: 'POST',
								dataType: 'json',
								url: context + '/creazioneSquadra',
								data: JSON.stringify(obj),
								contentType: 'application/json',
								mimeType: 'application/json',
								success: function(status, data) {
									if(data === 'success') {
										console.log("totale"+tot);
										console.log("budget"+bud);
										$j('#myModalLabel').text('Complimenti!');
										$j('#modalContent').text("Squadra creata con successo.");
										$j('#myModal').modal({ keyboard: true });
									} else {
										$j('#myModal').modal({ keyboard: true });
									}
								}
							});
						} else {
							
							$j('#myModalLabel').text('Attenzione!');
							$j('#modalContent').text("Hai superato il budget fissato per questa lega.");
							
							$j('#myModal').modal({ keyboard: true });
						}

					});
				}
				aggiorna();
			}
		});
		
		/*$j('#conferma').on('click', function(){
			
			let tot = $j('#tot').text();
			let b = $j('#budgetSquadra1').val();
			let bud = parseInt(b);
			if(tot <= bud) {
				let obj = {};
				obj['nomeSquadra'] = $j('#nomeSquadra1').val();
				obj['lega'] = $j('#legaSquadra1').val();
				obj['giocatori'] = giocatoriScelti;
				console.log(obj);
				
				$j.ajax({
					type: 'POST',
					dataType: 'json',
					url: context + '/creazioneSquadra',
					data: JSON.stringify(obj),
					contentType: 'application/json',
					mimeType: 'application/json',
					success: function(status, data) {
						if(data === 'success') {
							console.log("totale"+tot);
							console.log("budget"+bud);
							$j('#myModalLabel').text('Complimenti!');
							$j('#modalContent').text("Squadra creata con successo.");
							$j('#myModal').modal({ keyboard: true });
						} else {
							$j('#myModal').modal({ keyboard: true });
						}
					}
				});
			} else {
				
				$j('#myModalLabel').text('Attenzione!');
				$j('#modalContent').text("Hai superato il budget fissato per questa lega.");
				
				$j('#myModal').modal({ keyboard: true });
			}

		});*/
		
	</script>
</body>

</html>