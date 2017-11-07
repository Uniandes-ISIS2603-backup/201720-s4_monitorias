/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("MonitoriasModule");

    mod.constant("MonitoriasContex","api/monitorias");

    mod.controller("monitoriasUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'MonitoriasContext',
        function ($scope, $state, $stateParams, $http, MonitoriasContext) {
              
               $http.get(MonitoriasContext+ '/' + $stateParams.id ).then(function(response){
                   var monitoria = response.data;
                   $scope.nombreMonitor = monitoria.nombreMonitor;
                   $scope.nombreEstudiante = monitoria.calificacion;
                   $scope.estado = monitoria.estado;
                    $scope.tipo = monitoria.tipo;
               });
                
               $scope.updateMonitoria = function(){
                   $http.put(MonitoriasContext + '/' + $state.params.id,{
                       nombreMonitor: $scope.nombreMonitor
                       ,nombreEstudiante: $scope.nombreEstudiante
                       ,estado: $scope.estado,
                       tipo: $scope.tipo
                   }).then(function(response){
                       $state.go('valoracionesList',{valoracionId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);



