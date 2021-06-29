package br.com.zup.autor

import br.com.zup.endereco.Endereco
import br.com.zup.endereco.EnderecoDTOResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import javax.inject.Inject

@MicronautTest
internal class AutorControllerTest{

    @Inject
    lateinit var autorRepository: AutorRepository


    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup(){
        val enderecoResponse = EnderecoDTOResponse(logradouro = "Rua xxxx", localidade = "Porto Alegre", cep = "91760-000", bairro = "Pedra Redonda", ibge = "ibge", gia = "gia", ddd = "51",siafi = "siafi",uf = "RS")
        val endereco = Endereco(enderecoResponse, "700")

        autor = Autor("Carine Bertagnolli Bathaglini", "carine@email.com","descrição descrição ....", endereco)
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown(){
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve buscar um autor quando um email valido eh informado`(){

        //get, post, put?
        val response  = client.toBlocking().exchange("/autores/busca?email=${autor.email}", DetalhesAutorDTOResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body)
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.descricao, response.body()!!.descricao)
        assertEquals(autor.email, response.body()!!.email)

    }


    @Test
    internal fun `deve cadastrar novo autor`(){

        val response  = client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesAutorDTOResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body)
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.descricao, response.body()!!.descricao)
        assertEquals(autor.email, response.body()!!.email)

    }
}