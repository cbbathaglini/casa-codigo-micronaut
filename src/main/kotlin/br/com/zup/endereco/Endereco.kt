package br.com.zup.endereco

import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Embeddable
//@Entity
class Endereco (xmlcep: EnderecoDTOResponse,
                var numero: String ="") {

//    @Id
//    @GeneratedValue
//    var id : Long? = null

    val logradouro = xmlcep.logradouro
    val localidade = xmlcep.localidade
    val uf = xmlcep.uf
    val cep = xmlcep.cep
}