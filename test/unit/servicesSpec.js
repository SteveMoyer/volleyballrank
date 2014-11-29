'use strict';
goog.require('net.stevemoyer.vbrank.services');
describe('Volleyball services', function() {
    var http;
    var playerService;
    var q;
    var deferred;
    var rootScope;

    beforeEach(module('net.stevemoyer.vbrank.services'));

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
        describe('insertPlayer', function() {
        });
    });
    /// TODO: Test GameService
});

