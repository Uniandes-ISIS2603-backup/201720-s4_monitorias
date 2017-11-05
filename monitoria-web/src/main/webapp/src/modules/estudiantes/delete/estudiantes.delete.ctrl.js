(function (ng) {
    var mod = ng.module("EstudiantesModule");
    mod.constant("estudiantesContext", "api/estudiantes");
    mod.controller('estudianteDeleteCtrl', ['$scope', '$http', 'estudiantesContext', '$state',
        function ($scope, $http, estudiantesContext, $state) {
            var idEstudiante = $state.params.estudianteId;
            $scope.deleteEstudiante = function () {
                $http.delete(estudiantesContext + '/' + idEstudiante, {}).then(function (response) {
                    $state.go('estudiantesList', {estudianteId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);