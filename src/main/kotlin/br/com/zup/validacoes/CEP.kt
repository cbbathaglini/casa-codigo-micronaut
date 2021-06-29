package br.com.zup.validacoes

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Singleton
import javax.validation.Constraint


@MustBeDocumented
@Target(AnnotationTarget.FIELD)//, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CEPValidator::class])
annotation class CEP (
    val message: String = "CEP com formato inválido"
)


@Singleton
class CEPValidator : ConstraintValidator<CEP, String>{

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<CEP>,
        context: ConstraintValidatorContext
    ): Boolean {
//        if(value == null){
//            return false
//        }

        //https://github.com/demoiselle/validation/blob/master/impl/src/main/java/br/gov/frameworkdemoiselle/validation/internal/validator/CepValidator.java
        var result : Boolean = false;
        if ( value == null || "".equals(value) ) {
            result = true;
        } else if(value.length != 9){
            return false
        } else{
            var pattern : Pattern = Pattern.compile("^(([0-9]{2}\\.[0-9]{3}-[0-9]{3})|([0-9]{2}[0-9]{3}-[0-9]{3})|([0-9]{8}))$");
            var matcher : Matcher = pattern.matcher(value);
            result = matcher.find();
        }
        return result;
    }



}