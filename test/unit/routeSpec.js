'use strict';

describe('Volleyball routes', function() {

    beforeEach(module('vbRank'));
    beforeEach(module('vbRankServices'));

    it('should map routes to profile', inject(function($route) {
        expect($route.routes['/profile'].template).toEqual('<vbr-profile></vbr-profile>');
        expect($route.routes[null].redirectTo).toBe('/profile');
    }));

    it('should map routes to standings', inject(function($route) {
        expect($route.routes['/standings'].template).toEqual('<vbr-standings></vbr-standings>');
    }));
});
