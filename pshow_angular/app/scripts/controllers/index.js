'use strict';

angular.module('pshowApp')
  .controller('IndexCtrl', ['$scope', function ($scope) {
    $scope.loadviews = function(){
        console.log('add');
    };
  }]);
