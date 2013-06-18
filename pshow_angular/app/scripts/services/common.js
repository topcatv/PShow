'use strict';

angular.module('pshowApp')
  .factory('common', ['$location', '$window', function($location, $window) {

    // Public API here
    return {
      goto: function(path) {
        $window.oldPath = $location.path();
        $location.path(path);
      },
      back: function() {
        $location.path($window.oldPath);
      },
      addBreadcrumb: function(id, name){
        if(!$window.breadcrumb){
          $window.breadcrumb = new Array();
        }
        var index = -1;
        for(var i = 0; i < $window.breadcrumb.length; i++){
          if($window.breadcrumb[i].id == id) {
            index = i;
            break;
          }
        }
        if(index != -1){
          $window.breadcrumb = $window.breadcrumb.slice(0, index+1);
        }else{
          $window.breadcrumb.push({id: id, name: name});
        }
      },
      breadcrumb: function(){
        return $window.breadcrumb;
      }
    };
  }]);
