(function (ng) {

    var mod = ng.module("IdiomasModule");

    mod.constant("idiomasContext", "api/idiomas");

    mod.controller('idiomaNewCtrl', ['$scope', '$http', 'idiomasContext', '$state', 'recursosContext', '$rootScope',

        function ($scope, $http, idiomasContext, $state, recursosContext, $rootScope) {

            $rootScope.edit = false;

            $scope.createIdioma = function () {

                $http.post(idiomasContext, {

                    idioma: $scope.idiomaIdioma

                }).then(function (response) {

                    $state.go('idiomasList', {idiomaId: response.data.id}, {reload: true});

                });

            };

        }

    ]);

}

)(angular);
