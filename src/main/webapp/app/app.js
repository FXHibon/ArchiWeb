(function () {

    'use strict';

    angular
        .module('ProductApp', [
            'ngRoute',
            'ngMaterial',
            'ngMessages',
            'ngResource'
        ])
        .config(routeConfig)
        .config(themeConfig);

    routeConfig.$inject = ['$routeProvider'];
    themeConfig.$inject = ['$mdThemingProvider'];

    function routeConfig($routeProvider) {
        $routeProvider.
            otherwise({
                redirectTo: '/connection'
            });
    }

    function themeConfig($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('teal')
            .accentPalette('green');
    }
})();