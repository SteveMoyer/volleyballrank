'use strict';
var vbApp = angular.module('vbRank', ['ngRoute', 'vbRankControllers', 'vbRankServices']);

vbApp.directive('vbrStandings', function(){
    return {
        restrict: 'E',
    templateUrl: 'views/standings.html'
    };
});

vbApp.directive('vbrProfile', function(){
    return {
        restrict: 'E',
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl',
        link: function(scope, elem, attrs) {
            scope.$watch(attrs.playerId, function (value) {
                if (value) {
                    scope.loadPlayer(value);
                }
              })
        }
    }
});

vbApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/standings', { template: '<vbr-standings></vbr-standings>' }).
    when('/profile', { template: '<vbr-profile></vbr-profile>' }).
    when('/players/:playerId', { template: function($routeParams){ 
        return '<vbr-profile ng-attr-player-id="' + $routeParams.playerId + '"></vbr-profile>' }}).
    otherwise( { redirectTo: '/profile' });
}]);
