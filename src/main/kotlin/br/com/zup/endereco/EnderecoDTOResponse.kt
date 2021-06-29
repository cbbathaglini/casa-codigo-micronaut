package br.com.zup.endereco

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import javax.xml.bind.annotation.XmlRootElement

//@JacksonXmlRootElement(localName = "xmlcep")
//@XmlRootElement(name = "xmlcep")
data class EnderecoDTOResponse(
    val logradouro : String,
             val localidade : String,
             val cep : String,
             val bairro : String,
             val ibge : String,
             val gia : String,
             val ddd : String,
             val siafi : String,
             val uf : String
             ){

    fun converter(numero: String): Endereco {
        return Endereco(logradouro=this.logradouro, localidade = this.localidade, cep = this.cep, bairro = this.bairro, ibge = this.ibge, gia = this.gia,
            ddd= this.ddd, siafi = this.siafi, uf = this.uf, numero)
    }

//    fun converter():Endereco{
//        return Endereco()
//    }
}