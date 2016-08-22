package io.aquino.controller

import grails.transaction.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class CategoriesController {

    def categoryService
    def categoryRepositoryService

    def index() {
        println "index"
        println "GET /categories"
        categoryRepositoryService.findCategoryByParams()
        respond "name": "bla1", "name2": "asdasd a sjkad kjasd"
    }

    def show(Long id) {
        println "show"
        println "GET /categories/$id"
        categoryRepositoryService.findCategoryById(id)
        respond 'id': id, 'name': 'Example name'
    }

    def save(CategorySaveCommand categorySaveCommand) {
        println "save"
        println "POST /categories"
        def ret = categoryService.saveCategory(categorySaveCommand)
        if (ret.errors) {
            response.status = 400
        }

        respond ControllerHelpers.resolveServiceResponse(response, ret)
    }

    def update(CategoryUpdateCommand categoryUpdateCommand) {
        println "update"
        println "PUT /categories/$categoryUpdateCommand.id"
        def ret = categoryService.updateCategory(categoryUpdateCommand)
        if (ret.errors) {
            response.status = 400
        }

        respond ControllerHelpers.resolveServiceResponse(response, ret)
    }

    def delete(Long id) {
        println "delete"
        println "DELETE /categories/$id"
        def ret = categoryService.deleteCategory(id)
        if (ret.errors) {
            response.status = 400
        }

        respond ControllerHelpers.resolveServiceResponse(response, ret)
    }
}

class ControllerHelpers {

    def static resolveServiceResponse(def response, Map ret) {

        def finalResponse = [:]

        if (ret.errors) {
            response.status = 400
            finalResponse.errors = ret.errors
        }

        return finalResponse
    }
}

class CategorySaveCommand {

    String name
    Integer age

    static constraints = {
        name nullable: false, blank: false, minSize: 3, maxSize: 99
        age nullable: false, min: 1, max: 10
    }
}

class CategoryUpdateCommand {

    Long id
    Long version
    String name
    Integer age

    static constraints = {
        name nullable: false, blank: false, minSize: 3, maxSize: 99
        age nullable: false, min: 1, max: 10
    }
}
