(function (ng) {

    var mod = ng.module("ActividadesModules");

    mod.constant("actividadesContext","actividades");
    mod.constant("monitoriasContext", "api/monitorias");
    mod.controller("actividadesNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'actividadesContext','monitoriasContext'
        , function ($scope, $state, $http, actividadesContext,monitoriasContext) {
          
            $scope.createActividad = function(){
                   $http.post(monitoriasContext + '/' + $state.params.monitoriaId + '/' + actividadesContext,
                           {
                                tareaAsignada: $scope.actividadTareaAsignada
                                ,descripcion: $scope.actividadDescripcion
                     
                            }).then(function (response){
                                $state.go('actividadesList',{actividadId: response.data.id},{reload: true});
                            });
               };
        }
    ]);
})(angular);

