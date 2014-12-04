'use strict';
goog.provide('vbrank.games');
goog.require('vbrank.players');
var vbRankGames = angular.module('vbrank.games', 
        [vbrank.players.name]);
vbrank.games=vbRankGames;


vbRankGames.controller('NewGameCtrl',
        ['$scope', 'GameService', 'PlayerService', function($scope, GameService, PlayerService) {
            var that =this;
            that.addGame = function() {
                GameService.addGame(that.game).then(function(game){});
            };
            that.addingGame = false;
            that.getPlayerRefs = function() {
                PlayerService.getPlayerRefs().then(function(playerList){
                    that.players = playerList;
                    that.game = {};
                    that.addingGame = true;
                });
            };
        }]);

vbRankGames.directive('vbrNewGame', function(){
    return { restrict: 'E',
        scope: {},
        controller: 'NewGameCtrl',
        controllerAs: 'newGame',
        templateUrl: 'app/games/newGame.html',
        link: function(scope, elem, attrs) {
            scope.newGame.getPlayerRefs();
        }

    }
});

vbRankGames.service('GameService', ['$http', '$q', function($http, $q) {
    this.addGame = function(newGame) {
        var deferred = $q.defer();
        var url = '/rest/games';

        $http.post(url, newGame).success(function(data) {
            deferred.resolve(data);
        });

        return deferred.promise;
    };
    this.getGamesForPlayer = function(playerId) {
        var q = $q.defer();

        $http.get('/rest/games/latest/'+playerId).success(function(data) {
            q.resolve(data);
        });
        return q.promise;
    };

}]);

