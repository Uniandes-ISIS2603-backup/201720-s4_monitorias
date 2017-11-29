(function (ng) {

    var mod = ng.module("EstudiantesModule");

    mod.constant("estudiantesContex","api/estudiantes");
    mod.constant("monitoriasContext","api/monitorias");
    mod.controller("estudiantesUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'estudiantesContext','monitoriasContext',
        function ($scope, $state, $stateParams, $http, estudiantesContext) {
              
               $http.get(estudiantesContext+ '/' + $state.params.estudianteId ).then(function(response){
                   var estudiante = response.data;
                   $scope.estudianteId = estudiante.id;
                   $scope.estame = estudiante.name;
                   
                   $scope.estudianteCodigo=estudiante.codigo;
                   $scope.estudianteCorreo=estudiante.correo;
                   $scope.estudiantePenalizacion=estudiante.penalizacion;
                   $scope.estudianteUltimaMonitoria=estudiante.ultimaMonitoria;
                   
               });
               
            
                
               $scope.updateEstudiante = function(){
                   $http.put(estudiantesContext + '/' + $state.params.estudianteId,{
                       name: $scope.estudianteName,
                       codigo:$scope.estudianteCodigo,
                       correo:$scope.estudianteCorreo,
                       penalizacion:$scope.estudiantePenalizacion,
                       ultimaMonitoria:$scope.estudianteUltimaMonitoria
                   }).then(function(response){
                       $state.go('estudiantesList',{estudianteId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);

