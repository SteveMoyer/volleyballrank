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

        if(playerId == null){
            url = '/rest/players/me';
        } else {
            url = '/rest/players/' + playerId;
        }

        $http.get(url).success(function(data) {
            q.resolve(data);
        });

        return q.promise;
    };
}]);
