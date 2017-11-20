(function (ng) {
    var mod = ng.module("ActividadesModule");
    
    mod.constant("monitoriaContext", "api/monitorias");
    mod.constant("actividadesContext","actividades");
    
    mod.controller('actividadDeleteCtrl', ['$scope', '$http', 'monitoriaContext', '$state','actividadesContext',
        function ($scope, $http, monitoriaContext,$state , actividadesContext) {
            var idMonitoria = $state.params.monitoriaId;
            var idActividad = $state.params.actividadId;
            $scope.deleteActividad = function () {
                $http.delete(monitoriaContext + '/' + idMonitoria + '/' + actividadesContext + '/' + idActividad ,{}).then(function (response) {
                    $state.go('actividadesList', {actividadId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);