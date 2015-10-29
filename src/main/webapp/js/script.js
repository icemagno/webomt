var right_height;


function doLogin() {
	showMessageBox("Not implemented yet!");
}

// REDIMENSIONAMENTO DOS PAINÉIS DIREITO/ESQUERDO DO SITE
function setPanelSize() {
	var rightPanelSize = 265;
	$('#rightBox').css("width",rightPanelSize + "px");
	
	var left_width = $("#centerContainer").width() - ( rightPanelSize + 5 );
	$('#leftBox').css("width",left_width + "px");
	
	var left_height = $("#leftBox").height();
	
	if( right_height > left_height ) {
		$('#leftBox').css("min-height",right_height + "px");
	} else {
		$('#rightBox').css("height",left_height + "px");
	}
}

// APOS A INICIALIZAÇÃO DAS TABELAS DE PAGINAÇÂO
function doTableComplete() {
	setPanelSize();
}

//	MENSAGENS DE AVISO AO USUARIO - EXIBIR
function showMessageBox(texto) {
	if ( texto != '' ) {
		texto = texto + '<div onclick="hideMessageBox();" style="height: 13px;border:1px solid #000000;width: 50px;position:absolute;top:80px; right:5px" class="showMoreButton">CLOSE</div>';
		$("#msgText").html(texto);
		$("#centerContainer").animate({opacity: "0.2"}, 800);
		$("#blockAllPanel").css("display", "block");
		$("#msgBar").animate({"margin-left": "-150px"}, 800);
	}
}

function showDialogBox(texto, yesAction) {
	if ( texto != '' ) {
		texto = texto + '<div onclick="window.location.href=\'' + yesAction + '\'" style="background-color:#F2B50F; border:1px solid #F90101;color:#F90101;height: 13px;width: 50px;position:absolute;top:80px; right:5px" class="showMoreButton">YES</div>';
		texto = texto + '<div onclick="hideMessageBox();" style="height: 13px;border:1px solid #000000;width: 50px;position:absolute;top:80px; right:65px" class="showMoreButton">CLOSE</div>';
		$("#msgText").html(texto);
		$("#msgText").css("color","#DC3912");
		$("#centerContainer").animate({opacity: "0.2"}, 800);
		$("#blockAllPanel").css("display", "block");
		$("#msgBar").animate({"margin-left": "-150px"}, 800);
	}
}

//	MENSAGENS DE AVISO AO USUARIO - OCULTAR
function hideMessageBox() {
	$("#blockAllPanel").css("display", "none");
	$("#centerContainer").animate({opacity: "1"}, 800);
	$("#msgText").text();
	$("#msgBar").animate({"margin-left": "-5000px"}, 800);
}


function reloadDicas() {
	$('.dicas').tipTip({maxWidth: "auto", defaultPosition: "top", delay: 500});
	$('.dicas').tipTip('hide');
}



//	AO COMPLETAR O CARREGAMENTO DA PAGINA
$(document).ready(function() {
	right_height = $("#rightBox").height();
	
	// REDIMENSIONAMENTO DOS PAINÉIS DIREITO/ESQUERDO DO SITE
	setPanelSize();
	$(window).resize(function(){
		setPanelSize();
	}); 
	
	// DICAS DOS BOTÕES
	$('.dicas').tipTip({maxWidth: "auto", defaultPosition: "top", delay: 500});	

	
	// ESCONDE A MENSAGEM COM A TECLA <ESC>
	$(document).keyup(function(e) {
		if (e.keyCode == 27) { 
			hideMessageBox();
		}
	});		

});  