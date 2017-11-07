(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
    mod.controller("monitoresCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext',
        function ($scope, $state, $stateParams, $http, monitoresContext) {
                            
               $scope.createMonitor = function(){
                   $http.post(monitoresContext,{
                    foto: $scope.monitorFoto,
                    nombre: $scope.monitorNombre,
                    codigo: $scope.monitorCodigo,
                    tipo: $scope.tipoMonitor
                   }).then(function(response){
                       $state.go('monitoresList',{monitorCodigo:response.data.codigo},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);

