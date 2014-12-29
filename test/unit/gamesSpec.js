'use strict';
goog.require('vbrank.games');
goog.require('vbrank.players');
describe('Game functionality', function() {

    beforeEach(function() {
        module('vbrank.games');
        module('vbrank.players');
        module('templates');
    });

    var gameService;
    var playerService;
    var scope;
    var rootScope;
    var q;
    var controller;
    var compile;


    beforeEach(inject(function($rootScope, $controller, $q, PlayerService, GameService,$compile) {
        rootScope = $rootScope;
        controller = $controller;
        scope = rootScope.$new();
        gameService = GameService;
        playerService = PlayerService;
        compile = $compile;

        q = $q;
    }));

    describe('New Game Directive', function() {
        var deferredGet;

        beforeEach(function() {
            deferredGet = q.defer();
            deferredGet.resolve([]);
        });
        it('should retrieve player refs', function() {
            var element = compile('<vbr-new-game player-id="1"></vbr-new-game>')(rootScope);
            spyOn(playerService, 'getPlayerRefs').andReturn(deferredGet.promise);

            scope.$digest();
            expect(playerService.getPlayerRefs).toHaveBeenCalledWith();
            expect(scope.newGame.game.playerA.id).toEqual("1");

        });

    });


    describe('New Game Controller', function() {
        var playerRefsFixture = [];
        var deferredGet;

        beforeEach(function() {
            deferredGet = q.defer();
            deferredGet.resolve(playerRefsFixture);
            controller('NewGameCtrl as newGame', {$scope:scope, PlayerService:playerService,
                GameSerivce:gameService});
        });

        it('should add game', function() {
            spyOn(gameService, 'addGame').andReturn(deferredGet.promise);
            scope.newGame.addGame();
            rootScope.$apply();
            expect(gameService.addGame).toHaveBeenCalledWith(scope.newGame.game);
        });


    });
});
