'use strict';
goog.provide('vbrank.user.service')

var vbUserService = angular.module('vbrank.user.service', []);
vbrank.user.service = vbUserService;

vbUserService.service('UserService', ['$http', '$q', function($http, $q) {
    this.getUser = function() {
        var q = $q.defer();

        $http.get('/rest/players/me').success(function(data) {
            q.resolve(data);
        }).error(function(data) {
            q.reject(data);
        });
        return q.promise;
    };
}]);

