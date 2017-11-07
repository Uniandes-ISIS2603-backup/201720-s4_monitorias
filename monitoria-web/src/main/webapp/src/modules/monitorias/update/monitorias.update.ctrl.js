/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("MonitoriasModule");

    mod.constant("monitoriasContex","api/monitorias");

    mod.controller("monitoriasUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoriasContext',
        function ($scope, $state, $stateParams, $http, monitoriasContext) {
              
               $http.get(monitoriasContext+ '/' + $state.params.idMonitoria ).then(function(response){
                   var monitoria = response.data;
                   $scope.nombreMonitor = monitoria.nombreMonitor;
                   $scope.nombreEstudiante = monitoria.calificacion;
                   $scope.estado = monitoria.estado;
                    $scope.tipo = monitoria.tipo;
               });
               
               $scope.updateMonitoria = function(){
                   $http.put(monitoriasContext + '/' + $state.params.idMonitoria,{
                       
                       nombreMonitor: $scope.nombreMonitor
                       ,nombreEstudiante: $scope.nombreEstudiante
                       ,estado: $scope.estado,
                       tipo: $scope.tipo
                   }).then(function(response){
                       $state.go('monitoriasList',{monitoriaId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);



