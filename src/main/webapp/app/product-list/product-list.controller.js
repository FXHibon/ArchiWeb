/**
 * Created by fx on 24/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('ProductApp')
        .controller('ProductListCtrl', productListCtrl);

    productListCtrl.$inject = ['$scope', '$location', 'Product'];

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
    }
})();