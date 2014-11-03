'use strict';
var vbApp = angular.module('vbRank', ['ngRoute', 'vbRankControllers', 'vbRankServices']);

vbApp.directive('vbrStandings', function(){
    return {
        restrict: 'E',
        controller: 'StandingsCtrl',
        controllerAs: 'standings',
        templateUrl: 'views/standings.html'
    };
});

vbApp.directive('vbrProfile', function(){
    return {
        restrict: 'E',
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl',
        controllerAs: 'profile',
        link: function(scope, elem, attrs) {
            scope.profile.getPlayer(attrs.playerId);
        }
    }
});

vbApp.directive('vbrNewGame', function(){
    return { restrict: 'E',
        scope: {},
        controller: 'NewGameCtrl',
        controllerAs: 'newGame',
        templateUrl: 'views/newGame.html',
    }
});

vbApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/standings', { template: '<vbr-standings></vbr-standings>' }).
    when('/profile', { template: '<vbr-profile></vbr-profile>' }).
    when('/players/:playerId', { template: function($routeParams){
        return '<vbr-profile ng-attr-player-id="' + $routeParams.playerId + '"></vbr-profile>'}}).
    otherwise( { redirectTo: '/standings' });
}]);
