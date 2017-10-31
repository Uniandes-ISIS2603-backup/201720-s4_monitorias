(function (ng) {

    var mod = ng.module("BibliotecasModule");

    mod.constant("bibliotecasContex","api/bibliotecas");
    
    mod.controller("bibliotecaNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'bibliotecasContext', function ($scope, $state, $stateParams, $http, bibliotecasContext) {
               
                 $scope.createBiblioteca = function(){
                   $http.post(bibliotecasContext,
                           {
                                name: $scope.bibliotecaName
                                ,ubicacion: $scope.bibliotecaUbicacion
                            }).then(function (response){
                                //autor create successfully
                                $state.go('bibliotecasList',{bibliotecaId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
}
)(angular);

