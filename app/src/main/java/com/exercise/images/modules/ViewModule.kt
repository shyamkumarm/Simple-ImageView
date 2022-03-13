package com.exercise.images.modules


import com.exercise.images.viewmodels.JsonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module





val requestViewModel = module  {
    viewModel{ JsonViewModel(get()) }

}

val viewModules = listOf(requestViewModel)

