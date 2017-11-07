(function (ng) {

    var mod = ng.module("SedesModule");

    mod.constant("sedesContex","api/sedes");
    
    mod.controller("sedesCtrl", ['$scope', '$state', '$stateParams', '$http', 'sedesContext', function ($scope, $state, $stateParams, $http, sedesContext) 
        {
            
            $scope.buscarSede = function ()
            {
                  $scope.sedesRecords = $scope.sedesRecords.filter(function(sede){
                       
                    return sede.name.match("(.*)" + $scope.sedeNameBuscada +"(.*)");
                   });
            };
            
               
               $http.get(sedesContext).then(function(response)
               {
                   $scope.sedesRecords = response.data;
               });
               
               if($state.params.sedeId !== undefined)
               {
                   $http.get(sedesContext + '/' + $state.params.sedeId).then(function (response)
                   {
                       $scope.salonesRecords = response.data.salones;
                       $scope.varSedeId = $state.params.sedeId;
                       $scope.currentSede = response.data;

                   });
               }
        }
    ]);
})(angular);
