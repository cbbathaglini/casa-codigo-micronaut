package br.com.zup.autor

import br.com.zup.endereco.Endereco
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.LocalDateTime
import javax.persistence.*


@Entity
class Autor(var nome: String = "",
            var email: String ="",
            var descricao: String ="",
            //@field:Embedded var endereco: Endereco ) { //add @JsonProperty?
            @field:ManyToOne @field:Cascade(CascadeType.ALL) var endereco: Endereco ) { //add @JsonProperty?

    @Id @GeneratedValue var id : Long? = null
    val criadoEm: LocalDateTime = LocalDateTime.now()
}