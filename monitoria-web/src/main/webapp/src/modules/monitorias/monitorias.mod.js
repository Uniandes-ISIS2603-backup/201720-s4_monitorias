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

            }).state('monitoriasCreate',{
                url:'/monitorias',
                views:{
                    'mainView':
                            {
                                templateUrl: basePath + '/create/createMonitoria.html',
                                controller: 'monitoriasCreateCtrl',
                                controllerAs: 'ctrl'
                            }
                }
            }).state('monitoriasUpdate',{
                url:'/monitorias/:idMonitoria',
                param:{idMonitoria:null},
                views:
                        {
                            'mainView':
                            {
                                templateUrl: basePath + '/update/updateMonitorias.html',
                                controller: 'monitoriasUpdateCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
            })
            ;
        }
    ]);

})(window.angular);