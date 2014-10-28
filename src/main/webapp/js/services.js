'use strict';
var vbApp = angular.module('vbRankServices',[]);
vbApp.service('PlayerService',['$http','$q', function($http,$q) {
    this.getPlayers = function() {
        var q = $q.defer();
        $http.get('/rest/players/standings').success(function(data) {
            q.resolve(data);
        });
        return q.promise;
    };
}]);
