'use strict';

angular.module('pshowApp')
  .controller('ContentCtrl', ['$scope', 'content', function ($scope, content) {
	  $scope.contents = [];
	  $scope.breadcrumb = {};
	  $scope.init = function(){
	  	  loading();
		  content.getChild('root', function(data){
			  $scope.contents = data;
			  $scope.breadcrumb['root'] = '根目录';
			  loading_over();
		  });
	  };

	  $scope.cd = function(content_id){
	  	loading();
	  	var name = $scope.breadcrumb[content_id];
	  	content.getChild(content_id, function(data){
		  $scope.contents = data;
		  loading_over();
		});
	  };
  }]);
