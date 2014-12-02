'use strict';
goog.provide('vbrank.standings');
goog.require('net.stevemoyer.vbrank.services');
var vbRankStandings = angular.module('vbrank.standings',
        [net.stevemoyer.vbrank.services.name]);
vbrank.standings = vbRankStandings;

vbRankStandings.controller('StandingsCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            var that = this;
            this.getPlayers = function(){ PlayerService.getPlayers().then(function(players){
                that.players = players;
            });};
            this.insertPlayer = function() {
                var newPlayer = {};
                newPlayer.emailAddress = this.emailAddress;
                newPlayer.name = this.name;
                PlayerService.insertPlayer(newPlayer).then(function() {
                    that.emailAddress = null;
                    that.name = null;
                    that.getPlayers();
                });
            }
        }]);
vbRankStandings.directive('vbrStandings', function(){
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



