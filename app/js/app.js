'use strict';
var vbApp = angular.module('vbRank',['ngRoute','vbRankControllers','vbRankServices']);
vbApp.directive('standings',function(){
   return {
    restrict: 'E',
    templateUrl: '/app/partials/standings.html'
};});
vbApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/standings',
        {
            controller: 'StandingsController',
            templateUrl: '/app/views/standings.html'
        }).
    when('/profile',
        {
            controller: 'ProfileController',
            templateUrl: '/app/views/profile.html'
        }).
    otherwise(
        { redirectTo: '/profile'
        });
}]);
