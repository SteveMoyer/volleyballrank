'use strict';
var vbRankControllers = angular.module('vbRankControllers', ['vbRankServices']);
vbRankControllers.controller('StandingsCtrl',
        ['$scope', 'PlayerService', function($scope, PlayerService) {
            var that = this;
            this.getPlayers = function(){ PlayerService.getPlayers().then(function(players){
                that.players = players;
            });
            };
            this.getPlayers();
            this.insertPlayer = function() {
                var newPlayer = {};
                newPlayer.emailAddress = this.emailAddress;
                newPlayer.name = this.name;
                PlayerService.insertPlayer(newPlayer).then(function() {
                    that.emailAddress = null;
                    that.name = null;
                    that.getPlayers();
                });
            }
        }]);

vbRankControllers.controller('EditProfileCtrl',
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

vbRankControllers.controller('TabsCtrl',
        ['$scope', '$location', function($scope, $location) {
            var that = this;
            this.tabs = [ { link : '#/standings', label : 'Standings' },
            { link : '#/games/new', label : 'Add a game' },
            { link : '#/profile/me', label : 'Profile' }];

            this.selectedTab = this.tabs[0];
            this.setSelectedTab = function(tab) {
                that.selectedTab = tab;
            };

            this.tabClass = function(tab) {
                if (that.selectedTab == tab) {
                    return "active";
                } else {
                    return "";
                }
            };
        }]);

vbRankControllers.controller('ProfileCtrl',
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

vbRankControllers.controller('NewGameCtrl',
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
