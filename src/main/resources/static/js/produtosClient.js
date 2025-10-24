$(function() {
	$(".js-load-produtos").on('click', function() {
		$.ajax({
			url: "http://localhost:8080/api/produtos",
			type: "get",
			success: function(response) {
				desenhaTabela(response);
			}
		});
	})
});

function desenhaTabela(dados) {
	$(".js-produtos-table-body tr").remove();
	for (var i = 0; i < dados.length; i++) {
		desenhaLinha(dados[i]);
	}
}

function desenhaLinha(linha) {
	var linhaTabela = $("<tr/>");
	$(".js-produtos-table-body").append(linhaTabela);
	linhaTabela.append("<td>" + linha.id + "</td>");
	linhaTabela.append("<td>" + linha.nome + "</td>");
	linhaTabela.append("<td>" + linha.preco + "</td>");
	linhaTabela.append("<td>" + linha.categoria.nome + "</td>");
}