package io.aquino.repository

import grails.transaction.Transactional
import io.aquino.domain.Category

@Transactional
class CategoryRepositoryService {

    def findCategoryById(Long id) {
        Category.get(id)
    }

    def findCategoryByParams() {
        Category.list()
    }

    Category saveCategory(Category category) {
        category.save()
    }
}
