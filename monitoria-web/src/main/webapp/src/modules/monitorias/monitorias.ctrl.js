(function (ng) {

    var mod = ng.module("MonitoriasModule");

    mod.constant("monitoriasContext", "api/monitorias");

    mod.controller("monitoriasCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoriasContext', function ($scope, $state, $stateParams, $http, context) {

            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });
            if ($state.params.monitoriaId !== undefined) {
                $http.get(context + '/' + $state.params.monitoriaId).then(function (response) {
                    $scope.actividadesRecords = response.data.actividades;
                    $scope.vMonitoriaId = $state.params.monitoriaId;
                    $scope.currentMonitoria = response.data;
                });
            }

        }
    ]);
})(window.angular);