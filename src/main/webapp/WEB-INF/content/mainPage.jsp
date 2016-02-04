<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../header.jsp" %>



				<div id="leftBox" > 
				
					<div id="bcbMainButtons" class="basicCentralPanelBar">
						<%@ include file="buttons.jsp" %>
					</div>
					
					<div id="basicCentralPanel">
					
						<div class="basicCentralPanelBar">
							<img src="img/federations.png">
							<div class="basicCentralPanelBarText">Manage Federations</div>
						</div>
						
						<div class="menuBarMain">
							<img onclick="showNewPannel();" title="New FOM/SOM" class="button dicas" src="img/add.png">
						</div>						
						
						<div id="newPannel" style="display:none; height:180px; width:95%; margin:0 auto;margin-top:10px;margin-bottom:10px;">
							<form action="doNewFederation" enctype="multipart/form-data" method="post" id="formPost">
								<table>

									<tr>
										<td class="tableCellFormLeft">FOM File</td>
										<td class="tableCellFormRight"> 
											<input id="fedFom" name="fomFile" class="tableCellFormInputText" type="file"/> 
										</td>
									</tr>
									
									
								</table>
							</form>
							<div onclick="doPost()" class="basicButton">Send</div>							
							<div onclick="cancelNewPanel()" class="basicButton">Cancel</div>							
						
						</div>
						
						
						<div style="margin : 0 auto; width : 95%; margin-top:10px;"  >
							<table class="tableForm"  id="example" >
								<thead>
									<tr>
										<th style="width:80px;">Name</th>
										<th style="width:20px;">&nbsp;</th>
										<th style="width:350px;">Description</th>
										<th style="width:70px;">FOM File</th>
										<th style="width:65px;">&nbsp;</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="federation" items="${federationList}">
										<tr>
											<td>${federation.name}&nbsp;</td>
											<td>
												<c:if test="${ not empty federation.glyph}">
													<img alt="" class="miniButton dicas" title="Glyph" src="data:image/png;base64,${federation.glyph}"  />
												</c:if>&nbsp;
											</td>											
											<td>${federation.description}&nbsp;</td>
											<td><a href="showFomFile?idFederation=${federation.idFederation}">${federation.definitionFile}</a></td>
											<td>
												<img alt="" class="miniButton dicas" title="Delete" onclick="deleteFederation('${federation.idFederation}')" src="img/delete.png"/>
												<img alt="" class="miniButton dicas" title="Edit" onclick="editFederation('${federation.idFederation}')" src="img/edit.png"/>
												<img alt="" class="miniButton dicas" title="View" onclick="viewFederation('${federation.idFederation}')" src="img/search.png"/>
											</td>
											
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>												
					
				</div>
				
				<div id="rightBox"> 
					<%@ include file="commonpanel.jsp" %>
				</div>
				
				
<script>

function doPost() {
	var tag = $("#wfTag").val();
	var desc = $("#wfDescription").val();
	if ( (tag == '') || ( desc == '' ) ) {
		showMessageBox('Please fill all required fields.');
		return;
	} 
	$("#formPost").submit();
}

function showNewPannel() {
	$("#newPannel").css("display","block");
}

function cancelNewPanel() {
	$("#newPannel").css("display","none");
}

function viewFederation(idfed) {
	window.location.href="showFomFile?idFederation=" + idfed;
}

function editFederation(idfed) {
	window.location.href="editFomFile?idFederation=" + idfed;
}

$(document).ready(function() {
	$('#example').dataTable({
        "oLanguage": {
            "sUrl": "js/pt_BR.txt"
        },	
        "iDisplayLength" : 10,
		"bLengthChange": false,
		"fnInitComplete": function(oSettings, json) {
			doTableComplete();
		},
		"bAutoWidth": false,
		"sPaginationType": "full_numbers",
		"aoColumns": [ 
					  { "sWidth": "20%" },
					  { "sWidth": "40%" },
					  { "sWidth": "15%" },
					  { "sWidth": "25%" }]						
	} ).fnSort( [[0,'desc']] );
} );	
	
</script>				
				
<%@ include file="../../footer.jsp" %>
				