package com.umc.workbook.di

import com.umc.workbook.data.repository.StoreRepositoryImpl
import com.umc.workbook.data.repository.UserRepositoryImpl
import com.umc.workbook.domain.repository.StoreRepository
import com.umc.workbook.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindStoreRepository(storeRepositoryImpl: StoreRepositoryImpl): StoreRepository
}
