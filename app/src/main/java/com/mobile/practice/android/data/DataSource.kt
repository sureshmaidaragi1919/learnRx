package com.mobile.practice.android.data

import com.mobile.practice.android.model.Tasks

object DataSource {
    fun createTasksList() = run {
        listOf(
            Tasks("Clean vessels", true, 1),
            Tasks("Arrange vessels", true, 2),
            Tasks("Switch on gas stove", true, 3),
            Tasks("Put cooker with  content", true, 4),
            Tasks("Wait for visible", false, 5)
        )
    }
}