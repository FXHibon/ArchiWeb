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

productControllers.controller('ProductListCtrl', function ($scope, Products) {
    $scope.search = "";
    $scope.sortOption = [
        "title",
        "quantity"
    ];
    $scope.reverseSort = false;
    $scope.onCLickReverseSort = function () {
        $scope.reverseSort = !$scope.reverseSort;
    };
    $scope.products = Products.query();
    /*$scope.products = [{
        title: "produit 1",
        quantity: "5",
        image: "data:image/jpeg;base64,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",
        description: "le produit 1 il est bien..."
    },
        {
            title: "produit 2",
            quantity: "55",
            image: "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTVupgjmRolJ0JRmSSVFO3PasVcYvB7_gbdxcCQ52_yQgftQ-aHOHUr3b6k",
            description: "le produit 2 il est mieu..."
        },
        {
            title: "aroduit 2",
            quantity: "8",
            image: "",
            description: "le produit 2 il est mieu..."
        },
        {
            title: "produit 2",
            quantity: "8",
            image: "",
            description: "le produit 2 il est mieu..."
        },
        {
            title: "produit 2",
            quantity: "8",
            image: "",
            description: "le produit 2 il est mieu..."
        },
        {
            title: "aaaroduit 2",
            quantity: "1",
            image: "",
            description: "le produit 2 il est mieu..."
        },
        {
            title: "produit 2",
            quantity: "8",
            image: "",
            description: "le produit 2 il est mieu..."
        },
        {
            title: "produit 2",
            quantity: "8",
            image: "",
            description: "le produit 2 il est mieu..."
     }];*/
});

productControllers.controller('ProductDetailCtrl', function ($scope, Product) {
    $scope.tab = {
        selectedIndex: 0
    };
    $scope.next = function () {
        $scope.tab.selectedIndex = Math.min($scope.tab.selectedIndex + 1, 2);
    };
    $scope.previous = function () {
        $scope.v.selectedIndex = Math.max($scope.tab.selectedIndex - 1, 0);
    };
    $scope.product = Product.query("a");
});
