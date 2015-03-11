angular.module('productControllers')
    .controller('ConnectionCtrl', ['$scope', '$location', 'productAppConnexion', connectionCtrl]);

function connectionCtrl($scope, $location, productAppConnexion) {
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
}
