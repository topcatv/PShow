'use strict';

angular.module('pshowApp')
  .controller('ContentCtrl', ['$scope', '$routeParams', 'content', 'common', function ($scope, $routeParams, content, common) {
	  $scope.contents = [];
	  $scope.contenttypes = [];
	  $scope.content = {parentId : $routeParams.parentId};
	  
	  $scope.init = function(){
	  	  loading();
		  content.getChild('root', function(data){
			  $scope.contents = data;
			  common.addBreadcrumb('root', '根目录');
			  $scope.content['parentId'] = 'root';
			  loading_over();
		  });
	  };

	  $scope.getAllContentType = function(){
		  content.getAllContentType(function(data){
			  $scope.contenttypes = data;
		  });
	  };

	  $scope.cd = function(content_id, content_name){
	  	loading();
	  	content.getChild(content_id, function(data){
		  $scope.contents = data;
		  common.addBreadcrumb(content_id, content_name);
		  loading_over();
		});
	  };

	  $scope.breadcrumb = function(){
	  	return common.breadcrumb();
	  };

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
	  };

	  $scope.openCreateFolder = function(){
	  	common.goto('content/create/folder/' + $scope.content.parentId);
	  };

	  $scope.openCreateContent = function(){
	  	common.goto('content/create/content/' + $scope.content.parentId);
	  };
  }]);
