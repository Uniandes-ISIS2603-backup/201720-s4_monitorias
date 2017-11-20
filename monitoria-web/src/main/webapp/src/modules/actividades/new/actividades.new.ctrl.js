(function (ng) {

    var mod = ng.module("ActividadesModule");

    mod.constant("actividadesContext","actividades");
    mod.constant("monitoriaContext", "api/monitorias");

    mod.controller("actividadesNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'actividadesContext','monitoriaContext'
        , function ($scope, $state, $stateParams, $http, actividadesContext,monitoriaContext) {
              
           
            
            $scope.createActividad = function(){
                   $http.post(monitoriaContext + '/' + $state.params.monitoriaId + '/' + actividadesContext,
                           {
                                tareaAsignada: $scope.actividadTareaAsignada
                                ,descripcion: $scope.actividadDescripcion
                            }).then(function (response){
                                $state.go('actividadesList',{actividadId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
})(angular);


