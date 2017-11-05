(function (ng) {

    var mod = ng.module("PagosModule");

    mod.constant("pagosContex","api/pagoss");
    mod.controller("pagoUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagosContext',
        function ($scope, $state, $stateParams, $http, pagosContext) {
                            
               $scope.updatePagos = function(){
                   $http.put(pagosContext + '/' + $state.params.pagoId,{
                    estado: $scope.pagoEstado,
                    valor: $scope.pagoValor
                   }).then(function(response){
                       $state.go('pagosList',{pagosId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);

