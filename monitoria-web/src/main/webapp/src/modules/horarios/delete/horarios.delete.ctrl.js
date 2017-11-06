(function (ng) {
    var mod = ng.module("HorariosModule");
    mod.constant("horariosContext", "api/horarios");
    mod.controller('horarioDeleteCtrl', ['$scope', '$http', 'horariosContext', '$state',
        function ($scope, $http, horariosContext, $state) {
            var idHorario = $state.params.horarioId;
            $scope.deleteHorario = function () {
                $http.delete(horariosContext + '/' + idHorario, {}).then(function (response) {
                    $state.go('horariosList', {horarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);