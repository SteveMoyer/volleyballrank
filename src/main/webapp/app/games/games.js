'use strict';
goog.provide('vbrank.games');
goog.require('net.stevemoyer.vbrank.services');
var vbRankGames = angular.module('vbrank.games', 
        [net.stevemoyer.vbrank.services.name]);
vbrank.games=vbRankGames;


vbRankGames.controller('NewGameCtrl',
        ['$scope', 'GameService', 'PlayerService', function($scope, GameService, PlayerService) {
            var that =this;
            that.addGame = function() {
                GameService.addGame(that.game).then(function(game){});
            };
            that.addingGame = false;
            PlayerService.getPlayerRefs().then(function(playerList){
                that.players = playerList;
                that.game={};
                that.addingGame=true;
            });
        }]);
vbRankGames.directive('vbrNewGame', function(){
    return { restrict: 'E',
        scope: {},
        controller: 'NewGameCtrl',
        controllerAs: 'newGame',
        templateUrl: 'app/games/newGame.html',
    }
});


