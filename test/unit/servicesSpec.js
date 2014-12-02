'use strict';
goog.require('vbrank.players');
goog.require('vbrank.games');
describe('Volleyball services', function() {
    var http;
    var playerService;
    var gameService;
    var q;
    var deferred;
    var rootScope;

    beforeEach(module('vbrank.players','vbrank.games'));

    describe('PlayerService', function(){
        beforeEach(inject(function($rootScope, $http, $q, PlayerService) {
            http = $http;
            rootScope = $rootScope;
            q = $q
            playerService = PlayerService;
            deferred = q.defer();
            deferred.resolve({});
        }));

        describe('updatePlayer', function() {
            var playerFixture = {name:"anon 1", id:5};

            it('should post http update to player', function() {
                spyOn(http, 'post').andReturn({success: function() {}});

                playerService.updatePlayer(playerFixture);
                rootScope.$apply();

                expect(http.post).toHaveBeenCalledWith('/rest/players/5', playerFixture);
            });
        });
        describe('getPlayer', function() {
            it('should call get on me if not given a playerId', function() {
                spyOn(http, 'get').andReturn({success: function(){}});

                playerService.getPlayer();
                rootScope.$apply();

                expect(http.get).toHaveBeenCalledWith('/rest/players/me');
            });
            it('should call get with playerId if provided', function() {
                spyOn(http, 'get').andReturn({success: function(){}});

                playerService.getPlayer("player2");
                rootScope.$apply();

                expect(http.get).toHaveBeenCalledWith('/rest/players/player2');
            });
        });
        describe('getPlayers', function() {
            it('should call get for players', function() {
                spyOn(http,'get').andReturn( {success: function(){}});
                playerService.getPlayers();
                rootScope.$apply();
                expect(http.get).toHaveBeenCalledWith('/rest/players');
            });
        });
        describe('getPlayerRefs', function() {
            it('should call get for player refs', function() {
                spyOn(http,'get').andReturn( {success: function(){}});
                playerService.getPlayerRefs();
                rootScope.$apply();
                expect(http.get).toHaveBeenCalledWith('/rest/players/refs');
            });
        });

        describe('insertPlayer', function() {
            it('should call post with player', function() {
                spyOn(http,'post').andReturn( {success: function(){}});
                var newPlayer= {};
                playerService.insertPlayer(newPlayer);
                rootScope.$apply();
                expect(http.post).toHaveBeenCalledWith('/rest/players',newPlayer);
            });
               
        });
    });
    describe('GameService', function(){
        beforeEach(inject(function($rootScope, $http, $q, GameService) {
            http = $http;
            rootScope = $rootScope;
            q = $q
            gameService = GameService;
            deferred = q.defer();
            deferred.resolve({});
        }));
        describe('addGame', function(){
            it('should call post with game',function() {
                spyOn(http,'post').andReturn( {success: function(){}});
                var newGame= {};
                gameService.addGame(newGame);
                rootScope.$apply();
                expect(http.post).toHaveBeenCalledWith('/rest/games',newGame);


            });
        });
        describe('getGamesForPlayer', function(){
            it('should call get with playerId',function() {
                spyOn(http,'get').andReturn( {success: function(){}});
                gameService.getGamesForPlayer(1);
                rootScope.$apply();
                expect(http.get).toHaveBeenCalledWith('/rest/games/latest/1');


            });
        });
    });


});

