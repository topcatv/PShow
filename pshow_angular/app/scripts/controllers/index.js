'use strict';

angular.module('pshowApp')
  .controller('IndexCtrl', ['$scope', 'common', function ($scope, common) {
    $scope.goto = function(path){
    	common.goto(path);
    };
  }]);
