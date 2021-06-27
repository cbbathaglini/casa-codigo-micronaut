package br.com.zup.autor

class DetalhesAutorDTOResponse (autor: Autor){

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}