(function (ng) {

    var mod = ng.module("salonesModules");

    mod.constant("salonesContext","salones");
    mod.constant("sedesContext", "api/sedes");
    
    mod.controller("salonesCtrl", ['$scope', '$state', '$stateParams', '$http', 'salonesContext','sedesContext'
        , function ($scope, $state, $stateParams, $http, salonesContext,sedesContext) 
        {
            
            
            $scope.buscarSalon = function ()
            {
                  $scope.salonesRecords = $scope.salonesRecords.filter(function(salon){
                       
                    return salon.nombre.match("(.*)" + $scope.salonNameBuscada +"(.*)");
                   });
            };
               
               
               $http.get(sedesContext + '/' + $state.params.sedeId + '/' + salonesContext).then(function(response)
               {
                   $scope.salonesRecords = response.data;
               });
               
               if($state.params.salonId !== undefined)
               {
                   $http.get(sedesContext + '/'+ $state.params.sedeId + '/' + salonesContext + '/' + $state.params.salonId).then(function (response)
                   {
                       $scope.currentSalon = response.data;
                       $scope.sedeName = response.data.sede.name;
                   });
               }
        }
    ]);
})(angular);

