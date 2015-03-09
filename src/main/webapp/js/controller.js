var productControllers = angular.module('productControllers', ['ngMaterial', 'ngMessages', 'productServices'])
    .config(function ($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('teal')
            .accentPalette('green');
    });

productControllers.controller('ConnectCtrl', ['$scope', '$location', 'productAppConnexion',
        function ($scope, $location, productAppConnexion) {
            $scope.user = {
                login: "",
                password: ""
            };
            $scope.requeting = false;

            $scope.errors = {};

            $scope.submit = function () {
                $scope.requeting = true;
                $scope.errors = {};
                productAppConnexion.connection($scope.user)
                    .success(function () {
                        $location.path('product')
                    })
                    .error(function () {
                        $scope.requeting = false;
                        $scope.errors.unauthorized = true;
                    });
            }
        }]
);

productControllers.controller('ProductListCtrl', ['$scope', '$location', 'Products',
        function ($scope, $location, Products) {
            $scope.search = "";
            $scope.sortOption = [
                "name",
                "amount"
            ];

            $scope.reverse = false;

            $scope.reverseSort = function () {
                $scope.reverse = !$scope.reverse;
            };

            $scope.onClickCart = function (id) {
                $location.path('product/' + id);
            };

            $scope.products = Products.query();
        }]
);

productControllers.controller('ProductDetailCtrl', ['$scope', '$routeParams', 'Product',
        function ($scope, $routeParams, Product) {
            $scope.tab = {
                selectedIndex: 0
            };
            $scope.next = function () {
                $scope.tab.selectedIndex = Math.min($scope.tab.selectedIndex + 1, 2);
            };
            $scope.previous = function () {
                $scope.v.selectedIndex = Math.max($scope.tab.selectedIndex - 1, 0);
            };
            $scope.productId = $routeParams.productId;
            $scope.product = Product.get({id: $scope.productId});
        }]
);
