'use strict';
var vbRankControllers = angular.module('vbRankControllers',['vbRankServices']);
vbRankControllers.controller('StandingsCtrl',
        ['$scope','PlayerService',function($scope,PlayerService) {
            PlayerService.getPlayers().then(function(players){
                $scope.players= players;
            });
}]);
vbRankControllers.controller('ProfileCtrl',
        ['$scope','PlayerService',function($scope,PlayerService) {

}]);
