package com.terraformcreatives.tabemonotabetai.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.noties.markwon.Markwon


@Module
@InstallIn(ApplicationComponent::class)
class CommonsModule {

    @Provides
    fun provideMarkwon(@ApplicationContext context: Context): Markwon {
        return Markwon.builder(context)
            .build()
    }

}