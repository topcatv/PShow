'use strict';

angular.module('pshowApp', [])
  .config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/index.html',
        controller: 'IndexCtrl'
      })
      .when('/content', {
        templateUrl: 'views/content.html',
        controller: 'ContentCtrl'
      })
      .when('/content/create/folder/:parentId', {
        templateUrl: 'views/newFolder.html',
        controller: 'ContentCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);
