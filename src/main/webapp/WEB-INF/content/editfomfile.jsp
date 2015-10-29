<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../../header.jsp" %>


				<div id="leftBox"> 
				
					<div id="bcbMainButtons" class="basicCentralPanelBar">
						<%@ include file="buttons.jsp" %>
					</div>
									
					<div id="basicCentralPanel">
					
						<div class="basicCentralPanelBar">
							<img alt="" src="img/xml.png" />
							<div class="basicCentralPanelBarText">Edit ${federation.definitionFile} FOM from ${federation.name} Federation</div>
						</div>
						
						<div class="menuBarMain">
							<img onclick="back()" title="Back" class="button dicas" src="img/back.png">
							<img alt="" onclick="save();" title="Save File" class="button dicas" src="img/save.png" />
						</div>						
						
						<div style="margin : 0 auto; width : 95%; margin-top:10px;" id="dtTableContainer">
							<form method="post" action="saveFomFile" id="frmSave">
								<input type="hidden" name="idFederation" value="${federation.idFederation}" />
								<textarea style="border:0px; height:900px;width:100%" id="code" name="code">${fomFile}</textarea>
							</form>
						</div>
						
					</div>					
					
				</div>
				
				
				<div id="rightBox"> 
					<%@ include file="commonpanel.jsp" %>
				</div>
				
				
<script>
	
	$(document).ready(function() {
		CodeMirror.fromTextArea(document.getElementById("code"), { mode: "xml", lineNumbers: true });		
	});
	
	function cancel(idfed) {
		window.location.href="federations";
	}

	function save() {
		$("#frmSave").submit();
	}

	function back() {
		window.history.back();
	}

</script>				
				
<%@ include file="../../footer.jsp" %>
				