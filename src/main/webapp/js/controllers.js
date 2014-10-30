'use strict';
var vbRankControllers = angular.module('vbRankControllers', ['vbRankServices']);

vbRankControllers.controller('StandingsCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            $scope.getPlayers=function(){    PlayerService.getPlayers().then(function(players){
                $scope.players = players;
            });
            };
            $scope.getPlayers();
            $scope.insertPlayer = function() {
                var newPlayer={};
                newPlayer.emailAddress = $scope.emailAddress;
                newPlayer.name=$scope.name;
                PlayerService.insertPlayer(newPlayer).then(function() {
                    $scope.emailAddress=null;
                    $scope.name =null;
                    $scope.getPlayers();
                });
            }
        }]);

vbRankControllers.controller('ProfileCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            $scope.loadPlayer = function(playerId) {
                PlayerService.getPlayer(playerId).then(function(player){
                    $scope.player = player;
                });
            };

        }]);
