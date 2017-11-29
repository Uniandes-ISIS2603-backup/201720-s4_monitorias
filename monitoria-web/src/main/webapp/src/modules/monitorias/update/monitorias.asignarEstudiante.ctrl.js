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
              
           $scope.record=$state.params.idEstudiante;
           
           $scope.confirmacion=function(){$http.put(monitoriasContext + '/estudiante/'+ $state.params.idMonitoria+'/'+$state.params.idEstudiante).then(function(){
               $state.go('monitoriaDetail', {monitoriaId: $state.params.idMonitoria}, {reload: true});
           });};
           
                  
               
        }
    ]);
}
)(angular);