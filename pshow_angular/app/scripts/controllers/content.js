'use strict';

angular.module('pshowApp')
  .controller('ContentCtrl', ['$scope', '$routeParams', 'content', 'common', function ($scope, $routeParams, content, common) {
	  $scope.contents = [];
	  $scope.content = {parentId : $routeParams.parentId};
	  $scope.breadcrumb = {};
	  $scope.init = function(){
	  	  loading();
		  content.getChild('root', function(data){
			  $scope.contents = data;
			  $scope.breadcrumb['root'] = '根目录';
			  $scope.content['parentId'] = 'root';
			  loading_over();
		  });
	  };

	  $scope.cd = function(content_id){
	  	loading();
	  	content.getChild(content_id, function(data){
		  $scope.contents = data;
		  $scope.content['parentId'] = content_id;
		  loading_over();
		});
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
	  }

	  $scope.openCreateFolder = function(){
	  	common.goto('content/create/folder/' + $scope.content.parentId);
	  }
  }]);
