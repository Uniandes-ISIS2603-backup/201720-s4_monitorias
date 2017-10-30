(function (ng) 
{
    var mod = ng.module("salonesModules");
    
    mod.constant("sedesContext", "api/sedes");
    mod.constant("salonesContext","salones");
    
    mod.controller('salonDeleteCtrl', ['$scope', '$http', 'sedesContext', '$state','salonesContext',
        function ($scope, $http, sedesContext,$state , salonesContext) 
        {
            var idSede = $state.params.sedeId;
            var idSalon = $state.params.salonId;
            $scope.deleteSalon = function () 
            {
                $http.delete(sedesContext + '/' + idSede + '/' + salonesContext + '/' + idSalon ,{}).then(function (response) 
                {
                    $state.go('salonesList', {salonId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);