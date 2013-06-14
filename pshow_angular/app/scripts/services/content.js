'use strict';

angular.module('pshowApp')
  .factory('content', ['$http', function ($http) {

    // Public API here
    return {
      getChild: function (id, callback) {
    	  $http.get('proxy/content/child/' + id).success(function(data){callback(data);});
      },
      createFolder: function(content, callback){
        content['type'] = "sys:folder";
      	$http.post('proxy/content', $.param(content), {
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
      	.success(function(data){callback(data);});
      }
    };
  }]);
