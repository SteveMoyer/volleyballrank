'use strict';
var vbRankControllers = angular.module('vbRankControllers',['vbRankServices']);
vbRankControllers.controller('StandingsCtrl',['$scope','PlayerService',function($scope,PlayerService) {
        $scope.players = PlayerService.getPlayers();
}]);
