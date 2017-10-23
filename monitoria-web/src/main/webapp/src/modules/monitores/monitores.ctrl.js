(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
    
    mod.controller("monitoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext', function ($scope, $state, $stateParams, $http, context) {
               
                $http.get(context).then(function (response) {
                $scope.records = response.data;
            });
        }
    ]);
})(window.angular);

