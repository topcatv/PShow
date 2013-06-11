'use strict';

describe('Controller: ContentCtrl', function () {

  // load the controller's module
  beforeEach(module('pshowApp'));

  var ContentCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ContentCtrl = $controller('ContentCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
