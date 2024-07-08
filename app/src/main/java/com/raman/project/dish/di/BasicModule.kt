package com.raman.project.dish.di

import com.raman.project.dish.api.ApiService
import com.raman.project.dish.repositories.DishRepository
import com.raman.project.dish.viewModels.DishViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(FragmentComponent::class)
@Module
object BasicModule {


    @Provides
    // Consider singleton scope if DishRepository has no dependencies requiring new instances
    fun provideDishRepository(): DishRepository {
        return DishRepository()
    }

    @Provides
    fun provideDishViewModel(repository: DishRepository): DishViewModel {
        return DishViewModel(repository)
    }
}