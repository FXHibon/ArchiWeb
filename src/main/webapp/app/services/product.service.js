/**
 * Created by Baptiste on 11/03/2015.
 */

(function () {
    angular
        .module('ProductApp')
        .factory('Product', productFct);

    productFct.$inject = ['$resource'];

    function productFct($resource) {
        return $resource('rest/products/:id');
    }
})();