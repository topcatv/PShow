'use strict';

angular.module('pshowApp')
  .controller('ContentCtrl', ['$scope', '$routeParams', 'content', 'common', '$window', function ($scope, $routeParams, content, common, $window) {
	  $scope.contents = [];
	  $scope.content = {parentId : $routeParams.parentId};
	  
	  $scope.init = function(){
	  	  loading();
		  content.getChild('root', function(data){
			  $scope.contents = data;
			  if(!$window.breadcrumb){
			  	$window.breadcrumb = new Array();
			  }
			  $window.breadcrumb.push({id:'root', name:'根目录'});
			  $scope.content['parentId'] = 'root';
			  loading_over();
		  });
	  };

	  $scope.cd = function(content_id, content_name){
	  	loading();
	  	content.getChild(content_id, function(data){
		  $scope.contents = data;
		  var index = -1;
		  for(var i=0; i<$window.breadcrumb.length; i++){
		    if($window.breadcrumb[i].id == content_id) {
		    	index = i;
		    	break;
		    }
		  }
		  if(index != -1){
		  	$window.breadcrumb = $window.breadcrumb.slice(0, index+1);
		  }else{
		  	$window.breadcrumb.push({id: content_id, name: content_name});
		  }
		  loading_over();
		});
	  };

	  $scope.breadcrumb = function(){
	  	return $window.breadcrumb;
	  }

	  $scope.createFolder = function(){
	  	loading();
	  	content.createFolder($scope.content, function(data){
	  		console.log(data);
	  		if(data.id){
	  			noty({
	  				text: "文件夹建成功！",
	  				type: 'success',
	  				closeButton:"true"
	  			});
	  		}
	  		loading_over();
	  	});
	  }

	  $scope.openCreateFolder = function(){
	  	common.goto('content/create/folder/' + $scope.content.parentId);
	  }
  }]);
