(function (ng) {
    //Definición del módulo
var mod = ng.module("BibliotecasModule", ['ui.router']);

      mod.constant("bibliotecasContext", "api/bibliotecas");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/bibliotecas/';
           
           
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/bibliotecasList");
            $stateProvider.state('bibliotecas',{
             url:'/bibliotecas',
             abstract: true,
             views:{
                 'mainView':{
                     templateUrl: basePath + 'bibliotecas.html',
                     controller:'bibliotecasCtrl',
                     controllerAs:'ctrl'
                 }
             }
            }).state('bibliotecasList', {
                //Url que aparecerá en el navegador
                url: '/list',
                parent:'bibliotecas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'bibliotecas.list.html'         
                    }
                }

            }).state('bibliotecasCreate',{
                        url:'/create',
                        parent:'bibliotecas',
                     views:{
                         'detailView':{
                             templateUrl: basePath + 'new/bibliotecas.new.html',
                             controller: 'bibliotecaNewCtrl'
                         }

                     }
            }).state('bibliotecaDetail', {
                        url:'/{bibliotecaId:int}/detail',
                        parent:'bibliotecas',
                        param:{
                            bibliotecaId: null
                        },
                        views:{
                            'detailView':{
                                templateUrl:basePath + 'bibliotecas.detail.html',
                                controller: 'bibliotecasCtrl',
                                controlerAs:'ctrl'
                            }  
                        }
            })
                    .state('bibliotecaDelete', {
                        url:'/delete/{bibliotecaId:int}',
                        parent: 'bibliotecas',
                        param:{
                            bibliotecaId: null
                        },
                        views:{
                            'detailView':{
                                templateUrl: basePath + '/delete/bibliotecas.delete.html',
                                controller: 'bibliotecaDeleteCtrl'
                            }
                        }
            }).state('bibliotecaBuscar',{
                url:'/find/{bibliotecaName: String}',
                parent:'bibliotecas',
                param:{
                    bibliotecaName: null
                },
                views:{
                    'dateilView':{
                        templateUrl: basePath +'bibliotecas.list.html'
                    }
                }
            });
        }
    ]);

}
)(window.angular);

