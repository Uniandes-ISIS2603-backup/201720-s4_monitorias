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
            }).state('escogerMonitoria',{
                url:'/monitorias/{idEstudiante:int}',
                param:{idEstudiante:null},
                views:
                        {
                            'mainView':
                            {
                                templateUrl: basePath + '/update/updateMonitorias.html',
                                controller: 'monitoriasUpdateCtrl',
                                controllerAs: 'ctrl'
                            }
                        }
            }).state('asignarEstudiante',{
                url:'/monitorias/{idEstudiante:int}/{idMonitoria:int}',
                param:{idEstudiante:null,idMonitoria:null},
                views:
                        {
                            'mainView':{
                                templateUrl:basePath + 'update/'+'asignarEstudiante.html',
                                controller: 'monitoriasAsignarEstudianteCtrl',
                                controlerAs:'ctrl'
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
            }).state('monitorMonitoriaUpdate',{
                url:'/{idMonitor}/{monitoriaId}/monitor',
                views:
                        {
                           'mainView':{
                                templateUrl:basePath + 'update/'+'monitorias.cambiarEstado.html',
                                controller: 'cambiarEstadoCtrl',
                                controlerAs:'ctrl'
                           }
                        }
            })
            
            
            ;
        }
    ]);

})(window.angular);