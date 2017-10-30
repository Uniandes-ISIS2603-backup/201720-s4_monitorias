(function (ng) {

    var mod = ng.module("salonesModules");

    mod.constant("salonesContext","salones");
    mod.constant("sedeContext", "api/sedes");
    
    mod.controller("salonesCtrl", ['$scope', '$state', '$stateParams', '$http', 'salonesContext','sedesContext'
        , function ($scope, $state, $stateParams, $http, salonesContext,sedesContext) 
        {
               
               $http.get(sedesContext + '/' + $state.params.sedeId + '/' + salonesContext).then(function(response)
               {
                   $scope.salonesRecords = response.data;
               });
               
               if($state.params.salonesId !== undefined)
               {
                   $http.get(salonesContext + '/' + $state.params.salonesId).then(function (response)
                   {
                       $scope.salonesRecords = response.data.salones;
                       $scope.currentSede = response.data;
                   });
               }
        }
    ]);
})(angular);

