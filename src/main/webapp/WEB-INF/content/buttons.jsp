<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<img onclick="askLogout()" title="Logout" class="menuButton dicas" src="img/shutdown.png">
<c:if test="${loggedUser.type == 'ADMIN'}">
	<a href="viewText?fileName=logs/sagitarii.log"><img title="View Server Log" class="menuButton dicas" src="img/log.png"></a>
	<a href="showSystemLog?type=NODE_STATUS"><img title="View Node Status Log" class="menuButton dicas" src="img/log.png"></a>
	<a href="viewRunning"><img title="Running Experiments" class="menuButton dicas" src="img/running.png"></a>
	<a href="viewDeliveryControl"><img title="View Instance Delivery Control" class="menuButton dicas" src="img/fragment.png"></a>
	<a href="viewExecutors"><img title="View Executors" class="menuButton dicas" src="img/gears.png"></a>
	<a href="viewExperiments"><img title="Experiments" class="menuButton dicas" src="img/experiment.png"></a>
	<a href="tablesmanager"><img title="Custom Tables" class="menuButton dicas" src="img/tables.png"></a>
	<a href="viewClusters"><img title="View Running Nodes" class="menuButton dicas" src="img/node.png"></a>
	<a href="viewUsers"><img title="Manage Users" class="menuButton dicas" src="img/users.png"></a>
	<a href="viewConnections"><img title="View Active Database Connections" class="menuButton dicas" src="img/connection.png"></a>
	<a href="viewMetrics?type=ENTITY"><img title="View Statistics" class="menuButton dicas" src="img/statistics.png"></a>
	<a href="viewFileTransfers"><img title="View Open File Transefer Sessions" class="menuButton dicas" src="img/filetransfer.png"></a>
	<a href="runUserSql"><img title="Open Interactive SQL Terminal" class="menuButton dicas" src="img/sql.png"></a>
	<a href="indexRedir"><img title="Home" class="menuButton dicas" src="img/home.png"></a>
</c:if>

<c:if test="${loggedUser.type == 'COMMON'}">
	<a href="viewExperiments"><img title="Experiments" class="menuButton dicas" src="img/experiment.png"></a>
	<a href="indexRedir"><img title="Home" class="menuButton dicas" src="img/home.png"></a>
</c:if>

<script>

	function askLogout() {
		showDialogBox( "Logout! Are you sure?", "doLogout" );
	}

</script>