/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module("HelpApp", [])
        .value('urlBase', 'http://localhost:8080/tasks-java-backend/rest/')
        .controller("ChamadoController", function ($http, urlBase) {
            var self = this;
            self.usuario = 'Spezia, Gonzalez';

            self.chamados = [];
            self.chamado = undefined;

            self.novo = function () {
                self.chamado = {};
            };

            self.salvar = function () {
                var metodo = 'POST';
                if (self.chamado.id) {
                    metodo = 'PUT';
                }

                $http({
                    method: metodo,
                    url: urlBase + 'chamados/',
                    data: self.chamado
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    console.log(self.chamado);
                	self.ocorreuErro();
                });
            };

            self.alterar = function (chamado) {
                self.chamado = chamado;
            };

            self.deletar = function (chamado) {
                self.chamado = chamado;

                $http({
                    method: 'DELETE',
                    url: urlBase + 'chamados/' + self.chamado.id + '/'
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };

            self.concluir = function (chamado) {
                self.chamado = chamado;

                $http({
                    method: 'PUT',
                    url: urlBase + 'chamados/' + self.chamado.id + '/'
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
                alert(" tarefa concluida ");
            };

            self.ocorreuErro = function () {
                alert("Ocorreu um erro ao obter dados, contacte o responsavel pelo sistema.");
            };

            self.atualizarTabela = function () {
                $http({
                    method: 'GET',
                    url: urlBase + 'chamados/'
                }).then(function successCallback(response) {
                    self.chamados = response.data;
                    self.chamado = undefined;
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };

            self.activate = function () {
                self.atualizarTabela();
            };
            self.activate();
        });