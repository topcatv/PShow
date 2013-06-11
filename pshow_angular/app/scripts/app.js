'use strict';

angular.module('pshowApp', [])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/index.html',
        controller: 'IndexCtrl'
      })
      .when('/content', {
        templateUrl: 'views/content.html',
        controller: 'ContentCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);
