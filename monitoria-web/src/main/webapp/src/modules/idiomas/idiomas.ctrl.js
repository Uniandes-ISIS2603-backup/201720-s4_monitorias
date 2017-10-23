/*(function (ng) {

    var mod = ng.module("IdiomasModule");

    mod.constant("idiomasContex","api/idiomas");
    
    mod.controller("idiomasCtrl", ['$scope', '$state', '$stateParams', '$http', 'idiomasContext', function ($scope, $state, $stateParams, $http, idiomasContext) {
               
               $http.get('data/idiomas.json').then(function(response){
                   $scope.idiomasRecords = response.data;
               });
               
               if($state.params.idiomaIdioma !== undefined){
                   $http.get(idiomasContext + '/' + $state.params.idiomasIdioma).then(function (response){
                       $scope.recursosRecords = response.data.recursos;
                       $scope.currentIdioma = response.data;
                   });
               }
        }
    ]);
})(angular);
*/
(function (ng) {
    var mod = ng.module("IdiomasModule");
    mod.constant("idiomasContext", "api/idiomas");
    mod.controller('idiomasCtrl', ['$scope', '$http', 'idiomasContext', '$state',
        function ($scope, $http, idiomasContext, $state) {
            $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
            });

            if ($state.params.IdiomaId !== undefined) {
                $http.get(idiomasContext + '/' + $state.params.idiomasId).then(function (response) {
                    $scope.recursosRecords = response.data.recursos;
                    $scope.currentIdioma = response.data;
                });
            }
        }
    ]);
}
)(angular);


