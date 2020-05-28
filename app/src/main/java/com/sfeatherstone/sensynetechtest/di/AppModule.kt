package com.sfeatherstone.sensynetechtest.di

import com.sfeatherstone.sensynetechtest.repository.HospitalRepository
import com.sfeatherstone.sensynetechtest.repository.network.RemoteFileReader
import com.sfeatherstone.sensynetechtest.ui.HospitalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HospitalViewModel(get()) }

    factory { HospitalRepository(get()) }
    factory { RemoteFileReader() }
}