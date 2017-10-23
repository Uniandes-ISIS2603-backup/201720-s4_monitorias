(function (ng) {
    //Definición del módulo
var mod = ng.module("IdiomasModule", ['ui.router']);

      mod.constant("idiomasContext", "api/idiomas");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
          
            var basePath = 'src/modules/idiomas/';
            var basePathRecursos = 'src/modules/recursos';
           
            $urlRouterProvider.otherwise("/idiomasList");
            $stateProvider.state('idiomas',{
             url:'/idiomas',
             abstract: true,
             views:{
                 'mainView':{
                     templateUrl: basePath + 'idiomas.html',
                     controller:'idiomasCtrl',
                     controllerAs:'ctrl'
                 }
             }
            }).state('idiomasList', {
              
                url: '/list',
                parent:'idiomas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'idiomas.list.html'         
                    }
                }

            }).state('idiomasCreate',{
                        url:'/create',
                        parent:'idiomas',
                     vierws:{
                         'detailView':{
                             templateUrl: basePath + '/new/idiomas.new.html',
                             controller: 'idiomasNewCtrl'
                         }

                     }
            }).state('idiomaDetail', {
                        url:'/{idiomaIdioma:String}/detail',
                        parent:'idiomas',
                        param:{
                            idiomaIdioma: null
                        },
                        views:{
                            'listView':{
                                templateUrl:basePathRecursos + 'recursos.list.html',
                                controller: 'idiomasCtrl',
                                controlerAs:'ctrl'
                            },
                            'detailView':{
                                templateUrl:basePath + 'idiomas.detail.html',
                                controller: 'idiomasCtrl',
                                controlerAs:'ctrl'
                            }
                            
                        }
            });
        }
    ]);

})(window.angular);