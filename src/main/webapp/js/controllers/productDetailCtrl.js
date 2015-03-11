angular.module('productControllers')
    .controller('ProductDetailCtrl', ['$scope', '$routeParams', 'Product', 'CartProduct', productDetailCtrl]);

function productDetailCtrl($scope, $routeParams, Product, CartProduct) {
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

    $scope.order = function (queryAmount) {
        $scope.product.amount -= queryAmount;
        Product.save({id: $scope.productId}, $scope.product,
            function (data) {
                console.info("product.save OK: ", data);
            },
            function (data) {
                console.error("product.save KO: ", data);
            });

        var productInCart = {};
        productInCart.name = $scope.product.name;
        productInCart.id = $scope.product.id;
        productInCart.image = $scope.product.image;
        productInCart.description = $scope.product.description;
        productInCart.amount = queryAmount;
        CartProduct.save({id: $scope.productId}, productInCart);
    }
};