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
}