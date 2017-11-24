(function (ng) {
    //Definición del módulo
var mod = ng.module("SedesModule", ['ui.router']);

      mod.constant("sedesContext", "api/sedes");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/sedes/';
            //var basePathSalones = 'src/modules/salones';
           
            $urlRouterProvider.otherwise("/sedesList");
            
            $stateProvider.state('sedes',{
             url:'/sedes',
             abstract: true,
             views:{
                 'mainView':{
                     templateUrl: basePath + 'sedes.html',
                     controller:'sedesCtrl',
                     controllerAs:'ctrl'
                 }
             }
            }).state('sedesList', {
                //Url que aparecerá en el navegador
                url: '/list',
                parent:'sedes',
                views: {
                    'listView': 
                    {
                        templateUrl: basePath + 'sedes.list.html'         
                    }
                }

            }).state('sedesCreate',{
                        url:'/create',
                        parent:'sedes',
                     vierws:{
                         'detailView':{
                             templateUrl: basePath + 'new/sedes.new.html',
                             controller: 'sedeNewCtrl'
                         }

                     }
            }).state('sedeDetail', {
                        url:'/{sedeId:int}/detail',
                        parent:'sedes',
                        param:{
                            sedeId: null
                        },
                        views:
                        {
                            
                            'detailView':{
                                templateUrl:basePath + 'sedes.detail.html',
                                controller: 'sedesCtrl',
                                controlerAs:'ctrl'
                            }
                            
                        }
            }).state('sedeDelete', {
                        url:'/delete/{sedeId:int}',
                        parent: 'sedes',
                        param:{
                            sedeId: null
                        },
                        views:{
                            'detailView':
                            {
                                templateUrl: basePath + '/delete/sedes.delete.html',
                                controller: 'sedeDeleteCtrl'
                            }
                        }
            }).state('sedeUpdate',{
                url:'/{sedeId:int}/update'
                ,parent:'sedes'
                ,param:{
                    sedeId: null
                }
                ,views:{
                    'listView':{
                        templateUrl: basePath + 'update/sedes.update.html'
                        ,controller:'sedesUpdateCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

