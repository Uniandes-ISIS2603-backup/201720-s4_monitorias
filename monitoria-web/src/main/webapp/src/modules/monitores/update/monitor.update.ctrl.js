(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
    mod.controller("monitoresUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext',
        function ($scope, $state, $stateParams, $http, monitoresContext) {
                            
               $scope.updateMonitor = function(){
                   $http.put(monitoresContext + '/' + $state.params.monitorCodigo,{
                    foto: $scope.monitorFoto,
                    nombre: $scope.monitorNombre,
                    tipo: $scope.tipoMonitor
                   }).then(function(response){
                       $state.go('monitoresList',{monitorCodigo:response.data.codigo},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);

