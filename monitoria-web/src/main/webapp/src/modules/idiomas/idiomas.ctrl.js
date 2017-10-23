
(function (ng) {
    var mod = ng.module("IdiomasModule");
    mod.constant("idiomasContext", "api/idiomas");
    mod.controller('idiomasCtrl', ['$scope', '$http', 'idiomasContext', '$state',
        function ($scope, $http, idiomasContext, $state) {
            $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
            });

            if ($state.params.IdiomaId !== undefined) {
                $http.get(idiomasContext + '/' + $state.params.idiomasId).then(function (response) {
                    $scope.recursosRecords = response.data.recursos;
                    $scope.currentIdioma = response.data;
                });
            }
        }
    ]);
}
)(angular);


