/**
 * Created by Baptiste on 11/03/2015.
 */

(function () {
    angular
        .module('productServices')
        .factory('CartProduct', ['$resource', cartProductFct]
    );
    function cartProductFct($resource) {
        return $resource('rest/carts/:id');
    }
})();