<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../header.jsp" %>

				<div id="leftBox2"> 
					
					<div id="basicCentralPanel">
					
						<div class="basicCentralPanelBar">
							<img alt="" src="img/xml.png" />
							<div class="basicCentralPanelBarText">Inspect file ${federation.definitionFile}</div>
						</div>
						
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="width:90%; float:left">
								<tr><td>Name</td><td>${omt.modelIdentification.name.value}</td></tr>
								<tr><td>Type</td><td>${omt.modelIdentification.type.value}</td></tr>
								<tr><td>Version</td><td>${omt.modelIdentification.version.value}</td></tr>
								<tr><td>Description</td><td>${omt.modelIdentification.description.value}</td></tr>
								<tr><td>Modification Date</td><td>${omt.modelIdentification.modificationDate.value}</td></tr>
								<tr><td>Security Classification</td><td>${omt.modelIdentification.securityClassification.value}</td></tr>
								<tr><td>Purpose</td><td>${omt.modelIdentification.purpose.value}</td></tr>
								<tr><td>Application Domain</td><td>${omt.modelIdentification.applicationDomain.value}</td></tr>
								<tr><td>Description</td><td>${omt.modelIdentification.description.value}</td></tr>
								<tr><td>Use Limitation</td><td>${omt.modelIdentification.useLimitation.value}&nbsp;</td></tr>
								<tr><td>POC</td><td>
									<c:forEach var="poc" items="${omt.modelIdentification.poc}">
										${poc.pocName.value} - ${poc.pocOrg.value}<br/>
									</c:forEach>&nbsp; 
								</td></tr>
								
							</table>
							<c:if test="${ not empty glyphImage}">
							<img alt="" src="data:image/png;base64,${glyphImage}" style="float:right;margin:0px;height:80px;width:80px;margin-top:20px;" />
							</c:if>

						</div>

						<div class="basicCentralPanelBar">
							<img alt="" src="img/object.png"/>
							<div class="basicCentralPanelBarText">Object Classes</div>
						</div>

						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th >Name</th>
									<th>Sharing</th>
									<th>Descendants</th>
									<th>Attributes</th>
								</tr>
								<c:forEach var="object" items="${objects}">
									<tr>
										<td>${object.fullyQualifiedName}</td>
										<td>${object.sharing}</td>
										<td>
											<table>
												<c:forEach var="child" items="${object.children}">
													<tr><td style="color:#00933B">${child.name}</td></tr>
												</c:forEach>
											</table>
										</td>
										<td>
											<table>
												<c:forEach var="attr" items="${object.attributes}">
													<tr>
														<td style="width:90px;color:#F90101;cursor:pointer" class="dicas" title="${attr.ownerShip}, ${attr.transportation}" >${attr.name}</td>
														<td style="width:80px;color:#F90101">${attr.dataType}</td>
														<td style="width:90px;color:#F90101">
															<c:forEach var="dimm" items="${attr.dimensions}">
																${dimm}<br/> 
															</c:forEach>
														</td>
													</tr>
												</c:forEach>
											</table>
										</td>
									</tr>
								</c:forEach>
							</table>							
						</div>

						<div class="basicCentralPanelBar">
							<img alt="" src="img/interaction.png"/>
							<div class="basicCentralPanelBarText">Interaction Classes</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<c:forEach var="interaction" items="${omt.interactions.interactionClass.interactionClass}">
								<div style="height:30px;background-color:#FFFFFF; padding-left:4px;padding-right:4px; margin-bottom: 5px;width:230px" class="clusterBoard interactionBox" >
									<div class="userBoardT3" style="color:#4D7A93;font-weight:bold;margin-left:0px;width:95%;text-align:center">${interaction.name.value}</div>
									<div class="userBoardT3" style="color:#00933B;font-weight:bold;margin-left:0px;width:95%;text-align:center">${interaction.order.value}</div>
									<div class="userBoardT2" style="color:black;margin:0px;width:100%;">
										<table style="margin-bottom:4px;margin-top:4px;">
											<tr><th>Name</th><th>Data Type</th></tr>
											<c:forEach var="icParam" items="${interaction.parameter}">
												<tr>
													<td>${icParam.name.value}</td>
													<td>${icParam.dataType.value}</td>
												</tr>
											</c:forEach>
											
										</table>
									</div>
								</div>
							</c:forEach>
						</div>


						<div class="basicCentralPanelBar">
							<img alt="" src="img/dimension.png"/>
							<div class="basicCentralPanelBarText">Dimensions</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
							<tr><th>Name</th><th>Data Type</th><th>Value</th></tr>
							<c:forEach var="dimension" items="${omt.dimensions.dimension}">
								<tr><td>${dimension.name.value}</td><td>${dimension.dataType.value}</td><td>${dimension.value.value}</td></tr>
							</c:forEach>
							</table>
						</div>

						<div class="basicCentralPanelBar">
							<img alt="" src="img/datatype.png"/>
							<div class="basicCentralPanelBarText">Data Types</div>
						</div>


						<div class="basicCentralPanelBar">
							<img alt="" src="img/right.png"/>
							<div class="basicCentralPanelBarText" style="font-size:11px;color:#4D7A93">Basic Data Types</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th>Name</th>
									<th>Size</th>
									<th>Interpretation</th>
									<th>Endian</th>
									<th>Encoding</th>
								</tr>
							<c:forEach var="basicData" items="${omt.dataTypes.basicDataRepresentations.basicData}">
								<tr>
									<td>${basicData.name.value}</td>
									<td>${basicData.size.value}</td>
									<td>${basicData.interpretation.value}</td>
									<td>${basicData.endian.value}</td>
									<td>${basicData.encoding.value}</td>
								</tr>
							</c:forEach>
							</table>
						</div>


						<div class="basicCentralPanelBar">
							<img alt="" src="img/right.png"/>
							<div class="basicCentralPanelBarText" style="font-size:11px;color:#4D7A93">Simple Data Types</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th>Name</th>
									<th>Representation</th>
									<th>Units</th>
									<th>Resolution</th>
									<th>Accuracy</th>
									<th style="width:40%">Semantics</th>
								</tr>
							<c:forEach var="simpleData" items="${omt.dataTypes.simpleDataTypes.simpleData}">
								<tr>
									<td>${simpleData.name.value}</td>
									<td>${simpleData.representation.value}</td>
									<td>${simpleData.units.value}</td>
									<td>${simpleData.resolution.value}</td>
									<td>${simpleData.accuracy.value}</td>
									<td>${simpleData.semantics.value}</td>
								</tr>
							</c:forEach>
							</table>
						</div>


						<div class="basicCentralPanelBar">
							<img alt="" src="img/right.png"/>
							<div class="basicCentralPanelBarText" style="font-size:11px;color:#4D7A93">Enumerated Data Types</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th>Name</th>
									<th>Representation</th>
									<th style="width:20%">Enumerator</th>
									<th style="width:40%">Semantics</th>
								</tr>
							<c:forEach var="enumeratedData" items="${omt.dataTypes.enumeratedDataTypes.enumeratedData}">
								<tr>
									<td>${enumeratedData.name.value}</td>
									<td>${enumeratedData.representation.value}</td>
									<td>
										<table style="width:95%">
											<c:forEach var="enumerated" items="${enumeratedData.enumerator}">
												<tr>
													<td style="width:60%;color:#4D7A93">${enumerated.name.value}</td>
													<td style="width:40%;text-align:right;color:#666666">
														<c:forEach var="enumVal" items="${enumerated.value}">
															${enumVal.value}<br/>
														</c:forEach>
													</td>
												</tr>
											</c:forEach>
										</table>
									</td>
									<td>${enumeratedData.semantics.value}</td>
								</tr>
							</c:forEach>
							</table>
						</div>

						<div class="basicCentralPanelBar">
							<img alt="" src="img/right.png"/>
							<div class="basicCentralPanelBarText" style="font-size:11px;color:#4D7A93">Array Data Types</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th>Name</th>
									<th>Data Type</th>
									<th>Cardinality</th>
									<th style="width:40%">Encoding</th>
									<th style="width:40%">Semantics</th>
								</tr>
							<c:forEach var="arrayData" items="${omt.dataTypes.arrayDataTypes.arrayData}">
								<tr>
									<td>${arrayData.name.value}</td>
									<td>${arrayData.dataType.value}</td>
									<td>${arrayData.cardinality.value}</td>
									<td>${arrayData.encoding.value}</td>
									<td>${arrayData.semantics.value}</td>
								</tr>
							</c:forEach>
							</table>
						</div>

						<div class="basicCentralPanelBar">
							<img alt="" src="img/right.png"/>
							<div class="basicCentralPanelBarText" style="font-size:11px;color:#4D7A93">Fixed Record Data Types</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th>Name</th>
									<th>Encoding</th>
									<th>Fields</th>
									<th style="width:40%">Semantics</th>
								</tr>
							<c:forEach var="fixedData" items="${omt.dataTypes.fixedRecordDataTypes.fixedRecordData}">
								<tr>
									<td>${fixedData.name.value}</td>
									<td>${fixedData.encoding.value}</td>
									<td>
										<table>
										<c:forEach var="field" items="${fixedData.field}">
											<tr>
												<td style="width:50%;color:#4D7A93">${field.name.value}</td>
												<td style="color:#666666">${field.dataType.value}</td>
											</tr>
										</c:forEach>
										</table>
									</td>
									<td>${fixedData.semantics.value}</td>
								</tr>
							</c:forEach>
							</table>
						</div>



						<div class="basicCentralPanelBar">
							<img alt="" src="img/right.png"/>
							<div class="basicCentralPanelBarText" style="font-size:11px;color:#4D7A93">Variant Record Data Types</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th>Name</th>
									<th>Discriminant</th>
									<th>Data Type</th>
									<th>Encoding</th>
									<th>Alternative</th>
									<th style="width:40%">Semantics</th>
								</tr>
							<c:forEach var="variantData" items="${omt.dataTypes.variantRecordDataTypes.variantRecordData}">
								<tr>
									<td>${variantData.name.value}</td>
									<td>${variantData.discriminant.value}</td>
									<td>${variantData.dataType.value}</td>
									<td>${variantData.encoding.value}</td>
									<td>
										<table>
										<c:forEach var="alternative" items="${variantData.alternative}">
											<tr>
												<td style="color:#4D7A93">${alternative.name.value}</td>
												<td style="color:#4D7A93">${alternative.enumerator.value}</td>
												<td style="color:#666666">${alternative.dataType.value}</td>
											</tr>
										</c:forEach>
										</table>
									</td>
									<td>${variantData.semantics.value}</td>
								</tr>
							</c:forEach>
							</table>
						</div>


						<div class="basicCentralPanelBar">
							<img alt="" src="img/transportation.png"/>
							<div class="basicCentralPanelBarText">Transportation Types</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th>Name</th>
									<th>Reliable</th>
									<th style="width:40%">Semantics</th>
								</tr>
							<c:forEach var="transp" items="${omt.transportations.transportation}">
								<tr>
									<td>${transp.name.value}</td>
									<td>${transp.reliable.value}</td>
									<td>${transp.semantics.value}</td>
								</tr>
							</c:forEach>
							</table>
						</div>

						<div class="basicCentralPanelBar">
							<img alt="" src="img/sync.png"/>
							<div class="basicCentralPanelBarText">Synchronization Points</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<table style="margin-bottom:4px;margin-top:4px;">
								<tr>
									<th>Label</th>
									<th>Data Type</th>
									<th>Capability</th>
									<th style="width:40%">Semantics</th>
								</tr>
							<c:forEach var="sync" items="${omt.synchronizations.synchronizationPoint}">
								<tr>
									<td>${sync.label.value}</td>
									<td>${sync.dataType.value}</td>
									<td>${sync.capability.value}</td>
									<td>${sync.semantics.value}</td>
								</tr>
							</c:forEach>
							</table>
						</div>



						<div class="basicCentralPanelBar">
							<img alt="" src="img/switch.png"/>
							<div class="basicCentralPanelBarText">Switches</div>
						</div>
						<div style="margin : 10px auto; width : 95%;display:table" >
							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.autoProvide.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Auto Provide</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.autoProvide.isEnabled) }
								</div>
							</div>
							
							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.conveyRegionDesignatorSets.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Convey Region Designator Sets</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.conveyRegionDesignatorSets.isEnabled) }
								</div>
							</div>
							
							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.conveyProducingFederate.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Convey Producing Federate</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.conveyProducingFederate.isEnabled) }
								</div>
							</div>

							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.attributeScopeAdvisory.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Attribute Scope Advisory</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.attributeScopeAdvisory.isEnabled) }
								</div>
							</div>

							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.attributeRelevanceAdvisory.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Attribute Relevance Advisory</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.attributeRelevanceAdvisory.isEnabled) }
								</div>
							</div>

							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.objectClassRelevanceAdvisory.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Object Class Relevance Advisory</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.objectClassRelevanceAdvisory.isEnabled) }
								</div>
							</div>

							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.interactionRelevanceAdvisory.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Interaction Relevance Advisory</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.interactionRelevanceAdvisory.isEnabled) }
								</div>
							</div>

							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.serviceReporting.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Service Reporting</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.serviceReporting.isEnabled) }
								</div>
							</div>

							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.exceptionReporting.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Exception Reporting</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.exceptionReporting.isEnabled) }
								</div>
							</div>

							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard ${omt.switches.delaySubscriptionEvaluation.isEnabled}" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Delay Subscription Evaluation</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.delaySubscriptionEvaluation.isEnabled) }
								</div>
							</div>

							<div style="padding-left:4px;padding-right:4px;margin-bottom: 5px;width:120px;height:20px" class="clusterBoard true" >
								<div style="font-weight:bold;margin-left:0px;width:95%;text-align:center">Automatic Resign Action</div>
								<div style="text-align:center;margin:0px;width:100%;">
									${ fn:toUpperCase(omt.switches.automaticResignAction.resignAction) }
								</div>
							</div>

							
						</div>

					</div>												
					
				</div>
				
				<div id="rightBox"> 
					<%@ include file="commonpanel.jsp" %>
				</div>
								
<script type="text/javascript">

	$(document).ready(function() {
		
		var maxHeight = 0;
		$( ".objectBox" ).each(function( index ) {
			var height = $( this ).height();
			if ( height > maxHeight ) {
				maxHeight = height;
			}
		});
		$( ".objectBox" ).height( maxHeight+4 );
		
		var maxHeight = 0;
		$( ".interactionBox" ).each(function( index ) {
			var height = $( this ).height();
			if ( height > maxHeight ) {
				maxHeight = height;
			}
		});
		$( ".interactionBox" ).height( maxHeight+3 );		
		
		
		$("#leftBox").css({"width":"100%"});
		$("#topBar").remove();
		$("#bottomBar").remove();
		$("#msgBar").remove();
		$("#rightBox").remove();
		
	});

	
</script>				
				
<%@ include file="../../footer.jsp" %>