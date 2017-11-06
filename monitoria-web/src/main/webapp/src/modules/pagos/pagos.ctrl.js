(function (ng) {

    var mod = ng.module("PagosModule");

    mod.constant("pagosContext","api/pagos");
    
    mod.controller("pagosCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagosContext', function ($scope, $state, $stateParams, $http, context) {
               var listPagos={};
               
               function Estado(item){
                            if(item.estado===false)
                            item.estado="Por pagar";
                        else
                        item.estado="Pagado";
                        }
               
                $http.get(context).then(function (response) {
                    listPagos=response.data;
                    listPagos.forEach(Estado);
                        
                  $scope.pagosRecords = listPagos;
            }); 
            
           
        }
    ]);
}
        
        
        
        
        )(window.angular);

