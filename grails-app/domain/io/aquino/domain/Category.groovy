package io.aquino.domain

class Category {

    String name
    Integer age

    static constraints = {
        name nullable: false, blank: false, minSize: 3, maxSize: 99
        age nullable: false, min: 1, max: 10
    }
}
