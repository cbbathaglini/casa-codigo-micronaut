package br.com.zup.autor

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected //bean de introspecção: para conseguir acessar os atributos da classe
data class AutorDTORequest(
    @field:NotBlank val nome: String, //@NotBlank é direcionado ao atributo
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String
) {
}