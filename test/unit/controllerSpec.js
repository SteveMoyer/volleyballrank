'use strict';
goog.require('vbrank.standings');
goog.require('vbrank.players');
describe('Volleyball controllers', function() {

    beforeEach(module('vbrank.standings', 'vbrank.players'));

    var playerService;
    var scope;
    var rootScope;
    var q;
    var controller;

    beforeEach(inject(function($rootScope, $controller, $q, PlayerService) {
        rootScope = $rootScope;
        controller = $controller;
        scope = rootScope.$new();
        playerService = PlayerService;
        q = $q;
    }));

    describe('Standings Controller', function(){
        var playersFixture = [{name:"anon 1"}];
        var deferred;

        beforeEach(function() {
            deferred = q.defer();
            spyOn(playerService, 'getPlayers').andReturn(deferred.promise);
            deferred.resolve(playersFixture);
        });

        it('should insert a new player and clear fields', function() {
            controller('StandingsCtrl as standings', {$scope:scope, PlayerService:playerService});
            var insertDeferred = q.defer();
            insertDeferred.resolve({});
            scope.standings.emailAddress = 'b@c.com';
            scope.standings.name = 'new player';
            spyOn(playerService,'insertPlayer').andReturn(insertDeferred.promise);

            scope.standings.insertPlayer();
            rootScope.$apply();

            expect(scope.standings.emailAddress).toBe(null);
            expect(scope.standings.name).toBe(null);
            expect(scope.standings.players).toBe(playersFixture);
        });
    });
});
