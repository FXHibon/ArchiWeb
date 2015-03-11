angular
    .module('productApp', [
        'ngRoute',
        'productControllers'
    ])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider.
                when('/connection', {
                    templateUrl: 'view/connect.html',
                    controller: 'ConnectionCtrl'
                }).
                when('/product', {
                    templateUrl: 'view/product-list.html',
                    controller: 'ProductListCtrl'
                }).
                when('/product/:productId', {
                    templateUrl: 'view/product-detail.html',
                    controller: 'ProductDetailCtrl'
                }).
                when('/cart', {
                    templateUrl: 'view/cart.html',
                    controller: 'CartCtrl'
                }).
                otherwise({
                    redirectTo: '/connection'
                });
        }]
);