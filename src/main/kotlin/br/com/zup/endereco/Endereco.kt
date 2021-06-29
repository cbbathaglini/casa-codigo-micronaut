package br.com.zup.endereco

import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

//@Embeddable
@Entity
data class Endereco (val logradouro : String,
                     val localidade : String,
                     val cep : String,
                     val bairro : String,
                     val ibge : String,
                     val gia : String,
                     val ddd : String,
                     val siafi : String,
                     val uf : String,
                var numero: String ="") {

    @Id
    @GeneratedValue
    var id : Long? = null

//    val logradouro = xmlcep.logradouro
//    val localidade = xmlcep.localidade
//    val uf = xmlcep.uf
//    val cep = xmlcep.cep
}