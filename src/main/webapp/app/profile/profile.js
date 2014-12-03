'use strict';
goog.provide('vbrank.profile');
goog.require('vbrank.players');
var vbRankProfile = angular.module('vbrank.profile',
        [vbrank.players.name]);
vbrank.profile = vbRankProfile;

vbRankProfile.controller('EditProfileCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            var that = this;
            this.getMe = function() {
                PlayerService.getPlayer().then(function(player){
                    that.player = player;
                });
            };
            this.updatePlayer = function() {
                PlayerService.updatePlayer(that.player).then(function(player){
                    that.player = player;
                });
            };
        }]);

vbRankProfile.controller('ProfileCtrl',
        ['$scope', 'PlayerService','GameService', function($scope, PlayerService, GameService) {
            var that = this;
            $scope.ctrl=that;
            this.getPlayer = function(playerId) {
                PlayerService.getPlayer(playerId).then(function(player){
                    that.player = player;
                });
            };
            this.getGames = function(playerId) {
                GameService.getGamesForPlayer(playerId).then(function(games){
                    games.forEach(function(game) {
                        if(playerId == game.playerA.id || playerId == game.playerB.id){
                            game.win = game.teamABScore > game.teamCDScore;
                        } else {
                            game.win = game.teamABScore < game.teamCDScore;
                        }
                    });
                    that.games = games;
                });
            };
        }]);

vbRankProfile.directive('vbrProfile', function(){
    return {
        restrict: 'E',
        templateUrl: 'app/profile/profile.html',
        controller: 'ProfileCtrl',
        controllerAs: 'profile',
        link: function(scope, elem, attrs) {
            scope.profile.getPlayer(attrs.playerId);
            scope.profile.getGames(attrs.playerId);
        }
    }
});

vbRankProfile.directive('vbrEditProfile', function(){
    return { restrict: 'E',
        scope: {},
        controller: 'EditProfileCtrl',
        controllerAs: 'editProfile',
        templateUrl: 'app/profile/editProfile.html',
        link: function(scope, elem, attrs) {
            scope.editProfile.getMe();
        }

    }
});

