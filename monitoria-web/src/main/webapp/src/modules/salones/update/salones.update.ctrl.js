(function (ng) {

    var mod = ng.module("salonesModules");

    mod.constant("salonesContext","salones");
    mod.constant("sedesContext", "api/sedes");

    mod.controller("salonesUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'salonesContext','sedesContext'
        , function ($scope, $state, $stateParams, $http, salonesContext,sedesContext) 
        {             
            
            if($state.params.salonId !== undefined)
            {
                   $http.get(sedesContext +'/'+ $state.params.sedeId + '/' + salonesContext + '/' + $state.params.salonId).then(function (response)
                   {
                       var salon = response.data;
                       $scope.salonName = salon.nombre;
                   });
            }
            
            
            $scope.updateSalon = function(){
                   $http.put(sedesContext + '/' + $state.params.sedeId + '/' + salonesContext +'/'+ $state.params.salonId,
                           {
                                nombre: $scope.salonName
      
                            }).then(function (response)
                            {
                                $state.go('salonesList',{salonId: response.data.id},{reload: true});
                            });
               };
        }
    ]);
})(window.angular);

