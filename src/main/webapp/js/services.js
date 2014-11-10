'use strict';

var vbApp = angular.module('vbRankServices', []);
vbApp.service('PlayerService', ['$http', '$q', function($http, $q) {
    this.getPlayers = function() {
        var q = $q.defer();

        $http.get('/rest/players').success(function(data) {
            q.resolve(data);
        });

        return q.promise;
    };

    this.getPlayer = function(playerId) {
        var q = $q.defer();
        var url;

        if(playerId){
            url = '/rest/players/' + playerId;
        } else {
            url = '/rest/players/me';
        }

        $http.get(url).success(function(data) {
            q.resolve(data);
        });

        return q.promise;
    };
    this.getPlayerRefs = function() {
        var q = $q.defer();

        $http.get('/rest/players/refs').success(function(data) {
            q.resolve(data);
        });
        return q.promise;
    };

    this.insertPlayer = function(newPlayer) {
        var q = $q.defer();
        var url = '/rest/players';

        $http.post(url, newPlayer).success(function(data) {
            q.resolve(data);
        });

        return q.promise;
    };
    this.updatePlayer = function(updatedPlayer) {
        var q = $q.defer();
        var url = '/rest/players/' + updatedPlayer.id;

        $http.post(url, updatedPlayer).success(function(data) {
            q.resolve(data);
        });

        return q.promise;
    }
}]);

vbApp.service('GameService', ['$http', '$q', function($http, $q) {
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
