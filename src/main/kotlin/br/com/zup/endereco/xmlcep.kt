package br.com.zup.endereco

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

//@JacksonXmlRootElement(localName = "xmlcep")
class xmlcep(val logradouro : String,
             val localidade : String,
             val cep : String,
             val bairro : String,
             val ibge : String,
             val gia : String,
             val ddd : String,
             val siafi : String,
             val uf : String){
}