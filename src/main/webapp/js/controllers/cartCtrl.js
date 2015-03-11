angular.module('productControllers')
    .controller('CartCtrl', ['$scope', '$location', 'CartProduct', cartCtrl]);

function cartCtrl($scope, $location, CartProduct) {
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

    $scope.products = CartProduct.query();
};