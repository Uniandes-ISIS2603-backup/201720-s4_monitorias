(function (ng) {
    var mod = ng.module("recursosModules");
    
    mod.constant("bibliotecasContext", "api/bibliotecas");
    mod.constant("recursosContext","recursos");
    
    mod.controller('recursoDeleteCtrl', ['$scope', '$http', 'bibliotecasContext', '$state','recursosContext',
        function ($scope, $http, bibliotecasContext,$state , recursosContext) {
            var idBiblioteca = $state.params.bibliotecaId;
            var idRecurso = $state.params.recursoId;
            $scope.deleteRecurso = function () {
                $http.delete(bibliotecasContext + '/' + idBiblioteca + '/' + recursosContext + '/' + idRecurso ,{}).then(function (response) {
                    $state.go('recursosList', {recursoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);