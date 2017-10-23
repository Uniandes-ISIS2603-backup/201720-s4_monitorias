(function (ng) {
    //Definición del módulo
var mod = ng.module("recursoModule", ['BibliotecasModule','ui.router']);

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
                url: '/list',
                parent:'recursos',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'recursos.list.html',
                        controller: 'recursosCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('recursosCreate',{
                        url:'/create',
                        parent:'bibliotecas',
                     vierws:{
                         'detailView':{
                             templateUrl: basePath + '/new/bibliotecas.create.html',
                             controller: 'bibliotecaNewCtrl'
                         }

                     }
            }).state('recursosDetail', {
                        url:'/{bibliotecaName:String}/detail',
                        parent:'bibliotecas',
                        param:{
                            bibliotecaName: null
                        },
                        views:{
                            'listView':{
                                templateUrl:basePathRecursos + 'recursos.list.html',
                                controller: 'bibliotecasCtrl',
                                controlerAs:'ctrl'
                            },
                            'detailView':{
                                templateUrl:basePath + 'bibliotecas.detail.html',
                                controller: 'bibliotecasCtrl',
                                controlerAs:'ctrl'
                            }
                            
                        }
            });
        }
    ]);

})(window.angular);

