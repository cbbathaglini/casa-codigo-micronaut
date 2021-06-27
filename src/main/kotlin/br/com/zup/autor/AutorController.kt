package br.com.zup.autor

import io.micronaut.validation.Validated
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid request : AutorDTORequest) : HttpResponse<Any>{

        println("Requisição => ${request}")

        val autor = request.toAutor()
        autorRepository.save(autor)

        println("Resposta => ${autor.nome}")

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id",autor.id)))
        return HttpResponse.created(uri)

    }


    @Get
    fun listar() : HttpResponse<List<DetalhesAutorDTOResponse>>{

        val autores = autorRepository.findAll()
        val resposta = autores.map {  autor -> DetalhesAutorDTOResponse(autor) }

        return HttpResponse.ok(resposta)

    }


    @Get("/{id}")
    fun consultar(@PathVariable("id") idAutor : Long ) : HttpResponse<Any>{

        val autor = autorRepository.findById(idAutor)
        if(autor.isPresent) {
            return HttpResponse.ok(DetalhesAutorDTOResponse(autor.get()))
        }

        return HttpResponse.notFound("Autor não foi encontrado")
    }


    @Put("/{id}")
    fun alterar(@PathVariable("id") idAutor : Long, descricao : String ) : HttpResponse<Any>{

        val autorOp = autorRepository.findById(idAutor)
        if(autorOp.isPresent) {
            val autor : Autor = autorOp.get()
            autor.descricao = descricao
            autorRepository.update(autor)
            return HttpResponse.ok(DetalhesAutorDTOResponse(autor))
        }

        return HttpResponse.notFound("Autor não foi encontrado")
    }

    @Delete("/{id}")
    fun deletar(@PathVariable("id") idAutor : Long) : HttpResponse<Any>{

        val autorOp = autorRepository.findById(idAutor)
        if(autorOp.isPresent) {
            autorRepository.delete(autorOp.get())
            //autorRepository.deleteById(autorOp.get().id)
            return HttpResponse.ok()
        }

        return HttpResponse.notFound("Autor não foi encontrado")
    }


    @Get("/busca")
    @Transactional
    fun consultaQuery(@QueryValue(defaultValue = "") email : String) : HttpResponse<Any>{

        if(email.isBlank()){
            val autores = autorRepository.findAll()
            val resposta = autores.map {  autor -> DetalhesAutorDTOResponse(autor) }
            return HttpResponse.ok(resposta)
        }

        val autorOp = autorRepository.findByEmail(email)
        if(autorOp.isPresent) {
            return HttpResponse.ok(DetalhesAutorDTOResponse(autorOp.get()))
        }

        return HttpResponse.notFound("Autor não foi encontrado")
    }
}