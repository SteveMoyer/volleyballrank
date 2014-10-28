'use strict';

/* jasmine specs for routes go here */
describe('Volleyball controllers', function() {

    beforeEach(module('vbRank'));
    var playerService;
    var playersFixture={};
    var scope;
    beforeEach(inject(function($rootScope, $controller,$q, PlayerService) {
        scope = $rootScope.$new();
        var q = $q.defer();
        spyOn(PlayerService,'getPlayers').andReturn(q.promise);
        $controller('StandingsCtrl',{$scope:scope, PlayerService:PlayerService});
        q.resolve(playersFixture);
    }));
    describe('Standings Controller',function(){
        it('should populate players', function() {
                expect(scope.players).toBe(playersFixture);
        });

    });
});
