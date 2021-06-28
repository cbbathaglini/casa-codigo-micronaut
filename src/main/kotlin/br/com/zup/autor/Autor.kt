package br.com.zup.autor

import br.com.zup.endereco.Endereco
import java.time.LocalDateTime
import javax.persistence.*


@Entity
class Autor(var nome: String = "",
            var email: String ="",
            var descricao: String ="",
            @field:Embedded var endereco: Endereco ) {

    @Id @GeneratedValue var id : Long? = null
    val criadoEm: LocalDateTime = LocalDateTime.now()
}