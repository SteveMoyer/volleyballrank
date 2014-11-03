'use strict';
describe('Volleyball services', function() {
    var http;
    var playerService;
    var q;
    var deferred;
    var rootScope;

    beforeEach(module('vbRankServices'));

    describe('PlayerService', function(){
        beforeEach(inject(function($rootScope, $http, $q, PlayerService) {
            http = $http;
            rootScope = $rootScope;
            q = $q
            playerService = PlayerService;
            deferred = q.defer();
            deferred.resolve({});
        }));

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

