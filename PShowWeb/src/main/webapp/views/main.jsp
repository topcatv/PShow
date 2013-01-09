<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home</title>
<jsp:include page="/views/script.jsp" />
<script type="text/javascript">

	require([ "dojo/parser", "dijit/layout/ContentPane", "dijit/Toolbar", "dijit/ToolbarSeparator",
			"dijit/layout/BorderContainer", "dijit/layout/AccordionContainer",
			"dijit/layout/AccordionPane","dojo/store/Memory", "dijit/tree/ObjectStoreModel", "dijit/Tree", "dijit/form/Button" ]);
</script>

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
					<div data-dojo-type="dojo/store/Memory" data-dojo-id="myStore">
						<!-- Create store with inlined data.
				        For larger data sets should use dojo.store.JsonRest etc. instead of dojo.store.Memory. -->
						<script type="dojo/method">
         this.setData([
            { id: 'world', name:'The earth', type:'planet', population: '6 billion'},
            { id: 'AF', name:'Africa', type:'continent', population:'900 million', area: '30,221,532 sq km',
                    timezone: '-1 UTC to +4 UTC', parent: 'world'},
                { id: 'EG', name:'Egypt', type:'country', parent: 'AF' },
                { id: 'KE', name:'Kenya', type:'country', parent: 'AF' },
                    { id: 'Nairobi', name:'Nairobi', type:'city', parent: 'KE' },
                    { id: 'Mombasa', name:'Mombasa', type:'city', parent: 'KE' },
                { id: 'SD', name:'Sudan', type:'country', parent: 'AF' },
                    { id: 'Khartoum', name:'Khartoum', type:'city', parent: 'SD' },
            { id: 'AS', name:'Asia', type:'continent', parent: 'world' },
                { id: 'CN', name:'China', type:'country', parent: 'AS' },
                { id: 'IN', name:'India', type:'country', parent: 'AS' },
                { id: 'RU', name:'Russia', type:'country', parent: 'AS' },
                { id: 'MN', name:'Mongolia', type:'country', parent: 'AS' },
            { id: 'OC', name:'Oceania', type:'continent', population:'21 million', parent: 'world'},
            { id: 'EU', name:'Europe', type:'continent', parent: 'world' },
                { id: 'DE', name:'Germany', type:'country', parent: 'EU' },
                { id: 'FR', name:'France', type:'country', parent: 'EU' },
                { id: 'ES', name:'Spain', type:'country', parent: 'EU' },
                { id: 'IT', name:'Italy', type:'country', parent: 'EU' },
            { id: 'NA', name:'North America', type:'continent', parent: 'world' },
            { id: 'SA', name:'South America', type:'continent', parent: 'world' }
        ]);
    					</script>
						<script type="dojo/method" data-dojo-event="getChildren"
							data-dojo-args="object">
         					// Supply a getChildren() method to store for the data model where
         					// children objects point to their parent (aka relational model)
         					return this.query({parent: object.id});
    					</script>
					</div>

					<!-- Create the model bridging the store and the Tree -->
					<div data-dojo-type="dijit/tree/ObjectStoreModel" data-dojo-id="myModel" data-dojo-props="store: myStore, query: {id: 'world'}"></div>

					<!-- Create the tree -->
					<div data-dojo-type="dijit/Tree" id="myTree" data-dojo-props="model: myModel"></div>
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