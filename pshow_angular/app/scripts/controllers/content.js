'use strict';

angular.module('pshowApp')
  .controller('ContentCtrl', ['$scope', '$routeParams', 'content', 'common', function ($scope, $routeParams, content, common) {
	  $scope.contents = [];
	  $scope.contenttypes;
	  $scope.content = {parentId : $routeParams.parentId, name: $routeParams.cname};
	  $scope.contenttype;
	  $scope.properties;
	  
	  $scope.init = function(){
	  	  loading();
		  content.getChild($scope.content.parentId, function(data){
			  $scope.contents = data;
			  common.addBreadcrumb($scope.content.parentId, $scope.content.name);
			  loading_over();
		  });
	  };

	  $scope.getAllContentType = function(){
		  content.getAllContentType(function(data){
			  $scope.contenttypes = data;
		  });
	  };

	  $scope.getPropertiesForCT = function(){
		  content.getProperties($scope.contenttype.name, function(data){
			  $scope.properties = data;
		  });
	  };

	  $scope.cd = function(content_id, content_name){
	  	common.goto("content/"+content_id+"/"+content_name);
	  };

	  $scope.breadcrumb = function(){
	  	return common.breadcrumb();
	  };

	  $scope.createFolder = function(){
	  	loading();
	  	content.createFolder($scope.content, function(data){
	  		if(data.id){
	  			noty({
	  				text: "文件夹建成功！",
	  				type: 'success',
	  				closeButton:"true"
	  			});
	  		}
	  		loading_over();
	  	});
	  };

	  $scope.contentChange = function(){
	  	console.log('a');
	  }

	  $scope.createContent = function(){
	  	console.log('create content');
	  	content.createContent($scope.contenttype, $scope.content, function(data){
	  		console.log(data);
	  	});
	  };

	  $scope.openCreateFolder = function(){
	  	common.goto('content/create/folder/' + $scope.content.parentId);
	  };

	  $scope.openCreateContent = function(){
	  	common.goto('content/create/content/' + $scope.content.parentId);
	  };
  }]);
