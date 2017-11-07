(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
    mod.constant("idiomasContext", "api/idiomas");
    mod.controller("monitoresUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext','idiomasContext',
        function ($scope, $state, $stateParams, $http, monitoresContext,idiomasContext) {
               var listIdiomasMonitor=[];             
            $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
            });   
            
            
            $scope.updateMonitor = function(){
                listIdiomasMonitor=document.getElementsByName('idiomasMonitor');
                   $http.put(monitoresContext + '/' + $state.params.monitorCodigo,{                         
                    foto: $scope.monitorFoto,
                    nombre: $scope.monitorNombre,
                    tipo: $scope.tipoMonitor                    
                   }).then(function(response){
                       $state.go('monitoresList',{monitorCodigo:response.data.codigo},{reload: true});
                   });
               };
               
                $scope.selecionado = function(){
                
               };
        }
    ]);
}
)(angular);

