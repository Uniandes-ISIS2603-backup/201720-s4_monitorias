/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("ValoracionesModule");

    mod.constant("monitoriasContext","api/valoraciones");
    
    mod.controller("valoracionesCtrl", ['$scope', '$state', '$stateParams', '$http', 'valoracionesContext', function ($scope, $state, $stateParams, $http, context) {
               
                $http.get(context).then(function (response) {
                $scope.records = response.data;
            });
        }
    ]);
})(window.angular);



