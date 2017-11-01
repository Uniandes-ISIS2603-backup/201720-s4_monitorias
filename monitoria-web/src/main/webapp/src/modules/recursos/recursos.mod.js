(function (ng) {
    //Definición del módulo
var mod = ng.module("recursosModules", ['BibliotecasModule','ui.router']);

    mod.constant("recursosContext","recursos");
    mod.constant("bibliotecasContext", "api/bibliotecas");
     
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/recursos/';
           
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/recursosList");
            
            $stateProvider.state('recursos',{
             url:'/recursos',
             abstract: true,
             parent: 'bibliotecaDetail',
             views:{
                 childrenView:{
                     templateUrl: basePath + 'recursos.html'
                 }
             }
            }).state('recursosList', {
                //Url que aparecerá en el navegador
                url: '/{bibliotecaId:int}/list',
                parent:'recursos',
                param:{
                    bibliotecaId: null
                },
                views: {
                    listView: {
                        templateUrl: basePath + 'recursos.list.html',
                        controller: 'recursosCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('recursoDelete',{
                        url: '{bibliotecaId:int}/delete/{recursoId:int}',
                        parent: 'recursos',
                        param:{
                            bibliotecaId: null,
                            recursoId:null
                        },
                        views:{
                            listView:{
                                templateUrl: basePath + 'delete/recursos.delete.html',
                                controller:'recursoDeleteCtrl'
                            }
                        }
            }).state('recursoCreate',{
                url:'/{bibliotecaId:int}/create',
                parent:'recursos'
                ,param:{
                    bibliotecaId:null
                }
                ,views:{
                    'detailView':{
                        templateUrl: basePath + 'new/recursos.new.html'
                        ,controller: 'recursosNewCtrl'
                    }
                }
            }).state('recursoUpdate',{
                url:'/{bibliotecaId:int}/update/{recursoId:int}',
                parent:'recursos'
                ,param:{
                    bibliotecaId:null
                    ,recursoId:null
                }
                ,views:{
                    'detailView':{
                        templateUrl: basePath + 'update/recursos.update.html'
                        ,controller: 'recursosUpdateCtrl'
                    }
                }
            });
        }
    ]);

})(window.angular);

