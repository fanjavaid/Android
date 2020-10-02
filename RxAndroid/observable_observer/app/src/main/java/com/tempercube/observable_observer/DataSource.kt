package com.tempercube.observable_observer

object DataSource {
    fun getTasks(): List<Task> {
        return listOf(
            Task(1, "Buy a toothbrush", false),
            Task(2, "Reading Malcom Gladwell", true),
            Task(3, "Watching Netflix", false),
            Task(4, "Cleaning the bathroom", false)
        )
    }
}
