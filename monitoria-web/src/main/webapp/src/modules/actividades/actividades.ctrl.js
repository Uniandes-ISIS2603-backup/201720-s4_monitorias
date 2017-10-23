Idi
(function (ng) {
    var mod = ng.module("ActividadesModule");
    mod.constant("actividadesContext", "api/actividades");
    mod.controller('actividadesCtrl', ['$scope', '$http', 'actividadesContext', '$state',
        function ($scope, $http, actividadesContext, $state) {
            $http.get(actividadesContext).then(function (response) {
                $scope.actividadesRecords = response.data;
            });

            if ($state.params.ActividadId !== undefined) {
                $http.get(actividadesContext + '/' + $state.params.actividadesId).then(function (response) {
                    $scope.recursosRecords = response.data.recursos;
                    $scope.currentActividad = response.data;
                });
            }
        }
    ]);
}
)(angular);


