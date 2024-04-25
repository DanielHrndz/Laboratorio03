package com.alexisflop.labo03.model

data class ObjectClass(
    private var propertyOne: String,
    private var propertyTwo: String,

) {
    var PropertyOne: String
        get() = propertyOne
        set(value) {
            propertyOne = value
        }

    var PropertyTwo: String
        get() = propertyTwo
        set(value) {
            propertyTwo = value
        }
}

