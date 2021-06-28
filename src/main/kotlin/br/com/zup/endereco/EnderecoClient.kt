package br.com.zup.endereco

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client


@Client("https://viacep.com.br/ws")
interface EnderecoClient {
    @Get("/{cep}/json/")
    fun consulta(@PathVariable("cep") cep : String) : xmlcep

    @Get("/{cep}/xml/", produces = [MediaType.APPLICATION_XHTML,MediaType.APPLICATION_XML],consumes = [MediaType.APPLICATION_XHTML,MediaType.APPLICATION_XML])
    // ## @Consumes é utilizada para informar qual o formato/MediaType (XML, JSON,TEXT e etc...) que será utilizado para enviar os dados para o servidor no corpo da requisição (POST, PUT, PATCH)
    //@Produces(MediaType.APPLICATION_JSON)
    // ## @Produces é utilizada para informar qual o formato/MediaType (XML, JSON, TEXT etc...) será devolvido ao cliente (GET)
    //@Consumes(MediaType.APPLICATION_XHTML,MediaType.APPLICATION_XML)
    fun consultaXML(@PathVariable("cep") cep : String) : xmlcep
}