'use strict';

describe('Service: pageload', function () {

  // load the service's module
  beforeEach(module('pshowApp'));

  // instantiate service
  var pageload;
  beforeEach(inject(function(_pageload_) {
    pageload = _pageload_;
  }));

  it('should do something', function () {
    expect(!!pageload).toBe(true);
  });

});
