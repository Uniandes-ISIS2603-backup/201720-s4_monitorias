(function (ng) {

    var mod = ng.module("SedesModule");

    mod.constant("sedesContex","api/sedes");
    
    mod.controller("sedesCtrl", ['$scope', '$state', '$stateParams', '$http', 'sedesContext', function ($scope, $state, $stateParams, $http, sedesContext) {
               
               $http.get('data/sedes.json').then(function(response){
                   $scope.sedesRecords = response.data;
               });
               
               if($state.params.sedeName !== undefined){
                   $http.get(sedesContext + '/' + $state.params.bibliotecasName).then(function (response){
                       $scope.salonesRecords = response.data.salones;
                       $scope.currentSede = response.data;
                   });
               }
        }
    ]);
})(angular);

