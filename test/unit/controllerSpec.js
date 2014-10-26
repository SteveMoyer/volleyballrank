'use strict';

/* jasmine specs for routes go here */
describe('Volleyball controllers', function() {

    beforeEach(module('vbRank'));
    var playerService;
    var playersFixture={};
    var $scope;
    beforeEach(inject(function($rootScope, $controller) {
        $scope = $rootScope.$new();
        playerService = {
            getPlayers: function() { return playersFixture;}
        };
        $controller('StandingsCtrl',{$scope:$scope, PlayerService:playerService});
    }));
    describe('Standings Controller',function(){
        it('should populate players', function() {
            expect($scope.players).toBe(playersFixture);
        });

    });
});
