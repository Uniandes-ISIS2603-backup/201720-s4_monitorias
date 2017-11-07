(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
     mod.constant("idiomasContext", "api/idiomas");
    mod.controller("monitoresCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext','idiomasContext',
        function ($scope, $state, $stateParams, $http, monitoresContext,idiomasContext) {
               $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
            });             
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

