'use strict';

describe('Service: content', function () {

  // load the service's module
  beforeEach(module('pshowApp'));

  // instantiate service
  var content;
  beforeEach(inject(function (_content_) {
    content = _content_;
  }));

  it('should do something', function () {
    expect(!!content).toBe(true);
  });

});
