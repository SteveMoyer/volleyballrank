'use strict';
goog.provide("net.stevemoyer.vbrank.app");
goog.require("net.stevemoyer.vbrank.controllers");
goog.require("vbrank.profile");
goog.require("vbrank.standings");
goog.require("vbrank.games");
var vbApp = angular.module('net.stevemoyer.vbrank.app', ['ngRoute', 
        net.stevemoyer.vbrank.controllers.name, vbrank.profile.name, vbrank.standings.name,
        vbrank.games.name]);
net.stevemoyer.vbrank.app=vbApp;
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
