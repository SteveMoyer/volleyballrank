'use strict';

describe('Volleyball controllers', function() {

    beforeEach(module('vbRank'));

    var playerService;
    var gameService;
    var scope;
    var rootScope;
    var q;
    var controller;

    beforeEach(inject(function($rootScope, $controller, $q, PlayerService, GameService) {
        rootScope = $rootScope;
        controller = $controller;
        scope = rootScope.$new();
        playerService = PlayerService;
        gameService = GameService;
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
    describe('Edit Profile Controller', function() {
        var playerFixture = {id:5, name:"anon 1"};
        var updatedFixture = {id:5, name:"anon 2"};
        var deferredGet;

        beforeEach(function() {
            deferredGet = q.defer();
            deferredGet.resolve(playerFixture);
            controller('EditProfileCtrl as editProfile', {$scope:scope, PlayerService:playerService});
        });

        it('should retrieve my profile', function() {
            spyOn(playerService, 'getPlayer').andReturn(deferredGet.promise);
            scope.editProfile.getMe();
            rootScope.$apply();
            expect(scope.editProfile.player).toBe(playerFixture);
        });

        it('should update my profile', function() {
            var deferredPost = q.defer();
            deferredPost.resolve(updatedFixture);
            spyOn(playerService, 'getPlayer').andReturn(deferredGet.promise);
            spyOn(playerService, 'updatePlayer').andReturn(deferredPost.promise);
            scope.editProfile.updatePlayer(playerFixture);
            rootScope.$apply();
            expect(scope.editProfile.player).toBe(updatedFixture);

        });
    });
    describe('Profile Controller', function() {
        var playerFixture = {name:"anon 1"};
        var deferred;
        beforeEach(function() {
            controller('ProfileCtrl as profile', {$scope:scope, PlayerService:playerService,
                GameService: gameService});
        });
        describe('getPlayer', function() {
            beforeEach(function() {
                deferred = q.defer();
                deferred.resolve(playerFixture);
            });
            it('should populate player', function() {
                spyOn(playerService, 'getPlayer').andReturn(deferred.promise);
                scope.profile.getPlayer();
                rootScope.$apply();

                expect(scope.profile.player).toBe(playerFixture);
            });
        });
        describe('getGamesForPlayer', function() {
            var gameFixture;
            beforeEach(function() {
                deferred = q.defer();
            });
            it('should populate games', function() {
                gameFixture =[ {id:12345, playerA: {id:1}, playerB: {id:2},
                    playerC: {id:3}, playerD: {id:4}, teamABScore: 21, teamCDScore:19 }];

                deferred.resolve(gameFixture);
                spyOn(gameService, 'getGamesForPlayer').andReturn(deferred.promise);
                scope.profile.getGames(1);
                rootScope.$apply();

                expect(scope.profile.games).toBe(gameFixture);
            });
          it('should calculate wins', function() {
              gameFixture =[
              {id:12345, playerA: {id:1}, playerB: {id:2}, playerC: {id:3}, playerD: {id:4}, teamABScore: 21, teamCDScore:19 },
              {id:12346, playerA: {id:3}, playerB: {id:2}, playerC: {id:1}, playerD: {id:4}, teamABScore: 21, teamCDScore:19 },
              {id:12347, playerA: {id:1}, playerB: {id:2}, playerC: {id:3}, playerD: {id:4}, teamABScore: 19, teamCDScore:21 },
              {id:12348, playerA: {id:3}, playerB: {id:2}, playerC: {id:1}, playerD: {id:4}, teamABScore: 19, teamCDScore:21 }
              ];

              deferred.resolve(gameFixture);
                spyOn(gameService, 'getGamesForPlayer').andReturn(deferred.promise);
                scope.profile.getGames(1);
                rootScope.$apply();

                expect(gameFixture[0].win).toBe(true);
                expect(gameFixture[1].win).toBe(false);
                expect(gameFixture[2].win).toBe(false);
                expect(gameFixture[3].win).toBe(true);
            });


        });

    });
    /// TODO: test New Game Controller
});
