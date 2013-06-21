'use strict';

angular.module('pshowApp')
  .factory('common', ['$location', '$rootScope', function ($location, $rootScope) {


    // Public API here
    return {
      goto: function(path) {
        $location.path('/' + path);
      },
      addBreadcrumb: function(id, name){
        if(!$rootScope.breadcrumb){
          $rootScope.breadcrumb = new Array();
        }
        var index = -1;
        for(var i = 0; i < $rootScope.breadcrumb.length; i++){
          if($rootScope.breadcrumb[i].id == id) {
            index = i;
            break;
          }
        }
        if(index != -1){
          $rootScope.breadcrumb = $rootScope.breadcrumb.slice(0, index+1);
        }else{
          $rootScope.breadcrumb.push({id: id, name: name});
        }
      },
      breadcrumb: function(){
        return $rootScope.breadcrumb;
      }
    };
  }]);
