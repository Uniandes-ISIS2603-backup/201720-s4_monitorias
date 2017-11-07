
(function (ng) {
    var mod = ng.module("ActividadesModule");
    mod.constant("actividadesContext", "actividades");
    mod.constant("monitoriasContext", "api/monitorias");
    mod.controller('actividadesCtrl', ['$scope', '$http', 'actividadesContext','monitoriasContext', '$state',
        function ($scope, $http, actividadesContext, $state, monitoriasContext) {
           $http.get(monitoriasContext + '/' + $state.params.monitoriaId + '/' + actividadesContext).then(function(response){
                   $scope.actividadesRecords = response.data;
               });
               
           if($state.params.recursoId !== undefined){
                $http.get(monitoriasContext +'/'+ $state.params.monitoriaId + '/' + actividadesContext + '/' + $state.params.actividadId).then(function (response){
                $scope.currentActividad = response.data;
                });
            }
        }
    ]);
}
)(angular);


