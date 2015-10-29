<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../header.jsp" %>

				<div id="leftBoxAlter" style="width:100%; border-right:0px;position:relative" > 
				
					<div class="userBoard" style="margin:0 auto;margin-top:5px">
						<div class="userBoardT1" style="text-align:center;width:95%">Login</div>
						<div class="userBoardT2" style="text-align:center;width:95%">
							<form action="doLogin" method="post" name="formLogin" id="formLogin">
								<table>
									<tr>
										<td style="width:50%">Username</td><td><input autocomplete="off" id="username" type="text" name="username"></td>
									</tr>
									<tr>
										<td style="width:50%">Password</td><td><input autocomplete="off" id="password" type="password" name="password"></td>
									</tr>
									<tr>
										<td style="width:50%">&nbsp;</td><td><div style="margin-right: 7px;margin-top: 0px;" class="basicButton" onclick="doLogin()">Login</div></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					
					<div style="margin:0 auto;margin-top:15px;width: 240px;">
						<div class="userBoardT1" style="text-align:center;width:95%">
							<a href="requestAccess">Request Access</a>
						</div>
					</div>					
				</div>
								
				<br><br>
				
<script>

	function doLogin() {
		var password = $("#password").val();
		var username = $("#username").val();
		if ( (password == '') || ( username == '' ) ) {
			showMessageBox('Please fill all required fields.');
			return;
		} 
		$("#formLogin").submit();
	}

	$(document).ready(function() {
		$("#username").focus();
		
		$("#password").keypress(function(event) {
		    if (event.which == 13) {
		        event.preventDefault();
		        doLogin();
		    }
		});

	});
	
</script>				
				
				<script>
					showMessageBox( '${messageText}' );
				</script>				

				<div class="clear" />
			</div>
			
		</div>

		<div class="clear" />
		<div id="bottomBar" style="height:90px">
			
			<div class="footerDivCenter" style="position: relative;margin-top:5px;">
				<a class="dicas" title="Download Code" target="_BLANK" href="https://github.com/icemagno/webomt"><img src="img/logos/git_badge.png" class="badge"></a>
				<a class="dicas" title="Code Mirror" target="_BLANK" href="http://codemirror.net/"><img src="img/logos/codemirror_badge.png" class="badge"/></a>
				<a class="dicas" title="jQuery" target="_BLANK" href="https://jquery.com/"><img src="img/logos/jquery.png" class="badge"/></a>
				<a class="dicas" title="Hibernate" target="_BLANK" href="http://hibernate.org/"><img src="img/logos/hibernate_badge.png" class="badge"/></a>
				<a class="dicas" title="Apache Log4j" target="_BLANK" href="http://logging.apache.org/log4j/2.x/"><img src="img/logos/log4j_badge.png" class="badge"/></a>
				<a class="dicas" title="PostgreSQL" target="_BLANK" href="http://www.postgresql.org/"><img src="img/logos/postgres_badge.png" class="badge"/></a>
				<a class="dicas" title="Java" target="_BLANK" href="http://www.oracle.com/"><img src="img/logos/java_badge.png" class="badge"/></a>
				<a class="dicas" title="Eclipse" target="_BLANK" href="https://www.eclipse.org/luna/"><img src="img/logos/eclipse_badge.png" class="badge"/></a>
			</div> 
			
			
			<div class="footerDivCenter" style="font-style: italic;padding-top:5px">
				"Together we're stronger"<br>
				v1.0.345
			</div> 
			
		</div>
		
	</body>
	
</html>