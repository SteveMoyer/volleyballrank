'use strict';
goog.provide('net.stevemoyer.vbrank.controllers');
goog.require('net.stevemoyer.vbrank.services');
var vbRankControllers = angular.module('net.stevemoyer.vbrank.controllers', 
        [net.stevemoyer.vbrank.services.name]);
net.stevemoyer.vbrank.controllers=vbRankControllers;

vbRankControllers.controller('TabsCtrl',
        ['$scope', '$location','$rootScope', function($scope, $location,$rootScope) {
            var that = this;
            this.tabs = [ { link : '#/standings', label : 'Standings' },
            { link : '#/games/new', label : 'Add a game' },
            { link : '#/profile/me', label : 'Profile' }];

            $rootScope.$on('$viewContentLoaded', function(event,data) {
                $rootScope.$broadcast('tabChanged',data);
            });

            $scope.$on('tabChanged', function(event, data) {
                that.setSelectedTab(that.tabs.filter(function(element) {
                    return element.link === '#' +$location.path(); })[0]);
            });

            this.setSelectedTab = function(tab) {
                that.selectedTab = tab;
            };

            this.tabClass = function(tab) {
                if (that.selectedTab == tab) {
                    return "active";
                } else {
                    return "";
                }
            };
        }]);

