'use strict';
goog.provide("vbrank.app");
goog.require("vbrank.controllers");
goog.require("vbrank.profile");
goog.require("vbrank.standings");
goog.require("vbrank.games");
goog.require("vbrank.players");
goog.require("vbrank.user.ctrl");
goog.require("vbrank.user.service");

var vbApp = angular.module('vbrank.app',
        ['ngRoute',
        vbrank.players.name,
        vbrank.profile.name,
        vbrank.standings.name,
        vbrank.games.name,
        vbrank.controllers.name,
        vbrank.user.ctrl.name]);

vbrank.app = vbApp;

vbApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/games/new', { template: '<vbr-new-game></vbr-new-game>' }).
    when('/standings', { template: '<vbr-standings></vbr-standings>' }).
    when('/profile/me', { template: '<vbr-edit-profile></vbr-edit-profile>' }).
    when('/players/:playerId', { template: function($routeParams){
        return '<vbr-profile ng-attr-player-id="' + $routeParams.playerId + '"></vbr-profile>'}}).
    otherwise( { redirectTo: '/standings' });
}]);
