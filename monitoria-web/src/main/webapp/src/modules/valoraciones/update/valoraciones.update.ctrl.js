(function (ng) {

    var mod = ng.module("ValoracionesModule");

    mod.constant("valoracionesContex","api/valoraciones");

    mod.controller("valoracionesUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'valoracionesContext',
        function ($scope, $state, $stateParams, $http, valoracionesContext) {
              
               $http.get(valoracionesContext+ '/' + $stateParams.id ).then(function(response){
                   var valoracion = response.data;
                   $scope.comentario = valoracion.comentario;
                   $scope.calificacion = valoracion.calificacion;
                   $scope.fecha = valoracion.fecha;
                   
               });
                
               $scope.updateValoracion = function(){
                   $http.put(valoracionesContext + '/' + $state.params.id,{
                       comentario: $scope.comentario
                       ,calificacion: $scope.calificacion
                       ,fecha: $scope.fecha
                   }).then(function(response){
                       $state.go('valoracionesList',{valoracionId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);


