'use strict';
goog.provide("net.stevemoyer.vbrank.app");
goog.require("net.stevemoyer.vbrank.controllers");
var vbApp = angular.module('net.stevemoyer.vbrank.app', ['ngRoute', 
        net.stevemoyer.vbrank.controllers.name]);
net.stevemoyer.vbrank.app=vbApp;

vbApp.directive('vbrStandings', function(){
    return {
        restrict: 'E',
        controller: 'StandingsCtrl',
        controllerAs: 'standings',
        templateUrl: 'views/standings.html',
        link: function(scope, elem, attrs) {
            scope.standings.getPlayers();
        }
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
            scope.profile.getGames(attrs.playerId);
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
vbApp.directive('vbrEditProfile', function(){
    return { restrict: 'E',
        scope: {},
        controller: 'EditProfileCtrl',
        controllerAs: 'editProfile',
        templateUrl: 'views/editProfile.html',
        link: function(scope, elem, attrs) {
            scope.editProfile.getMe();
        }

    }
});


vbApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/games/new', { template: '<vbr-new-game></vbr-new-game>' }).
    when('/standings', { template: '<vbr-standings></vbr-standings>' }).
    when('/profile/me', { template: '<vbr-edit-profile></vbr-edit-profile>' }).
    when('/profile', { template: '<vbr-profile></vbr-profile>' }).
    when('/players/:playerId', { template: function($routeParams){
        return '<vbr-profile ng-attr-player-id="' + $routeParams.playerId + '"></vbr-profile>'}}).
    otherwise( { redirectTo: '/standings' });
}]);
