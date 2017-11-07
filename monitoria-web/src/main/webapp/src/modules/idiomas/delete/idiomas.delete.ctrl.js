(function (ng) {
    var mod = ng.module("IdiomasModule");
    mod.constant("idiomasContext", "api/idiomas");
    mod.controller('idiomaDeleteCtrl', ['$scope', '$http', 'idiomasContext', '$state',
        
        function ($scope, $http, idiomasContext, $state) {
            
            var idIdioma = $state.params.idiomaId;
            $scope.deleteIdioma = function () {
                $http.delete(idiomasContext + '/' + idIdioma, {}).then(function (response) {
                    $state.go('idiomasList', {idiomaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);