'use strict';
var vbRankControllers = angular.module('vbRankControllers', ['vbRankServices']);

vbRankControllers.controller('StandingsCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            $scope.getPlayers = function(){ PlayerService.getPlayers().then(function(players){
                $scope.players = players;
            });
            };
            $scope.getPlayers();
            $scope.insertPlayer = function() {
                var newPlayer = {};
                newPlayer.emailAddress = $scope.emailAddress;
                newPlayer.name = $scope.name;
                PlayerService.insertPlayer(newPlayer).then(function() {
                    $scope.emailAddress = null;
                    $scope.name = null;
                    $scope.getPlayers();
                });
            }
        }]);

vbRankControllers.controller('ProfileCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            $scope.getPlayer = function(playerId) {
                PlayerService.getPlayer(playerId).then(function(player){
                    $scope.player = player;
                });
            };
        }]);

vbRankControllers.controller('NewGameCtrl',
        ['$scope', 'GameService', 'PlayerService', function($scope, GameService, PlayerService) {
            $scope.addGame = function() {
                GameService.addGame($scope.newGame).then(function(game){
                });
            };
            $scope.addingGame=false;
            PlayerService.getPlayers().then(function(playerList){
                    $scope.players = playerList;
                    $scope.newGame={};
                    $scope.addingGame=true;
            });
        }]);
