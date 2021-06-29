package br.com.zup.autor


data class DetalhesAutorDTOResponse (val nome : String,
                                     val email : String,
                                     val descricao : String,
){
    constructor(autor: Autor) : this(autor.nome, autor.email, autor.descricao)
}