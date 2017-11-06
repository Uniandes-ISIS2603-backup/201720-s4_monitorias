(function (ng) {

    var mod = ng.module("HorariosModule");

    mod.constant("horariosContext","api/horarios");
    
    mod.controller("horariosCtrl", ['$scope', '$state', '$stateParams', '$http', 'horariosContext',
        function ($scope, $state, $stateParams, $http, horariosContext) {
               
                $http.get(horariosContext).then(function (response) {
                $scope.horarios = response.data;
            });
            
               
               if($state.params.horarioId !== undefined){
                   $http.get(horariosContext + '/' + $state.params.horarioId).then(function (response){
                       
                       $scope.varHorarioId = ($state.params.horarioId);
                       $scope.currentHorario = response.data;
                   });
               }
        }
    ]);
}
)(angular);
