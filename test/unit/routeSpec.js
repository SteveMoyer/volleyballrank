'use strict';

/* jasmine specs for routes go here */
describe('Volleyball routes', function() {

    beforeEach(module('vbRank'));
    beforeEach(module('vbRankServices'));

    it('should map routes to profile', inject(function($route) {
        expect($route.routes['/profile'].controller).toBe('ProfileCtrl');
        expect($route.routes['/profile'].templateUrl).toEqual('views/profile.html');
        expect($route.routes[null].redirectTo).toBe('/profile');
    }));

    it('should map routes to standing', inject(function($route) {
        expect($route.routes['/standings'].controller).toBe('StandingsCtrl');
        expect($route.routes['/standings'].templateUrl).toEqual('views/standings.html');
    }));
});
