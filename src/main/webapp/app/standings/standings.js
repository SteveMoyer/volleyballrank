'use strict';
goog.provide('vbrank.standings');
goog.require('vbrank.players');
var vbRankStandings = angular.module('vbrank.standings',
        [vbrank.players.name]);
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
        templateUrl: 'app/standings/standings.html',
        link: function(scope, elem, attrs) {
            scope.standings.getPlayers();
        }
    };
});



