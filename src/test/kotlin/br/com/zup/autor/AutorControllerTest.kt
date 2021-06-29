package br.com.zup.autor

import br.com.zup.endereco.Endereco
import br.com.zup.endereco.EnderecoClient
import br.com.zup.endereco.EnderecoDTOResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.net.URI
import javax.inject.Inject


@MicronautTest
internal class AutorControllerTest{

    @Inject
    lateinit var autorRepository: AutorRepository

    @Inject
    lateinit var enderecoClient: EnderecoClient


    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

//    @Inject
//    @Client("/")
//    lateinit var rxHttpClient: RxHttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup(){


        val endereco : Endereco = Endereco(logradouro = "Rua xxxx", localidade = "Porto Alegre", cep = "91760-000", bairro = "Pedra Redonda", ibge = "ibge", gia = "gia", ddd = "51",siafi = "siafi",uf = "RS", "700")

        autor = Autor("Carine Bertagnolli Bathaglini", "carine@email.com","descrição descrição ....", endereco)
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown(){
        autorRepository.deleteAll()
    }


//  @Test
//  @Ignore
//  public void testMailSend() throws Exception {
//    //String requestBody = "{\"cc\": [\"sergio.delamo@softamo.com\"],\"recipient\": \"sergio.delamo@softamo.com\", \"subject\": \"Interested in Pet\", \"replyTo\": \"sergio.delamo@softamo.com\", \"htmlBody\": \"Body html\", \"bcc\": [\"sergio.delamo@softamo.com\"]}";
//    String requestBody = "{\"recipient\": \"sergio.delamo@softamo.com\"}";
//    HttpResponse rsp = client.toBlocking().exchange(HttpRequest.POST("/v1/mail/send",requestBody));
//    assertEquals(200, rsp.getStatus().getCode());
//  }


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
        val enderecoResponse : EnderecoDTOResponse = EnderecoDTOResponse(logradouro = "Rua xxxx", localidade = "Porto Alegre", cep = "91760-000", bairro = "Pedra Redonda", ibge = "ibge", gia = "gia", ddd = "51",siafi = "siafi",uf = "RS")

        Mockito.`when`(enderecoClient.consulta(autor.endereco.cep)).thenReturn(enderecoResponse)

        //val autorDTORequest = AutorDTORequest(nome = "Carine", email = "email@email.com", descricao = "descricao teste mockito", CEP = "91760-000", numero = "760")

        val dto = "{\n" +
                    "\t\"nome\":\"Sophia\",\n" +
                    "\t\"email\":\"sophia2@email.com\",\n" +
                    "\t\"descricao\":\"descricao descricao ...\",\n" +
                    "\t\"numero\" :\"760\",\n" +
                    "\t\"CEP\" : \"91760-000\"\n" +
                   "}"
        val response = client.toBlocking().exchange(HttpRequest.POST("/autores", dto),Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock() : EnderecoClient{
        return Mockito.mock(EnderecoClient::class.java)
    }


}