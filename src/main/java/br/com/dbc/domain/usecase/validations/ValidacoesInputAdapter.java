package br.com.dbc.domain.usecase.validations;

import br.com.dbc.domain.port.input.ValidacoesInput;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class ValidacoesInputAdapter implements ValidacoesInput {

    @Autowired
    private Validator validator;

    @Override
    public Optional<StringBuilder> execute(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {

            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Object> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage() + "/");
            }
            Optional<StringBuilder> optionalStringBuilder = Optional.of(sb);

            return optionalStringBuilder;
        }
        return Optional.empty();
    }
}