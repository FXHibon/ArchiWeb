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
            when('/product/:productId', {
                templateUrl: 'app/product-detail/product-detail.html',
                controller: 'ProductDetailCtrl'
            });
    }
})();