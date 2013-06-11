'use strict';

angular.module('pshowApp')
  .factory('content', ['$http', function ($http) {

    // Public API here
    return {
      getChild: function (id, callback) {
    	  $http.get('proxy/content/child/' + id).success(function(data){callback(data);});
      }
    };
  }]);
