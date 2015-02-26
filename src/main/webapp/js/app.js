var productApp = angular.module('productApp', [
    'ngRoute',
    'productControllers'
]);

productApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/connection', {
                templateUrl: 'view/connect.html',
                controller: 'ConnectCtrl'
            }).
            when('/product', {
                templateUrl: 'view/product-list.html',
                controller: 'ProductDetailCtrl'
            }).
            when('/product/:productId', {
                templateUrl: 'view/product-detail.html',
                controller: 'ProductListCtrl'
            }).
            otherwise({
                redirectTo: '/connection'
            });
    }]);