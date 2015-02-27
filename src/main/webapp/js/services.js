/**
 * Created by Baptiste on 26/02/2015.
 */
var productServices = angular.module('productServices', ['ngResource']);

productServices.factory('productAppConnexion', function ($http) {
    return {
        connection: function (user) {
            return $http.post('rest/connect', {
                login: user.pseudo,
                password: user.password
            });
        }
    };
});

productServices.factory('Product', ['$resource', function ($resource) {
    return $resource('rest/product/:id', null, {query: {isArray: true}});
}]);

productServices.factory('Products', ['$resource',
        function ($resource) {
            return $resource('rest/product', {}, {
                query: {method: 'GET', isArray: true}
            });
        }]
);