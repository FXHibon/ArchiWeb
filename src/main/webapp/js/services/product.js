/**
 * Created by Baptiste on 11/03/2015.
 */

(function () {
    angular
        .module('productServices')
        .factory('Product', ['$resource', productFct]
    );
    function productFct($resource) {
        return $resource('rest/products/:id');
    }
})();