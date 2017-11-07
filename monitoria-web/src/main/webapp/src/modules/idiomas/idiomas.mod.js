(function (ng) {
    //Definición del módulo
var mod = ng.module("IdiomasModule", ['ui.router']);

      mod.constant("idiomasContext", "api/idiomas");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
          
            var basePath = 'src/modules/idiomas/';
            
           
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
                     views:{
                         'detailView':{
                             templateUrl: basePath + 'new/idiomas.new.html',
                             controller: 'idiomaNewCtrl'
                         }

                     }
            }).state('idiomaDetail', {
                        url:'/{idiomaId:int}/detail',
                        parent:'idiomas',
                        param:{
                            idiomaId: null
                        },
                        views:{
                           
                            'detailView':{
                                templateUrl:basePath + 'idiomas.detail.html',
                                controller: 'idiomasCtrl',
                                controlerAs:'ctrl'
                            },
                            'listView':{
                                
                            }
                            
                        }
            })
            .state('idiomaUpdate',{
                url:'/{idiomaId:int}/update'
                ,parent:'idiomas'
                ,param:{idiomaId: null}
                ,views:{
                    'listView':{
                        templateUrl: basePath + 'update/idiomas.update.html'
                        ,controller:'idiomasUpdateCtrl'
                    }
                }
            })
              .state('idiomasDelete', {
                url:'/delete/{idiomaId:int}',
                parent: 'idiomas',
                params:{
                    idiomasId: null
                },
                views:{
                    detailView:{
                        templateUrl: basePath + '/delete/idiomas.delete.html',
                        controller:'idiomaDeleteCtrl'
                    }
                }
            })
        
    
        }
    ]);

})(window.angular);