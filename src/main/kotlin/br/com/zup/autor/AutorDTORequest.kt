package br.com.zup.autor

import br.com.zup.endereco.Endereco
import br.com.zup.endereco.EnderecoDTOResponse
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected //bean de introspecção: para conseguir acessar os atributos da classe
data class AutorDTORequest(
    @field:NotBlank val nome: String, //@NotBlank é direcionado ao atributo
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val CEP: String,
    @field:NotBlank val numero: String,
) {
    fun toAutor( enderecoResponse : EnderecoDTOResponse): Autor {

        val endereco = Endereco(enderecoResponse,numero)
        return Autor(nome, email, descricao, endereco)
    }
}