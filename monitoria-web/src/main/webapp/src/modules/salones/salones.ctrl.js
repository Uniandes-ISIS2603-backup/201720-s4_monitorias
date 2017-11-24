(function (ng) {

    var mod = ng.module("salonesModules");

    mod.constant("salonesContext","salones");
    mod.constant("sedesContext", "api/sedes");
    
    mod.controller("salonesCtrl", ['$scope', '$state', '$stateParams', '$http', 'salonesContext','sedesContext'
        , function ($scope, $state, $stateParams, $http, salonesContext,sedesContext) 
        {
               
               $http.get(sedesContext + '/' + $state.params.sedeId + '/' + salonesContext).then(function(response)
               {
                   $scope.salonesRecords = response.data;
               });
               
               if($state.params.salonId !== undefined)
               {
                   $http.get(sedesContext + '/'+ $state.params.sedeId + '/' + salonesContext + '/' + $state.params.salonId).then(function (response)
                   {
                       $scope.currentSalon = response.data;
                   });
               }
        }
    ]);
})(angular);

