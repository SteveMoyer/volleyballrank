'use strict';
var vbRankControllers = angular.module('vbRankControllers', ['vbRankServices']);

vbRankControllers.controller('StandingsCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            var that = this;
            this.getPlayers = function(){ PlayerService.getPlayers().then(function(players){
                that.players = players;
            });
            };
            this.getPlayers();
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

vbRankControllers.controller('ProfileCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            var that = this;
            $scope.ctrl=that;
            this.getPlayer = function(playerId) {
                PlayerService.getPlayer(playerId).then(function(player){
                    that.player = player;
                });
            };
        }]);

vbRankControllers.controller('NewGameCtrl',
        ['$scope', 'GameService', 'PlayerService', function($scope, GameService, PlayerService) {
            var that =this;
            that.addGame = function() {
                GameService.addGame(that.game).then(function(game){
                });
            };
            that.addingGame = false;
            PlayerService.getPlayers().then(function(playerList){
                    that.players = playerList;
                    that.game={};
                    that.addingGame=false;
            });
        }]);
