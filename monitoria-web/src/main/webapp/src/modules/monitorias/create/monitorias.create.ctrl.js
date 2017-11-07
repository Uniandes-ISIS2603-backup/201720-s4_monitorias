/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("MonitoriasModule");

    mod.constant("monitoriasContex","api/monitorias");
    
    mod.controller("monitoriasCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoriasContext', function ($scope, $state, $stateParams, $http, monitoriasContext) {
               
                 $scope.createMonitoria = function(){
                   $http.post(monitoriasContext,
                           {
                                nombreMonitor: $scope.nombreMonitor,
                                nombreEstudiante:$scope.nombreEstudiante
                                ,tipo: $scope.tipo,
                                estado:$scope.estado
                                
                            }).then(function (response){
                                //autor create successfully
                                $state.go('monitoriasList',{monitoriaId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
}
)(angular);
