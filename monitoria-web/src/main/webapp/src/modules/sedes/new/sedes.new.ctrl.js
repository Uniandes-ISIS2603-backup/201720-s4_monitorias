(function (ng) {

    var mod = ng.module("SedesModule");

    mod.constant("sedesContex","api/sedes");
    
    mod.controller("sedeNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'sedesContext', function ($scope, $state, $stateParams, $http, sedesContext) {
               
                 $scope.createSede = function()
                 {
                   $http.post(sedesContext,
                           {
                                name: $scope.sedeName
                                ,direccion: $scope.sedeDireccion
                            }).then(function (response)
                            {
                                $state.go('sedesList',{sedeId: response.data.id},{reload: true});
                            });
               };
        }
    ]);
}
)(angular);

