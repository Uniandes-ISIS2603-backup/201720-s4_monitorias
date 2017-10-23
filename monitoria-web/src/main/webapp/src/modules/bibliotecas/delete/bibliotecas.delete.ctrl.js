(function (ng) {
    var mod = ng.module("BibliotecasModule");
    mod.constant("bibliotecasContext", "api/bibliotecas");
    mod.controller('bibliotecaDeleteCtrl', ['$scope', '$http', 'bibliotecasContext', '$state',
        function ($scope, $http, bibliotecasContext, $state) {
            var idBiblioteca = $state.params.bibliotecaId;
            $scope.deleteBiblioteca = function () {
                $http.delete(bibliotecasContext + '/' + idBiblioteca, {}).then(function (response) {
                    $state.go('bibliotecasList', {bibliotecaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);