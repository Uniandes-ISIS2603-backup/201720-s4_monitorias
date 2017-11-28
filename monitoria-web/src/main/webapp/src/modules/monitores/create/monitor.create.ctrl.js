(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
     mod.constant("idiomasContext", "api/idiomas");
    mod.controller("monitoresCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext','idiomasContext',
        function ($scope, $state, $stateParams, $http, monitoresContext,idiomasContext) {
               $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
                $scope.idiomasSeleccionados = [];
            });             
               $scope.createMonitor = function(){
                   $http.post(monitoresContext,{
                    foto: $scope.monitorFoto,
                    nombre: $scope.monitorNombre,
                    codigo: $scope.monitorCodigo,
                    tipo: $scope.tipoMonitor,
                    idiomas: $scope.idiomasSeleccionados
                   }).then(function(response){
                       $state.go('monitoresList',{monitorCodigo:response.data.codigo},{reload: true});
                   });
               };
               /**
                * Método para la selección de idiomas de un monitor
                * @param {type} idioma idioma que se quiere seleccionar o deseleccionar
                * @returns {undefined} void
                */
               $scope.seleccionarIdioma = function(idioma){
                   //Variable que indica si se eliminó el idioma. Cuando llega un idioma por parametro puede ser para eliminar o para agregar
                   var eliminado = false;
                   //Se verifica si el tamaño de la lista de seleccionados es diferente de 0
                   if($scope.idiomasSeleccionados.length != 0){
                       //Varaible auxiliar para guardar un idioma en la lista de ya seleccionados
                       var idiomaBuscado;
                       //Se hace un for para buscar en la lista de seleccionados
                       for(var i =0; i < $scope.idiomasSeleccionados.length;i++){
                           //Se asigna el i-esimo idioma seleccionado a la variable auxiliar
                           idiomaBuscado = $scope.idiomasSeleccionados[i];
                           //se compara para saber si el idioma que llegó por parametro es igual a el que se sacó de la lista de idiomas
                           if(idiomaBuscado === idioma){
                               //sí son iguales, se hace eliminado = true
                               eliminado = true;
                               //se crea una nueva lista auxiliar
                                $scope.nuevosIdiomas = [];
                                //Se hace un for para agregar todos los idiomas seleccionados a la nueva lista auxiliar menos el i-esimo, el cual es el que se quiere deseleccionar
                               for(var j =0; j < $scope.idiomasSeleccionados.length;j++){
                                   //Se verifica que se metan todos los idiomas menos el i-esimo
                                   if(j !== i){
                                       //se inserta el idioma en la posición j-esima
                                       $scope.nuevosIdiomas.push($scope.idiomasSeleccionados[j]);
                                   }
                               }
                               //Se hace idioma seleccionados = al 
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

