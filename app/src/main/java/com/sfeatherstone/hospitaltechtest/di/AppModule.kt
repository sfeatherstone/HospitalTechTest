package com.sfeatherstone.hospitaltechtest.di

import com.sfeatherstone.hospitaltechtest.repository.HospitalRepository
import com.sfeatherstone.hospitaltechtest.repository.network.RemoteFileReader
import com.sfeatherstone.hospitaltechtest.ui.HospitalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HospitalViewModel(get()) }

    factory { HospitalRepository(get()) }
    factory { RemoteFileReader() }
}