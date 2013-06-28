'use strict';

angular.module('pshowApp')
  .factory('content', ['$http', function ($http) {

    // Public API here
    return {
      getChild: function (id, callback) {
    	  $http.get('/proxy/content/child/' + id).success(function(data){callback(data);});
      },
      getAllContentType: function (callback) {
        $http.get('/proxy/content/type').success(function(data){callback(data);});
      },
      getProperties: function (content_name, callback) {
        $http.get('/proxy/content/type/properties/' + content_name).success(function(data){callback(data);});
      },
      createFolder: function(content, callback){
        content['type'] = "sys:folder";
      	$http.post('/proxy/content', $.param(content), {
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
      	.success(function(data){callback(data);});
      },
      createContent: function(contentType, content, callback){
        content['type'] = contentType.name;
        $http.post('/proxy/content', $.param(content), {
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .success(function(data){callback(data);});
      },
      getContent: function(contentId, callback) {
        $http.get('/proxy/content/' + contentId).success(function(data){callback(data);});
      },
      rename: function(contentId, name, callback) {
        $http.put('/proxy/content/' + contentId, $.param({'name':name}), {
          headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .success(function(data){callback(data);});
      }
    };
  }]);
