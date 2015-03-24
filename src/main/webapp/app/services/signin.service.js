/**
 * Created by Baptiste on 11/03/2015.
 */

(function () {
    angular
        .module('ProductApp')
        .factory('productAppConnexion', productAppConnexionFct);

    function productAppConnexionFct($http) {
        return {
            connection: function (user) {
                return $http.post('rest/connect', user);
            }
        };
    }
})();