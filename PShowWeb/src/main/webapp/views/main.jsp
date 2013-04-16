<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home</title>
<jsp:include page="/views/script.jsp" />
<script type="text/javascript">

	require([ "dojo/parser", "dijit/layout/ContentPane", "dijit/Toolbar", "dijit/ToolbarSeparator",
			"dijit/layout/BorderContainer", "dijit/layout/AccordionContainer",
			"dijit/layout/AccordionPane", "dijit/form/Button" ]);
</script>
<script type="text/javascript" src="javascript/nav_tree.js"></script>
<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
	margin: 0;
	overflow: hidden;
}

#borderContainer {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<body class="claro">
		<div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="design:'sidebar', gutters:true, liveSplitters:true" id="borderContainer">
		<div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="splitter:true, region:'top', style:'height:60px'" >
		<div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="design:'sidebar', gutters:true, liveSplitters:true">
			<div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'leading', style:'width: 20%'">
				<shiro:user>
				Hello, <shiro:principal/>, how are you today?
				</shiro:user>
			</div>
				<div id="toolbar1" data-dojo-type="dijit/Toolbar" data-dojo-props="region:'center'">
					<div data-dojo-type="dijit/form/Button" id="toolbar1.cut" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconCut', showLabel:false">Cut</div>
					<div data-dojo-type="dijit/form/Button" id="toolbar1.copy" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconCopy', showLabel:false">Copy</div>
					<div data-dojo-type="dijit/form/Button" id="toolbar1.paste" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconPaste', showLabel:false">Paste</div>
					<!-- The following adds a line between toolbar sections -->
					<span data-dojo-type="dijit/ToolbarSeparator"></span>
				</div>
		</div>
		</div>
		<div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="splitter:true, region:'center'">
			<div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="design:'sidebar', gutters:true, liveSplitters:true">
			<div data-dojo-type="dijit/layout/AccordionContainer" data-dojo-props="region:'leading', splitter:true, style: 'width: 20%'">
				<div data-dojo-type="dijit/layout/ContentPane" title="导航树">
				<div id="nav_tree"></div>
				</div>
				<div data-dojo-type="dijit/layout/ContentPane" title="pane #2">accordion pane #2</div>
			</div>
			
			<div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="splitter:true, region:'center', style: 'width: 80%'">main panel</div>
			</div>
			</div>
			<div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="splitter:true, region:'bottom'">bottom bar</div>
		</div>
	</body>
</html>