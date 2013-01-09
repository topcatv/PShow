<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="generator" content="HTML Tidy, see www.w3.org" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<jsp:include page="/views/script.jsp" />

<script type="text/javascript">
	
	require([ "dojo/parser", "dijit/Tooltip", "dijit/form/Form",
			"dijit/form/TextBox", "dijit/form/ValidationTextBox",
			"dijit/form/Button" ]);
	require(
			[ "dojo/dom", "dojo/on", "dojo/ready", "dojo/domReady!" ],
			function(dom, on, ready) {
				var login = function() {
					var form = dom.byId("registration_form");
					form.submit();
				};
				ready(function() {
					on(dom.byId("btn_submit"), "click", login);
					on(dom.byId("registration_form"), "keypress",
							function(evt) {
								var charOrCode = evt.charCode || evt.keyCode
								switch (charOrCode) {
								case dojo.keys.ENTER:
									dojo.byId("registration_form").submit();
									break;
								}
							});
					<%String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
						if (error != null) {%>
							var node = dom.byId("registration_form");
							dijit.Tooltip.show("<%if (error.contains("DisabledAccountException")) {
							out.print("用户已被屏蔽,请登录其他用户.");
						} else {
							out.print("登录失败，请重试.");
						}%>", node, [ "below" ]);
					<%}%>
				});
			});
</script>

<style type="text/css">
h3 {
	margin: 10px;
}

label,input {
	display: block;
	float: left;
	margin-bottom: 5px;
}

label {
	text-align: left;
	width: 100px;
}

br {
	clear: left;
}

.grouping {
	width: 300px;
	border: solid 1px rgb(230, 230, 230);
	padding: 5px;
	margin: 10px;
}

div#container {
	margin-left: auto;
	margin-right: auto;
	width: 228px;
	text-align: left;
	padding-top: 200px;
}

body {
	text-align: center;
}
</style>
</head>
<body class="claro">
<div id="container">
	<h3>Sign-Up for out greater offers:</h3>

	<form data-dojo-type="dijit/form/Form" id="registration_form" action="login" method="post">
		<div class="grouping">
			<label for="userName">Login Name:</label>
			<input id="username" type="text" maxlength="25" name="username" data-dojo-type="dijit/form/ValidationTextBox" data-dojo-props="trim:'true', required:'true'" /><br />
			<label for="pwd">Password:</label>
			<input id="pwd" type="password"	maxlength="25" name="password" data-dojo-type="dijit/form/ValidationTextBox" data-dojo-props="trim:'true', required:'true'" /><br />
			<button id="btn_submit" data-dojo-type="dijit/form/Button">Sign	Up!</button>
		</div>
	</form>
</div>
</body>
</html>

