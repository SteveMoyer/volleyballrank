'use strict';
var vbApp = angular.module('vbRankServices',[]);
vbApp.service('PlayerService', function() {
    this.getPlayers = function() {
        return [
        { name: "jim", wins:5, losses:1},
        { name: "john", wins:4, losses:2} ];
};
});
