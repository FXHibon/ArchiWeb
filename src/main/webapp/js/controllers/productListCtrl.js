(function () {
    angular.module('productControllers')
        .controller('ProductListCtrl', ['$scope', '$location', 'Product', productListCtrl]);

    function productListCtrl($scope, $location, Product) {
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

        $scope.products = Product.query();
    };
})();