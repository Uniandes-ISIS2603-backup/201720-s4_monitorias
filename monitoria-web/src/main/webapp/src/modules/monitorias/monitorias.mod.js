(function (ng) {
    
var mod = ng.module("MonitoriasModule", ['ui.router']);

      mod.constant("monitoriasContext", "api/monitorias");
    
     
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
          
            var basePath = 'src/modules/monitorias/';
            
            $urlRouterProvider.otherwise("/monitoriasList");
            $stateProvider.state('monitorias',{
             url:'/monitorias',
             abstract: true,
             views:{
                 'mainView':{
                     templateUrl: basePath + 'monitorias.html',
                     controller:'monitoriasCtrl',
                     controllerAs:'ctrl'
                 },
                 'listView': {
                        templateUrl: basePath + 'getMonitorias.html'         
                    }
             }
            }).state('monitoriasList', {
              
                url: '/list',
                parent:'monitorias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'getMonitorias.html'         
                    }
                }


            }).state('monitoriasCreate',{
                        url:'/create',
                        parent:'monitorias',
                     views:{
                         'detailView':{
                             templateUrl: basePath + 'create/createMonitoria.html',
                             controller: 'monitoriasCreateCtrl'
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
            .state('monitoriaDetail', {
                        url:'/{monitoriaId:int}/detail',
                        parent:'monitorias',
                        param:{
                            monitoriaId: null
                        },
                        views:{
                           
                            'detailView':{
                                templateUrl:basePath + 'monitorias.detail.html',
                                controller: 'monitoriasCtrl',
                                controlerAs:'ctrl'
                            }
                            
                            
                        }
            })
            
            
            ;
        }
    ]);

})(window.angular);