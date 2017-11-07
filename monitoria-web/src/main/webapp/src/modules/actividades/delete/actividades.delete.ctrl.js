(function (ng) {
    var mod = ng.module("ActividadesModule");
    
    mod.constant("monitoriasContext", "api/monitorias");
    mod.constant("actividadesContext","actividades");
    mod.controller('$state','actividadDeleteCtrl', ['$scope', '$http', 'monitoriasContext', 'actividadesContext',
        function ($scope, $http, monitoriasContext,$state , actividadesContext) {
            
            var idActividad = $state.params.actividadId;
            var idMonitoria = $state.params.monitoriaId;
            $scope.deletActividad = function () {
                $http.delete(monitoriasContext + '/' + idMonitoria + '/' + actividadesContext + '/' + idActividad ,{}).then(function (response) {
                    $state.go('actividadesList', {actividadId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);