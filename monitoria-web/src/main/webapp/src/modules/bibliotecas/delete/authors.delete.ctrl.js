(function (ng) {
    var mod = ng.module("bibliotecaModule");
    mod.constant("bibliotecasContext", "api/bibliotecas");
    mod.controller('bibliotecaDeleteCtrl', ['$scope', '$http', 'bibliotecasContext', '$state',
        function ($scope, $http, bibliotecasContext, $state) {
            var idAuthor = $state.params.bibliotecaId;
            $scope.deleteAuthor = function () {
                $http.delete(authorsContext + '/' + idAuthor, {}).then(function (response) {
                    $state.go('authorsList', {authorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);