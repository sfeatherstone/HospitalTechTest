package com.sfeatherstone.hospitaltechtest.di

import com.sfeatherstone.hospitaltechtest.App.Companion.koinModules
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest


@Category(CheckModuleTest::class)
class ModuleCheckTest : AutoCloseKoinTest() {

    @Test
    fun checkModules() = org.koin.test.check.checkModules {
        modules(koinModules)
    }
}