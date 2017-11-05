(function (ng) {

    var mod = ng.module("EstudiantesModule");

    mod.constant("estudiantesContex","api/estudiantes");
    
    mod.controller("estudianteCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'estudiantesContext', function ($scope, $state, $stateParams, $http, estudiantesContext) {
               
                 $scope.createEstudiante = function(){
                   $http.post(estudiantesContext,
                           {
                                name: $scope.estudianteName,
                                codigo:$scope.estudianteCodigo,
                                correo:$scope.estudianteCorreo,
                                penalizacion:$scope.estudiantePenalizacion,
                                ultimaMonitoria:$scope.estudianteUltimaMonitoria
                            }).then(function (response){
                               
                                $state.go('estudiantesList',{estudianteId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
}
)(angular);

