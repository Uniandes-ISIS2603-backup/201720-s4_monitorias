(function (ng) {

    var mod = ng.module("HorariosModule");

    mod.constant("horariosContex","api/horarios");
   
    mod.controller("horarioUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'horariosContext','monitoriasContext',
        function ($scope, $state, $stateParams, $http, horariosContext) {
              
               $http.get(horariosContext+ '/' + $state.params.horarioId ).then(function(response){
                   var horario = response.data;
                   $scope.horarioId = horario.id;
                   $scope.horarioInicio = horario.horaInicio;
                   $scope.horarioFin=horario.horaFin;
                   $scope.horarioEstado=horario.estado;
            
               });
           
               $scope.updateHorario = function(){
                   $http.put(horariosContext + '/' + $state.params.horarioId,{
                      
                       horaInicio: $scope.horarioInicio,
                       horaFin:$scope.horarioFin,
                       estado:$scope.horarioEstado
                      
                   }).then(function(response){
                       $state.go('horariosList',{horarioId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);

