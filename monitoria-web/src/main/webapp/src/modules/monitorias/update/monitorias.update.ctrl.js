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
                $scope.records = response.data;
                $scope.estudiante=$state.params.idEstudiante;
            });
            
               
        }
    ]);
}
)(angular);



