package br.com.zup.endereco

import br.com.zup.autor.AutorRepository
import br.com.zup.autor.DetalhesAutorDTOResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.validation.Validated

//@Validated
//
//class EnderecoController(val autorRepository: AutorRepository,
//                      val enderecoCliente:EnderecoClient) {
//
//
//    @Get("/{cep}/busca")
//    fun consultar(@PathVariable("cep") cep : String ) : HttpResponse<Any> {
//
//        val autor = autorRepository.findById(idAutor)
//        if(autor.isPresent) {
//            return HttpResponse.ok(DetalhesAutorDTOResponse(autor.get()))
//        }
//
//        return HttpResponse.notFound("Autor não foi encontrado")
//    }
//
//}
