(function (ng) {

    var mod = ng.module("HorariosModule");

    mod.constant("horariosContex","api/horarios");
    
    mod.controller("horarioCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'horariosContext', function ($scope, $state, $stateParams, $http, horariosContext) {
            
                 $scope.createHorario = function(){
                   $http.post(horariosContext,
                           {
                                horaInicio: $scope.horarioHoraInicio,
                                horaFin: $scope.horarioHoraFin,
                                disponibilidad:$scope.horarioEstado  
                               
                            }).then(function (response){
                               
                                $state.go('horariosList',{horarioId: response.data.id},{reload: true});
                            });
               }
                 console.log($scope.horarioHoraInicio);
        }
    ]);
}
)(angular);

