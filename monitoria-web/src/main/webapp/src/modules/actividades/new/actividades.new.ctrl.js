(function (ng) {

    var mod = ng.module("ActividadModule");

    mod.constant("actividadesContext", "api/actividades");

    mod.controller('actividadNewCtrl', ['$scope', '$http', 'actividadesContext', '$state', 'recursosContext', '$rootScope',

        function ($scope, $http, actividadesContext, $state, recursosContext, $rootScope) {

            $rootScope.edit = false;

            $scope.createActividad = function () {

                $http.post(actividadesContext, {

                    tareaAsignada: $scope.tareaAsignada,
                    descripcion: $scope.descripcion

                }).then(function (response) {

                    $state.go('actividadesList', {actividadId: response.data.id}, {reload: true});

                });

            };
        }
  ]);
}
)(angular);

