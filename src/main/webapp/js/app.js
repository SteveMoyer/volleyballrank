'use strict';
var vbApp = angular.module('vbRank',['ngRoute','vbRankControllers','vbRankServices']);
vbApp.directive('standings',function(){
   return {
    restrict: 'E',
    templateUrl: 'views/standings.html'
};});
vbApp.directive('profile',function(){
   return {
    restrict: 'E',
    templateUrl: 'views/profile.html'
};});
vbApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/standings', { template: '<standings></standings>' }).
    when('/profile', { template: '<profile></profile>' }).
    otherwise( { redirectTo: '/profile' });
}]);
