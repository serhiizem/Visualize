package com.algorithms.validation;

import com.algorithms.entity.GenerationRequest;
import com.algorithms.entity.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;

@Component
@EnableConfigurationProperties
public class GenerationRequestValidator implements Validator {

    private static final Logger log = LoggerFactory.getLogger(GenerationRequestValidator.class);

    private MessageSource messages;

    @Autowired
    public GenerationRequestValidator(MessageSource messages) {
        this.messages = messages;
        log.info("MessageSource autowired: {}", messages);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return GenerationRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GenerationRequest request = (GenerationRequest) target;
        Range range = request.getRange();
        log.info("Validating range: {}", range);

        int numberOfAvailableValues = range.getMaxValue() - range.getMinValue();

        if(isLess(numberOfAvailableValues, range.getArraySize())) {
            errors.rejectValue("range.maxValue", "Wrong array bounds",
                    messages.getMessage("wrong.array.bounds", null, new Locale("en_US")));
        }
        if(range.getArraySize() == 0) {
            errors.rejectValue("range.arraySize", "Array size is 0",
                    messages.getMessage("zero.array.size", null, new Locale("en_US")));
        }
    }

    private boolean isLess(int numberOfAvailableNumbers, int arraySize) {
        return numberOfAvailableNumbers < arraySize;
    }
}
