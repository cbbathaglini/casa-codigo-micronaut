package br.com.zup.endereco

import javax.persistence.Embeddable

@Embeddable
class Endereco (xmlcep: xmlcep,
                var numero: String ="") {

//    @Id
//    @GeneratedValue
//    var id : Long? = null

    val logradouro = xmlcep.logradouro
    val localidade = xmlcep.localidade
    val uf = xmlcep.uf
    val cep = xmlcep.cep
}