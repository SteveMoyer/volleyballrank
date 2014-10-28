'use strict';

/* jasmine specs for routes go here */
describe('Volleyball controllers', function() {

    beforeEach(module('vbRank'));
    var playerService;
    var scope;
    var rootScope;
    var q;
    var controller;

    beforeEach(inject(function($rootScope, $controller,$q, PlayerService) {
        rootScope=$rootScope;
        controller=$controller;
        scope = rootScope.$new();
        playerService=PlayerService;
        q=$q;
    }));

    describe('Standings Controller',function(){
        var playersFixture=[{name:"anon 1"}];
        var deferred;
        beforeEach(function() {
            deferred = q.defer();
            spyOn(playerService,'getPlayers').andReturn(deferred.promise);
            controller('StandingsCtrl',{$scope:scope, PlayerService:playerService});
        });
        it('should populate players', function() {
            deferred.resolve(playersFixture);
            rootScope.$apply();
            expect(scope.players).toBe(playersFixture);
        });

    });
    describe('Profile Controller', function() {
        var playerFixture={name:"anon 1"};
        var deferred;
        beforeEach(function() {
            deferred = q.defer();
            spyOn(playerService,'getPlayer').andReturn(deferred.promise);
            controller('ProfileCtrl',{$scope:scope, PlayerService:playerService});
        });
        it('should populate player', function() {
            deferred.resolve(playerFixture);
            rootScope.$apply();
            expect(scope.player).toBe(playerFixture);
        });

    });
});
