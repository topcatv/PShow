'use strict';

angular.module('pshowApp')
  .directive('dyform', ['$http', '$compile', 'content', function ($http, $compile, content) {
    var textTemplate = '<div class="control-group">'+
    '<label class="control-label" for="input{{property.name}}">{{property.title}}</label>'+
    '<div class="controls"><input type="text" id="input{{property.name}}" ng-required="property.mandatory" model="{{model.name}}"></div></div>';

    var getTemplate = function(property) {
        var template = '';

        switch(property.propertyType) {
            case 'd:text':
                template = textTemplate.replace(/\{\{model\.name\}\}/, property.name);
                break;
        }

        return template;
    };

    var postLink = function(scope, element, attrs) {
      
        var widget = $(getTemplate(scope.property));
        widget.bind('change', function(event){
          var input = $(event.originalEvent.target);

          scope.$parent.content[input.attr('model')] = input.val();
        });
        element.append(widget);

        $compile(element.contents())(scope);
    };

    return {
      // template: '<div class="form-horizontal"></div>',
      restrict: 'E',
      replace: true,
      link: postLink,
      scope: {
        property: '='
      }
    };
  }]);
