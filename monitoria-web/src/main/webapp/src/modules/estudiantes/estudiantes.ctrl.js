(function (ng) {

    var mod = ng.module("EstudiantesModule");

    mod.constant("estudiantesContext","api/estudiantes");
    
    mod.controller("estudiantesCtrl", ['$scope', '$state', '$stateParams', '$http', 'estudiantesContext',
        function ($scope, $state, $stateParams, $http, estudiantesContext) {
               
                $http.get(estudiantesContext).then(function (response) {
                $scope.estudiantes = response.data;
            });
            
               
               if($state.params.estudianteId !== undefined){
                   $http.get(estudiantesContext + '/' + $state.params.estudianteId).then(function (response){
                       
                       $scope.varEstudianteId = $state.params.estudianteId;
                       $scope.currentEstudiante = response.data;
                       
                   });
               }
        }
        
    ]);
  
}
)(angular);

