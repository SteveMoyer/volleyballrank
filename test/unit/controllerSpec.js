'use strict';

describe('Volleyball controllers', function() {

    beforeEach(module('vbRank'));

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

        it('should populate players', function() {
            controller('StandingsCtrl as standings', {$scope:scope, PlayerService:playerService});
            rootScope.$apply();

            expect(scope.standings.players).toBe(playersFixture);
        });

    });

    describe('Profile Controller', function() {
        var playerFixture = {name:"anon 1"};
        var deferred;
        var ctrl;
        beforeEach(function() {
            deferred = q.defer();
            deferred.resolve(playerFixture);
            controller('ProfileCtrl as profile', {$scope:scope, PlayerService:playerService});
        });

        it('should populate player', function() {
            spyOn(playerService, 'getPlayer').andReturn(deferred.promise);
            scope.profile.getPlayer();
            rootScope.$apply();

            expect(scope.profile.player).toBe(playerFixture);
        });

    });
    /// TODO: test New Game Controller
});
