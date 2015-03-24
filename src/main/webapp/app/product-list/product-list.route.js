/**
 * Created by fx on 24/03/2015.
 */

(function () {
    angular
        .module('ProductApp')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider.
            when('/product-list', {
                templateUrl: 'app/product-list/product-list.html',
                controller: 'ProductListCtrl'
            });
    }
})();