(function (ng) 
{
    var mod = ng.module("SedesModule");
    mod.constant("sedesContext", "api/sedes");
    mod.controller('sedeDeleteCtrl', ['$scope', '$http', 'sedesContext', '$state',
        function ($scope, $http, sedesContext, $state) 
        {
            var idSede = $state.params.sedeId;
            $scope.deleteSede = function () 
            {
                $http.delete(sedesContext + '/' + idSede, {}).then(function (response) 
                {
                    $state.go('sedesList', {sedeId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);