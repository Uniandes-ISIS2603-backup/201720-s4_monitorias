(function (ng) {
    
var mod = ng.module("MonitoriasModule", ['ui.router']);

      mod.constant("monitoriasContext", "api/monitorias");
    
     
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
          
            var basePath = 'src/modules/monitorias/';
            
            $urlRouterProvider.otherwise("/monitorias");
            
            $stateProvider.state('monitoriasList', {
                url: '/monitorias',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'getMonitorias.html',
                        controller: 'monitoriasCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });
        }
    ]);

})(window.angular);