var productControllers = angular.module('productControllers', ['ngMaterial', 'ngMessages', 'productServices'])
    .config(function ($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('teal')
            .accentPalette('green');
    });

productControllers.controller('ConnectCtrl', function ($scope, $location, productAppConnexion) {
    $scope.user = {
        pseudo: "",
        password: ""
    };
    $scope.submit = function () {
        productAppConnexion.connection($scope.user).success(function () {
            $location.path('product')
        });
    }
});

productControllers.controller('ProductListCtrl', function ($scope, $location, Products) {
    $scope.search = "";
    $scope.sortOption = [
        "name",
        "amount"
    ];
    $scope.reverseSort = false;
    $scope.onCLickReverseSort = function () {
        $scope.reverseSort = !$scope.reverseSort;
    };
    $scope.onClickCart = function (id) {
        $location.path('product/' + id);
    };
    $scope.onClickMore = function (id) {
        $location.path('product/' + id);
    };
    $scope.products = Products.query();
});

productControllers.controller('ProductDetailCtrl', function ($scope, $routeParams, Product) {
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
});
