(function (ng) {

    var mod = ng.module("ActividadesModule");

    mod.constant("actividadesContext","actividades");
    mod.constant("monitoriasContext", "api/monitorias");
     

    mod.controller("actividadesUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'actividadesContext','monitoriasContext'
        , function ($scope, $state, $stateParams, $http, actividadesContext,monitoriasContext) {
           
            
            if($state.params.actividadId !== undefined){
                   $http.get(monitoriasContext +'/'+ $state.params.monitoriaId + '/' + actividadesContext + '/' + $state.params.actividadId).then(function (response){
                       var actividad = response.data;
                       $scope.actividadTareaAsignada = actividad.tareaAsignada;
                       $scope.actividadDescripcion = actividad.descripcion;
                   });
            }
             $scope.updateActividad = function(){
             $http.put(monitoriasContext + '/' + $state.params.monitoriaId + '/' + actividadesContext +'/'+ $state.params.actividadId,
             {
                    tareaAsignada: $scope.actividadTareaAsignada
                    ,descripcion: $scope.actividadDescripcion
                    }).then(function (response){
                   
                    $state.go('actividadesList',{actividadId: response.data.id},{reload: true});                     });
               };
        }
    ]);
})(angular);

