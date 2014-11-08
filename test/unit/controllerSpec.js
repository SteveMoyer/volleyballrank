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
