(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
    mod.constant("idiomasContext", "api/idiomas");
    mod.controller("monitoresUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext','idiomasContext',
        function ($scope, $state, $stateParams, $http, monitoresContext,idiomasContext) {
               $scope.idiomas = [];
               $scope.idiomasSeleccionados = [];
            $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
                console.log("hola mensaje en INTENTO ");
            });
                
            $scope.updateMonitor = function(){
                for(var i =0; i < $scope.idiomasSeleccionados.length;i++){
                        var id=$scope.idiomasSeleccionados[i];
                        $scope.idiomas.push({id});
                    }
                   $http.put(monitoresContext + '/' + $state.params.monitorCodigo,{                         
                    foto: $scope.monitorFoto,
                    nombre: $scope.monitorNombre,
                    tipo: $scope.tipoMonitor,
                    idiomas: $scope.idiomas
                   }).then(function(response){
                       $state.go('monitoresList',{monitorCodigo:response.data.codigo},{reload: true});
                   });
               };
             $scope.seleccionarI = function(idioma){
                    var eliminado = false;
                     console.log("hola mensaje en INTENTO ");
                    if($scope.idiomasSeleccionados.length != 0){
                     var idiomaBuscado;
                     for(var i =0; i < $scope.idiomasSeleccionados.length;i++){
                       idiomaBuscado = $scope.idiomasSeleccionados[i];
                          if(idiomaBuscado === idioma){
                               eliminado = true;
                                $scope.nuevosIdiomas = [];
                                 for(var j =0; j < $scope.idiomasSeleccionados.length;j++){
                                    if(j !== i){
                                      $scope.nuevosIdiomas.push($scope.idiomasSeleccionados[j]);
                                   }
                               }
                                 $scope.idiomasSeleccionados = $scope.nuevosIdiomas;
                           }
                       }
                   }
                   if(!eliminado){
                       $scope.idiomasSeleccionados.push(idioma);
                   }
                   console.log($scope.idiomasSeleccionados);
               };
           }
    ]);
}
)(angular);

