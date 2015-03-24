/**
 * Created by Baptiste on 11/03/2015.
 */

(function () {
    angular
        .module('ProductApp')
        .factory('CartProduct', cartProductFct);

    cartProductFct.$inject = ['$resource'];

    function cartProductFct($resource) {
        return $resource('rest/carts/:id');
    }
})();