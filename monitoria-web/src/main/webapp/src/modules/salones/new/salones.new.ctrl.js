(function (ng) {

    var mod = ng.module("salonesModules");

    mod.constant("salonesContext","salones");
    mod.constant("sedesContext", "api/sedes");

    mod.controller("salonesNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'salonesContext','sedesContext'
        , function ($scope, $state, $stateParams, $http, salonesContext,sedesContext) 
        {            
            $scope.createSalon = function(){
                   $http.post(sedesContext + '/' + $state.params.sedeId + '/' + salonesContext,
                           {
                                nombre: $scope.salonName
                            }).then(function (response)
                            {
                                $state.go('salonesList',{salonId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
})(angular);

