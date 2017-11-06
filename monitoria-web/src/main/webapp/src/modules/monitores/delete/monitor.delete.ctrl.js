(function (ng) {
    var mod = ng.module("MonitoresModule");
    mod.constant("monitoresContext", "api/pagos");
    mod.controller('monitorDeleteCtrl', ['$scope', '$http', 'monitoresContext', '$state',
        function ($scope, $http, monitoresContext, $state) {
            var monitorCodigo = $state.params.monitorCodigo;
            $scope.deleteMonitor = function () {
                $http.delete(monitoresContext + '/' + monitorCodigo, {}).then(function (response) {
                    $state.go('monitoresList', {monitorCodigo: response.data.codigo}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);