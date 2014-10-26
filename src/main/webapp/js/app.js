'use strict';
var vbApp = angular.module('vbRank',['ngRoute','vbRankControllers','vbRankServices']);
vbApp.directive('standings',function(){
   return {
    restrict: 'E',
    templateUrl: 'views/standings.html'
};});
vbApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/standings',
        {
            controller: 'StandingsCtrl',
            templateUrl: 'views/standings.html'
        }).
    when('/profile',
        {
            controller: 'ProfileCtrl',
            templateUrl: 'views/profile.html'
        }).
    otherwise(
        { redirectTo: '/profile'
        });
}]);
