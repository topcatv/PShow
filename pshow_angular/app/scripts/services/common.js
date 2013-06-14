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
      }
    };
  }]);
