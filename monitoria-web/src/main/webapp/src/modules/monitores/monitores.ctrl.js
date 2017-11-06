(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
    
    mod.controller("monitoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext', function ($scope, $state, $stateParams, $http, context) {
               var listMonitores={};
               
                $http.get(context).then(function (response) {
                    
                     listMonitores=response.data;
                    
                    
                $scope.monitoresRecords = listMonitores;
            });
        }
    ]);
})(window.angular);

