/**
 * Created by Baptiste on 26/02/2015.
 */
var productServices = angular.module('productServices', ['ngResource']);

productServices.factory('productAppConnexion', function ($http) {
    return {
        connection: function (user) {
            return $http.post('rest/connect', user);
        }
    };
});

productServices.factory('Product', ['$resource',
    function ($resource) {
        return $resource('rest/products/:id');
    }]);

productServices.factory('Products', ['$resource',
        function ($resource) {
            return $resource('rest/products', {}, {
                query: {method: 'GET', isArray: true}
            });
        }]
);