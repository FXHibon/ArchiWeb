/**
 * Created by Baptiste on 24/03/2015.
 */

(function () {
    angular.module('productControllers')
        .controller('AppCtrl', ['$scope', '$location', appCtrl]);

    function appCtrl($scope, $location) {
        $scope.data = {
            selectedIndex: 0
        };
        $scope.next = function () {
            $scope.data.selectedIndex = Math.min($scope.data.selectedIndex + 1, 2);
        };
        $scope.previous = function () {
            $scope.data.selectedIndex = Math.max($scope.data.selectedIndex - 1, 0);
        };
    }
})();