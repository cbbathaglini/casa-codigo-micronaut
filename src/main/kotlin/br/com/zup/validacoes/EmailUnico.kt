package br.com.zup.validacoes

import br.com.zup.autor.Autor
import br.com.zup.autor.AutorRepository
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [EmailValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(
    RetentionPolicy.RUNTIME
)
annotation class EmailUnico(
    val message: String = "O email informado já existe na base de dados",
    val groups :  Array<KClass<*>> = [],
    val payload : Array<KClass<out Payload>> = []
)


@Singleton
open class EmailValidator : ConstraintValidator<EmailUnico, String>{
    @Inject
    lateinit var autorRepository: AutorRepository

    //@PersistenceContext
    @Inject
    lateinit var em: EntityManager


    @Transactional
    open override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<EmailUnico>,
        context: ConstraintValidatorContext
    ): Boolean {

//        val q = em.createQuery("select a from Autor a where a.email =:value")
//        q.setParameter("value", value)
//        val list = q.resultList
//        return !list.isEmpty()

        val autor :Optional<Autor> = autorRepository.findByEmail(value!!)
        if(autor.isPresent){
            return false
        }
        return true
    }


}