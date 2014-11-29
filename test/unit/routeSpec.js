'use strict';
goog.require('net.stevemoyer.vbrank.app');
describe('Volleyball routes', function() {

    beforeEach(module('net.stevemoyer.vbrank.app'));

    it('should map routes to profile', inject(function($route) {
        expect($route.routes['/profile'].template).toEqual('<vbr-profile></vbr-profile>');
    }));
    it('should map to me to new game', inject(function($route) {
        expect($route.routes['/games/new'].template).toEqual('<vbr-new-game></vbr-new-game>');
    }));


    it('should map to me to edit  profile', inject(function($route) {
        expect($route.routes['/profile/me'].template).toEqual('<vbr-edit-profile></vbr-edit-profile>');
    }));


    it('should map routes to standings', inject(function($route) {
        expect($route.routes['/standings'].template).toEqual('<vbr-standings></vbr-standings>');
        expect($route.routes[null].redirectTo).toBe('/standings');
    }));
});
