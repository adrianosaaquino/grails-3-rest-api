package io.aquino.utils;

import org.grails.web.converters.exceptions.ConverterException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrorsCustomMarshaller {

    def messageSource;

    public ValidationErrorsCustomMarshaller(messageSource) {
        this.messageSource = messageSource
    }

    public void marshalObject(object, listErrors) throws ConverterException {

        Errors errors = (Errors) object;

        try {

            for (Object o : errors.getAllErrors()) {
                Map ret = [:]
                if (o instanceof FieldError) {
                    FieldError fe = (FieldError) o;
                    ret."field" = fe.getField();
                    ret."error-code" = fe.code;
                    ret."rejected-value" = fe.getRejectedValue();
                    Locale locale = LocaleContextHolder.getLocale();
                    if (messageSource != null) {
                        ret."message" = messageSource.getMessage(fe, locale);
                    } else {
                        ret."message" = fe.getDefaultMessage();
                    }
                } else if (o instanceof ObjectError) {
                    ObjectError fe = (ObjectError) o;
                    Locale locale = LocaleContextHolder.getLocale();
                    if (messageSource != null) {
                        ret."message" = messageSource.getMessage(fe, locale);
                    } else {
                        ret."message" = fe.getDefaultMessage();
                    }
                }

                listErrors << ret
            }
        }
        catch (ConverterException ce) {
            throw ce;
        }
        catch (Exception e) {
            e.printStackTrace()
            throw new ConverterException("Error converting Bean with class " + object.getClass().getName(), e);
        }
    }
}
