'use strict';

describe('Directive: dyform', function () {
  beforeEach(module('pshowApp'));

  var element;

  it('should make hidden element visible', inject(function ($rootScope, $compile) {
    element = angular.element('<dyform></dyform>');
    element = $compile(element)($rootScope);
    expect(element.text()).toBe('this is the dyform directive');
  }));
});
