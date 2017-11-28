/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {

    var mod = ng.module("MonitoriasModule");

    mod.constant("monitoriasContex","api/monitorias");

    mod.controller("monitoriasAsignarEstudianteCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoriasContext',
        function ($scope, $state, $stateParams, $http, monitoriasContext) {
              
           
           $http.put(monitoriasContext + '/estudiante/'+ $state.params.idMonitoria+'/'+$state.params.idEstudiante).then(function(){
           
           $http.get(monitoriasContext + '/' + $state.params.idMonitoria).then(function (response) {
                    $scope.actividadesRecords = response.data.actividades;
                    $scope.vMonitoriaId = $state.params.monitoriaId;
                    $scope.currentMonitoria = response.data;
               
                });});
           
                  
               
        }
    ]);
}
)(angular);