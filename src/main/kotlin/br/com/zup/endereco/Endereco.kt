package br.com.zup.endereco

import javax.persistence.Embeddable
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Embeddable
class Endereco (enderecoDTOResponse: EnderecoDTOResponse,
                var numero: String ="") {

//    @Id
//    @GeneratedValue
//    var id : Long? = null

    val logradouro = enderecoDTOResponse.logradouro
    val localidade = enderecoDTOResponse.localidade
    val uf = enderecoDTOResponse.uf
    val cep = enderecoDTOResponse.cep
}