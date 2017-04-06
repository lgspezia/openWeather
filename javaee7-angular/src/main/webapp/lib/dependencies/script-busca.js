// Definindo um novo módulo para nossa aplicação
//var app = angular.module ("instantSearch", []);
// Crie um filtro de pesquisa instantânea

//
var app = angular.module('instantSearch', ['ngResource', 'ngGrid', 'ui.bootstrap']);

//Cria um controlador com nome das musicas Lista Controller para ligar para a seção de grade.
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

 // Verifica a variável SortInfo. Se forem detectadas alterações precisamos atualizar a grade.
 // Isso também funciona para o primeiro acesso, uma vez que nós atribuímos a triagem inicial na seção initialize.
 $scope.$watch('sortInfo.fields[0]', function () {
     $scope.refreshGrid();
 }, true);

 // Pega o evento transmitido quando o formulário é liberado para também limpar a seleção de grade.
 $scope.$on('clear', function () {
     $scope.gridOptions.selectAll(false);
 });
});

//

app.filter ('searchFor', function () {

	// Todos os filtros devem retornar uma função. O primeiro 
	// parâmetro é um dado que será filtrado, e o segundo é um 
	// argumento que vai ser passado com dois pontos
	// searchFor: searchString

	return function (arr, searchString) {

		if (!searchString) {
			return arr;
		}

		var result = [];

		searchString = searchString.toLowerCase();

		// Usando o útil método forEach para iterar através do array
		angular.forEach (arr, function (item) {

			if (item.title.toLowerCase().indexOf(searchString) !== -1) {
				result.push(item);
			}
		});

		return result;
	};

});


// O Controlador

function InstantSearchController ($scope) {

	// O modelo de dados. Estes itens normalmente são requisitados via Ajax,
	// mas aqui estão simplificados. Veja o próximo exemplo para 
	// dicas usando Ajax.
	
	 $scope.items = [
		{
			url: 'imovel.tipo',
			title: 'imovel.proprietario',
			image: 'imovel.fotoUrl'
		},
		{
			url: 'http://tutorialzine.com/2013/08/simple-registration-system-php-mysql/',
			title: 'Making a Super Simple Registration System With PHP and MySQL',
			image: 'http://cdn.tutorialzine.com/wp-content/uploads/2013/08/simple_registration_system-100x100.jpg'
		},
		{
			url: 'http://tutorialzine.com/2013/08/slideout-footer-css/',
			title: 'Create a slide-out footer with this neat z-index trick',
			image: 'http://cdn.tutorialzine.com/wp-content/uploads/2013/08/slide-out-footer-100x100.jpg'
		},
		{
			url: 'http://tutorialzine.com/2013/06/digital-clock/',
			title: 'How to Make a Digital Clock with jQuery and CSS3',
			image: 'http://cdn.tutorialzine.com/wp-content/uploads/2013/06/digital_clock-100x100.jpg'
		},
		{
			url: 'http://tutorialzine.com/2013/05/diagonal-fade-gallery/',
			title: 'Smooth Diagonal Fade Gallery with CSS3 Transitions',
			image: 'http://cdn.tutorialzine.com/wp-content/uploads/2013/05/featured-100x100.jpg'
		},
		{
			url: 'http://tutorialzine.com/2013/05/mini-ajax-file-upload-form/',
			title: 'Mini AJAX File Upload Form',
			image: 'http://cdn.tutorialzine.com/wp-content/uploads/2013/05/ajax-file-upload-form-100x100.jpg'
		},
		{
			url: 'http://tutorialzine.com/2013/04/services-chooser-backbone-js/',
			title: 'Your First Backbone.js App – Service Chooser',
			image: 'http://cdn.tutorialzine.com/wp-content/uploads/2013/04/service_chooser_form-100x100.jpg'
		}
	]; 
	
}


function InstantWeatherController ($scope) {

	// O modelo de dados. Estes itens normalmente são requisitados via Ajax,
	// mas aqui estão simplificados. Veja o próximo exemplo para 
	// dicas usando Ajax.
	
	 $scope.items = [
		{
			url: 'imovel.tipo',
			title: 'imovel.proprietario',
			image: 'imovel.fotoUrl'
		},
		{
			url: 'hysql/',
			title: 'Mak MySQL',
			image: 'http:system-100x100.jpg'
		},
		{
			url: 'htr-css/',
			title: 'Cre trick',
			image: 'htde-out-footer-100x100.jpg'
		},
		{
			url: 'httpal-clock/',
			title: 'HowSS3',
			image: 'http100x100.jpg'
		},
		{
			url: 'httpllery/',
			title: 'Smoions',
			image: 'ht0x100.jpg'
		},
		{
			url: 'httorm/',
			title: 'Miorm',
			image: 'htpload-form-100x100.jpg'
		},
		{
			url: 'httackbone-js/',
			title: 'Yooser',
			image: 'htter_form-100x100.jpg'
		}
	]; 
	
}



//Create a controller with name alertMessagesController to bind to the feedback messages section.
app.controller('alertMessagesController', function ($scope) {
    // Picks up the event to display a saved message.    

    // Picks up the event to display a server error message.
    $scope.$on('error', function () {
        $scope.alerts = [
            { type: 'danger', msg: 'Houve um problema na conexao, tente novamente!' }
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
});

//Service that provides  operations
//app.factory('imovelService', function ($resource) {
//    return $resource('resources/imoveis/:id');
//});


function List(capacity) {
    var collection = [];

    if (capacity != null) {
        collection[capacity];
    }
}

this.GetItem = function (index) {
    if (index <= collection.length - 1) {
        return collection[index];
    }
    else {
        throw "Index was out of range. Must be non-negative and less than the size of the collection";
    }
}


