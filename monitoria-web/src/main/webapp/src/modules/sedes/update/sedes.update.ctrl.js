(function (ng) {

    var mod = ng.module("SedesModule");

    mod.constant("sedesContex","api/sedes");
    mod.constant("salonesContext","salones");
    mod.controller("sedesUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'sedesContext','salonesContext',
        function ($scope, $state, $stateParams, $http, sedesContext,salonesContext) {
              
               $http.get(sedesContext+ '/' + $state.params.sedeId ).then(function(response){
                   var sede = response.data;
                   $scope.sedeName = sede.name;
                   $scope.sedeDireccion = sede.Direccion;
                   
               });
               
               $http.get(sedesContext+ '/' + $state.params.sedesId +'/'+salonesContext).then(function(response){
                   $scope.sedeSalones = response.data;
                   
               });
                
               $scope.updateSede = function(){
                   $http.put(sedesContext + '/' + $state.params.sedeId,{
                       name: $scope.sedeName
                       ,direccion: $scope.sedeDireccion
                       ,salones: $scope.sedeSalones
                   }).then(function(response){
                       $state.go('sedesList',{sedeId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);
