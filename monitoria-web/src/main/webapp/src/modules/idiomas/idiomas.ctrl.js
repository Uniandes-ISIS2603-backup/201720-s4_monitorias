(function (ng) {
    var mod = ng.module("IdiomasModule");
    mod.constant("idiomasContex", "api/idiomas");
    mod.controller("idiomasCtrl", ['$scope', '$state', '$stateParams', '$http', 'idiomasContext', function ($scope, $state, $stateParams, $http, idiomasContext) {

            $scope.buscarIdioma = function () {
                $scope.idiomasRecords = $scope.idiomasRecords.filter(function (idioma) {
                    return idioma.name.match("(.*)" + $scope.idiomaIdiomaBuscado + "(.*)");
                });
            };

            $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
            });

            if ($state.params.idiomaId !== undefined) {

                $http.get(idiomasContext + '/' + $state.params.idiomaId).then(function (response) {
                    $scope.recursosRecords = response.data.recursos;
                    $scope.vIdiomaId = $state.params.idiomaId;
                    $scope.currentIdioma = response.data;
                });
            }
        }
    ]);
}
)(angular);
