(function (ng) {

    var mod = ng.module("MonitoriasModule");

    mod.constant("monitoriasContext","api/monitorias");
    mod.controller("cambiarEstadoCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoriasContext',
        function ($scope, $state, $stateParams, $http, monitoriasContext
                ) {
              
               $http.get(monitoriasContext+ '/' + $state.params.monitoriaId ).then(function(response){
                   var monitoria = response.data;
                   $scope.estadoActual = monitoria.Estado;
                   $scope.monitor=$state.params.idMonitor;
                   
               });
      
                
               $scope.cambiarEstado = function(){
                   $http.put(monitoriasContext + '/' + $state.params.monitoriaId,{
                      estado: $scope.estadoNuevo
                       
                   }).then(function(){
                       $state.go('monitorDetail',{monitorCodigo:$state.params.idMonitor},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);