import grails.converters.JSON
import grails.validation.ValidationErrors
import io.aquino.domain.Role
import io.aquino.domain.User
import io.aquino.domain.UserRole
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

        Role admin = new Role("ROLE_ADMIN").save()
        User user = new User("user", "pass").save()
        UserRole.create(user, admin, true)
    }
    def destroy = {
    }
}
