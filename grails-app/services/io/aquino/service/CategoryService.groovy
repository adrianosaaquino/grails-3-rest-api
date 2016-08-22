package io.aquino.service

import grails.transaction.Transactional
import io.aquino.controller.CategorySaveCommand
import io.aquino.controller.CategoryUpdateCommand
import io.aquino.domain.Category

@Transactional
class CategoryService {

    def saveCategory(CategorySaveCommand categorySaveCommand) {

        def ret = [:]

        if (!categorySaveCommand.validate()) {
            ret.errors = categorySaveCommand.errors
            return ret
        }

        def category = new Category(categorySaveCommand.properties)

        if (category.save()) {
            ret.id = category.id
        } else {
            ret.errors = category.errors
        }

        return ret
    }

    def updateCategory(CategoryUpdateCommand categoryUpdateCommand) {
        [:]
    }

    def deleteCategory(Long id) {
        [:]
    }
}
