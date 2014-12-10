'use strict';
goog.require('vbrank.app');
describe('Volleyball routes', function() {

    beforeEach(module('vbrank.app'));

    it('should map to /games/new to new game', inject(function($route) {
        expect($route.routes['/games/new'].template).toEqual('<vbr-new-game></vbr-new-game>');
    }));

    it('should map to me to edit profile', inject(function($route) {
        expect($route.routes['/profile/me'].template).toEqual('<vbr-edit-profile></vbr-edit-profile>');
    }));
    
    it('should map routes to standings', inject(function($route) {
        expect($route.routes['/standings'].template).toEqual('<vbr-standings></vbr-standings>');
        expect($route.routes[null].redirectTo).toBe('/standings');
    }));
});
