(function (ng) {
    //Definición del módulo
var mod = ng.module("ActividadesModule", ['MonitoriasModule','ui.router']);

      mod.constant("actividadesContext", "actividades");
      mod.constant("monitoriasContext", "api/monitorias");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
          
            var basePath = 'src/modules/actividades/';
          
           
            $urlRouterProvider.otherwise("/actividadesList");
            $stateProvider.state('actividades',{
             url:'/actividades',
             abstract: true,
             parent: 'monitoriaDetail',
             views:{
                 childrenView:{
                     templateUrl: basePath + 'actividades.html'
                 }
             }
            }).state('actividadesList', {
              
                url: '/{monitoriaId:int}/list',
                parent:'actividades',
                param:{
                    monitoriaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'actividades.list.html' ,   
                        controller: 'actividadesCtrl',
                        controllerAs: 'ctrl'
                    }  
                }

            }).state('actividadCreate',{
                url:'/{monitoriaId:int}/create',
                parent:'actividades'
                ,param:{
                    monitoriaId:null
                }
                ,views:{
                    'detailView':{
                        templateUrl: basePath + 'new/actividades.new.html'
                        ,controller: 'actividadesNewCtrl'
                    }
                }
            }).state('actividadUpdate',{
                url:'/{monitoriaId:int}/update/{actividadId:int}',
                parent:'actividades'
                ,param:{
                    monitoriaId:null
                    ,actividadId:null
                }
                ,views:{
                    'detailView':{
                        templateUrl: basePath + 'update/actividades.update.html'
                        ,controller: 'actividadesUpdateCtrl'
                    }
                }
            })
            .state('actividadDelete',{
                        url: '{monitoriaId:int}/delete/{actividadId:int}',
                        parent: 'actividades',
                        param:{
                            monitoriaId: null,
                            actividadId:null
                        },
                        views:{
                            listView:{
                                templateUrl: basePath + 'delete/actividades.delete.html',
                                controller:'actividadDeleteCtrl'
                            }
                        }
            });
//            }).state('actividadDetail', {
//                        url:'/{tareaAsignadaActividad:String}/detail',
//                        parent:'actividades',
//                        param:{
//                            tareaAsignadaActividad: null
//                        },
//                        views:{
//                            'listView':{
//                                templateUrl:basePathRecursos + 'recursos.list.html',
//                                controller: 'actividadesCtrl',
//                                controlerAs:'ctrl'
//                            },
//                            'detailView':{
//                                templateUrl:basePath + 'actividades.detail.html',
//                                controller: 'actividadesCtrl',
//                                controlerAs:'ctrl'
//                            }
//                            
//                        }
//            });

        }
    ]);

})(window.angular);