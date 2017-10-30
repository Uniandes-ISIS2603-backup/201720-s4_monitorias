/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("ValoracionesModule");
    mod.constant("valoracionesContext", "api/valoraciones");
    mod.controller('valoracionDeleteCtrl', ['$scope', '$http', 'valoracionesContext', '$state',
        function ($scope, $http, valoracionesContext, $state) {
            var idValoracion = $state.params.valoracionId;
            $scope.deleteValoracion = function () {
                $http.delete(valoracionesContext + '/' + idValoracion, {}).then(function (response) {
                    $state.go('valoracionesList', {valoracionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
