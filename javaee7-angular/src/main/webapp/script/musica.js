var app = angular.module('musicas', ['ngResource', 'ngGrid', 'ui.bootstrap']);

// Cria um controlador com nome das musicas Lista Controller para ligar para a seção de grade.
app.controller('musicasListController', function ($scope, $rootScope, musicaService) {
    // Inicializar informações necessárias: a triagem, a primeira página para mostrar e as opções de grade.
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.musicas = {paginaAtual: 1};

    $scope.gridOptions = {
        data: 'musicas.lista',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'nome', displayName: 'Nome' },
            { field: 'estilo', displayName: 'Estilo' },
            { field: 'artista', displayName: 'Artista' },
            { field: '', width: 26, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }
        ],

        multiSelect: false,
        selectedItems: [],
        // Transmite um evento quando uma linha é selecionada, para sinalizar a forma que ele precisa para carregar os dados da linha.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('musicaSelecionado', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    // Atualiza a grade, chamando o método rest adequado.
    $scope.refreshGrid = function () {
        var listMusicasArgs = {
            page: $scope.musicas.paginaAtual,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        musicaService.get(listMusicasArgs, function (data) {
            $scope.musicas = data;
        })
    };
    // Transmitir um evento quando um elemento na grade é excluído. Nenhuma deleção é realizada neste ponto.
    $scope.deleteRow = function (row) {
        $rootScope.$broadcast('apagaMusica', row.entity.id);
    };

    // Verifica a variável SortInfo. Se forem detectadas alterações precisamos atualizar a grade.
    // Isso também funciona para o primeiro acesso, uma vez que nós atribuímos a triagem inicial na seção initialize.
    $scope.$watch('sortInfo.fields[0]', function () {
        $scope.refreshGrid();
    }, true);

    // Faz alguma coisa quando a grade é alterada.
    // A grade lança a ngGridEventSorted que se apanhada aqui e atribui o SortInfo ao escopo.
    // Isso permitirá verificar ao SortInfo a mudança e atualizar a grade.
    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });

    // Pega o evento transmitido quando uma musica é salva ou excluída para atualizar os elementos de grade com as informações mais atualizadas.
    $scope.$on('refreshGrid', function () {
        $scope.refreshGrid();
    });

    // Pega o evento transmitido quando o formulário é liberado para também limpar a seleção de grade.
    $scope.$on('clear', function () {
        $scope.gridOptions.selectAll(false);
    });
});

// Cria um controlador com musicas nome a partir do controlador para ligar para a seção forma.
app.controller('musicasFormController', function ($scope, $rootScope, musicaService) {
    // Limpa o formulário. Clicando no botão "Limpar" ou quando um teste bem-sucedido é realizada.
    $scope.clearForm = function () {
        $scope.musica = null;
        // apaga os campos do 'url' se o valor for inválido.
        
        document.getElementById('fotoUrl').value = null;
        // reseta a validacao do formulario.
        $scope.musicaForm.$setPristine();
        // transmite o evento e limpa a grade.
        $rootScope.$broadcast('clear');
    };


    var demoApp = angular.module('demoApp', []);
    demoApp.controller('SimpleContr',SimpleContr);

    function SimpleContr($scope) {
    $scope.musicas = [
        {nome:'musica.nome',artista:'Gilberto Gil'},
        {nome:'Aquarela do Brasil',artista:'Gal Costa'},
        {nome:'Brasileirinho',artista:'Dorival'}           
    ];
    }
    
    // chama o metodo rest para salvar uma pesssoa.
    $scope.atualizaMusica = function () {
        musicaService.save($scope.musica).$promise.then(
            function () {
                // 	Transmite o evento para atualizar a grade.
                $rootScope.$broadcast('refreshGrid');
                // Transmite o evento para exibir uma mensagem salva.
                $rootScope.$broadcast('musicaSalvo');
                $scope.clearForm();
            },
            function () {
                // Transmite o evento para exibir um erro
                $rootScope.$broadcast('erro!');
            });
    };

    // Evento transmitido quando a musica é selecionado na grade para realizar a carga das musicas e chamar o serviço.
    $scope.$on('musicaSelecionado', function (event, id) {
        $scope.musica = musicaService.get({id: id});
    });

    // Evento transmitido quando a musica é excluída, executa a  exclusao chamando o serviço rest.
    $scope.$on('apagaMusica', function (event, id) {
    	musicaService.deletes({id: id}).$promise.then(						
            function () {
                //  refresh the grid.
                $rootScope.$broadcast('refreshGrid');
                // display a delete message.
                $rootScope.$broadcast('musicaApagado');
                $scope.clearForm();
            },
            function () {
                //  server error.
                $rootScope.$broadcast('error');
            });
    });
});

// Cria um controlador com o nome alertMessagesController para ligar a seção de mensagens.
app.controller('alertMessagesController', function ($scope) {
    // Evento para exibir uma mensagem salva.
    $scope.$on('musicaSalvo', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Musica salva com sucesso!' }
        ];
    });

    // evento para exibir uma mensagem de delecao.
    $scope.$on('musicaApagado', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Musica apagada com sucesso!' }
        ];
    });

    // evento para exibir uma mensagem de erro do servidor.
    $scope.$on('error', function () {
        $scope.alerts = [
            { type: 'danger', msg: 'Houve um problema na conexao, tente novamente!' }
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
});

// serviços
app.factory('musicaService', function ($resource) {
    return $resource('resources/musicas/:id');
});

angular.module('myReverseFilterApp', []).filter('reverse', function() {
  return function(input, uppercase) {
    input = input || '';
    var out = "";
    for (var i = 0; i < input.length; i++) {
      out = input.charAt(i) + out;
    }
    // condicional
    if (uppercase) {
      out = out.toUpperCase();
    }
    return out;
  };
})
.controller('MyController', ['$scope', function($scope) {
  $scope.greeting = 'hello';
}]);
