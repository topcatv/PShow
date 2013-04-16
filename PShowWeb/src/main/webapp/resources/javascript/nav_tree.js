require([
    "dojo/ready", "dojo/dom", "dojo/store/JsonRest",
    "dijit/tree/ObjectStoreModel", "dijit/Tree","dojo/store/Memory"
], function(ready, dom, JsonRest, ObjectStoreModel, Tree, Memory){

    var myStore = new JsonRest({
        target: 'content/child/',
        getChildren: function(object){
            return this.get(object.id);
        }
    });

    // Create the model
    var myModel = new ObjectStoreModel({
        store: myStore,
        root: {id: 'root',name: 'root'}
    });

    // Create the Tree.   Note that all widget creation should be inside a dojo.ready().
    ready(function(){
        var tree = new Tree({
            model: myModel,
            srcNodeRef:dom.byId('nav_tree')
        });
        tree.startup();
    });
});