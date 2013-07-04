'use strict';

angular.module('pshowApp')
  .controller('ContentCtrl', ['$scope', '$routeParams', 'content', 'common', function ($scope, $routeParams, content, common) {
	  $scope.contents = [];
	  $scope.contenttypes;
	  $scope.content = {parentId : $routeParams.parentId, name: $routeParams.cname};
	  $scope.contenttype;
	  $scope.properties;
	  $scope.contentid = $routeParams.contentId;
	  
	  $scope.init = function(){
	  	  loading();
		  content.getChild($scope.content.parentId, function(data){
			  $scope.contents = data;
			  common.addBreadcrumb($scope.content.parentId, $scope.content.name);
			  for (var i = $scope.contents.length - 1; i >= 0; i--) {
			  	var type = $scope.contents[i].type;
			  	if (type == 'http://www.pshow.org/model/system/0.1:folder') {
			  		$scope.contents[i].show_type = 'Folder';
			  	} else {
			  		$scope.contents[i].show_type = 'Docs';
			  	}
			  }
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

	  $scope.cdOrOpen = function(content_id, content_name, content_type){
	  	if("Folder" == content_type){
			$scope.cd(content_id, content_name);
		} else {
			$scope.openContent(content_id);
		}
	  };

	  $scope.edit = function(index){
	  	$scope.index = index;
	  	var c = $scope.contents[index];
	  	$scope.content.name = c.text;
	  	if("Folder" == c.show_type){
	  		$('#rename_dialog').modal();
	  	}else{
	  		common.goto("content/edit/" + c.id);
	  	}
	  };

	  $scope.del = function(index){
	  	$scope.index = index;
	  	var c = $scope.contents[index];
	  	content.del(c.id, function(data){
	  		if(data){
	  			noty({
	  				text: "删除成功！",
	  				type: 'success',
	  				closeButton:"true"
	  			});
	  			$scope.contents.splice(index,1);
	  		}
	  	});
	  };

	  $scope.rename = function(index){
	  	var c = $scope.contents[index];
	  	content.rename(c.id, $scope.content.name, function(data){
	  		if(data){
	  			noty({
	  				text: "修改成功！",
	  				type: 'success',
	  				closeButton:"true"
	  			});
	  			$scope.contents[index].text = $scope.content.name;
	  			$('#rename_dialog').modal('hide');
	  		}
	  	});
	  };

	  $scope.openContent = function(content_id){
	  	console.log(content_id);
	  	common.goto("content/"+content_id);
	  };

	  $scope.readContent = function(content_id){
	  	content.getContent(content_id, function(data){
	  		$scope.content = data;
	  	});
	  };

	  $scope.updateContent = function(){
	  	var c = {id : $scope.contentid, isFolder: false};
	  	$('#content_update :input:not(button)').each(function(index){
	  		c[$(this).attr('name')] = $(this).val();
	  	});
	  	content.update(c, function(data){
  			if(data){
	  			noty({
	  				text: "修改成功！",
	  				type: 'success',
	  				closeButton:"true"
	  			});
	  		}
  		});
	  }

	  $scope.breadcrumb = function(){
	  	return common.breadcrumb();
	  };

	  $scope.createFolder = function(){
	  	loading();
	  	content.createFolder($scope.content, function(data){
	  		if(data.id){
	  			noty({
	  				text: "文件夹创建成功！",
	  				type: 'success',
	  				closeButton:"true"
	  			});
	  		}
	  		loading_over();
	  	});
	  };

	  $scope.createContent = function(){
	  	loading();
	  	var options = {
			//target: '#result',
			url: '/proxy/content',
			type: 'post',
			data: {parentId:$scope.content.parentId,type:$scope.contenttype.name},
			success: function(data) {
				// var data = $.parseJSON($('#result').text());
				console.log(data);
				if(data.id){
		  			noty({
		  				text: "文档创建成功！",
						type: 'success',
						closeButton:"true"
					});
		  		}
		  		loading_over();
			}
		};
	  	$('#content_form').ajaxSubmit(options);
	  };

	  $scope.openCreateFolder = function(){
	  	common.goto('content/create/folder/' + $scope.content.parentId);
	  };

	  $scope.openCreateContent = function(){
	  	common.goto('content/create/content/' + $scope.content.parentId);
	  };
  }]);
