(function (ng) {
    var mod = ng.module("ActividadesModule");
    mod.constant("actividadesContext", "api/actividades");
    mod.controller('actividadesDeleteCtrl', ['$scope', '$http', 'actividadesContext', '$state',
        
        function ($scope, $http, actividadesContext, $state) {
            var idActividad = $state.params.actividadId;
            $scope.deleteActividad = function () {
                $http.delete(actividadesContext + '/' + idActividad, {}).then(function (response) {
                    $state.go('actividadesList', {actividadId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
