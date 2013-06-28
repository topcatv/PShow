'use strict';

angular.module('pshowApp', [])
  .config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $routeProvider
      .when('/index', {
        templateUrl: '/views/index.html',
        controller: 'IndexCtrl'
      })
      .when('/content/:parentId/:cname', {
        templateUrl: '/views/content.html',
        controller: 'ContentCtrl'
      })
      .when('/content/:contentId', {
        templateUrl: '/views/contentDetail.html',
        controller: 'ContentCtrl'
      })
      .when('/content/create/folder/:parentId', {
        templateUrl: '/views/newFolder.html',
        controller: 'ContentCtrl'
      })
      .when('/content/create/content/:parentId', {
        templateUrl: '/views/newContent.html',
        controller: 'ContentCtrl'
      });
      $locationProvider.html5Mode(true);
  }]);
