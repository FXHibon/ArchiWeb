/**
 * Created by fx on 24/03/2015.
 */

(function () {
    'use strict';

    angular
        .module('ProductApp')
        .controller('SigninController', connectionCtrl);

    connectionCtrl.$inject = ['$scope', '$location', 'productAppConnexion'];

    function connectionCtrl($scope, $location, productAppConnexion) {
        $scope.user = {
            login: "",
            password: ""
        };

        $scope.requeting = false;

        $scope.errors = {};

        $scope.submit = submit;

        function submit() {
            $scope.requeting = true;
            $scope.errors = {};
            productAppConnexion.connection($scope.user)
                .success(function () {
                    $location.path('product-list');
                })
                .error(function () {
                    $scope.requeting = false;
                    $scope.errors.unauthorized = true;
                });
        }
    }

})();
