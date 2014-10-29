'use strict';
describe('Volleyball services', function() {
    var http={get: function(){}};
    var playerService;
    var q;
    var deferred;
    var rootScope;
    beforeEach(module('vbRankServices'));
    describe('PlayerService', function(){
        beforeEach(inject(function($rootScope,$http,$q,PlayerService) {
            http=$http;
            rootScope=$rootScope;
            q=$q
            playerService = PlayerService;
        }));
        beforeEach(function() {
            deferred = q.defer();
            deferred.resolve({});
        });


        it('should call me if not given a playerId', function() {
            spyOn(http,'get').andReturn({success: function(){}});
            playerService.getPlayer().then(function(player) {

            });

            rootScope.$apply();
            expect(http.get).toHaveBeenCalledWith('/rest/players/me');
        });
        it('should call with playerId ', function() {
            spyOn(http,'get').andReturn({success: function(){}});
            playerService.getPlayer("player2").then(function(player) {

            });

            rootScope.$apply();
            expect(http.get).toHaveBeenCalledWith('/rest/players/player2');
        });
    });

});

