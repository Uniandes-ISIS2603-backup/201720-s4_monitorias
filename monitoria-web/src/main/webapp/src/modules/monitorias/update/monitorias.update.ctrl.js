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
              
             $http.get(monitoriasContext).then(function (response) {
                $scope.respuesta = response.data;
                $scope.records =[];
                var monitoria;
                for(var i =0; i < $scope.respuesta.length; i++){
                    
                 $http.get("api/monitorias/" + $scope.respuesta[i].id).then(function (response) {
                    $scope.monitoria = response.data;
                     monitoria =  $scope.monitoria;
                     if(monitoria.estado === ("pendiente") || monitoria.estado === ("Pendiente") ){
                        if(monitoria.tipo === "larga"){
                            if(monitoria.estudiantes.length < 4){
                                $scope.records.push(monitoria);  
                            } 
                        }else
                        {
                            if(monitoria.estudiantes.length <1){
                                $scope.records.push(monitoria);
                            }
                        }
                      }
                  });
                };
             
                $scope.estudiante=$state.params.idEstudiante;
            });
            
               
        }
    ]);
}
)(angular);



