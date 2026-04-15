package com.example.buy_it.data.injection

import com.example.buy_it.data.datasource.UserRemoteDatasource
import com.example.buy_it.data.datasource.impl.firestore.UserFirestoreDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    @Singleton
    abstract fun bindUserRemoteDatasource(
        impl: UserFirestoreDataSourceImpl
    ): UserRemoteDatasource
}
