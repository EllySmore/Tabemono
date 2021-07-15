package com.terraformcreatives.tabemonotabetai.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
class BaseModule {

    @Provides
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideResources(context: Context): Resources = context.resources

}
