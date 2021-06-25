package br.com.zup.autor

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController {

    @Post
    fun cadastra(@Body @Valid request : AutorDTORequest){


        println(request)
    }
}