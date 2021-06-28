package br.com.zup.endereco

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse

//@Client("http://localhost:8081/cep/busca")
@Client("https://viacep.com.br/ws")
interface EnderecoClient {
    @Get("/{cep}/json/")
    fun consulta(@PathVariable("cep") cep : String) : EnderecoDTOResponse
}