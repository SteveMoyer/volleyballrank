'use strict';
goog.require('vbrank.user.service');
goog.provide('vbrank.user.ctrl');
var vbRankUser = angular.module('vbrank.user.ctrl',
        [vbrank.user.service.name]);
vbrank.user.ctrl = vbRankUser;

vbRankUser.controller('UserCtrl',
        ['$scope',"$window", 'UserService', function($scope, $window, UserService) {
            var that = this;
            this.getCurrentUser = function() {
                UserService.getUser().then(function(user){
                    that.user = user;
                },function(data) {
                    $window.location.href = '/unauthorized.html';
                });
            };
            this.getCurrentUser();
        }]);


