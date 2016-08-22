import grails.converters.JSON
import grails.validation.ValidationErrors
import io.aquino.utils.ValidationErrorsCustomMarshaller

class BootStrap {

    def messageSource

    def init = { servletContext ->
        JSON.registerObjectMarshaller(ValidationErrors) { validationErrors ->

            def listErrors = []
            def marshaller = new ValidationErrorsCustomMarshaller(messageSource)
            marshaller.marshalObject(validationErrors, listErrors)
            return listErrors
        }
    }
    def destroy = {
    }
}
