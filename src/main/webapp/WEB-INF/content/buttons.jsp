<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<img onclick="askLogout()" title="Logout" class="menuButton dicas" src="img/shutdown.png">
<c:if test="${loggedUser.type == 'ADMIN'}">
	<a href="indexRedir"><img title="Home" class="menuButton dicas" src="img/home.png"></a>
</c:if>

<c:if test="${loggedUser.type == 'COMMON'}">
	<a href="mainPage"><img title="Home" class="menuButton dicas" src="img/home.png"></a>
</c:if>

<script>

	function askLogout() {
		showDialogBox( "Logout! Are you sure?", "doLogout" );
	}

</script>