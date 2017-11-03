/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("ValoracionesModule");

    mod.constant("valoracionesContex","api/valoraciones");
    
    mod.controller("valoracionCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'valoracionesContext', function ($scope, $state, $stateParams, $http, valoracionesContext) {
               
                 $scope.createValoracion = function(){
                   $http.post(valoracionesContext,
                           {
                                comentario: $scope.comentario
                                ,calificacion: $scope.calificacion,
                                fecha:$scope.fecha
                            }).then(function (response){
                                //autor create successfully
                                $state.go('valoracionesList',{valoracionId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
}
)(angular);

