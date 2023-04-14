package com.io.utkarsh.di.component

import android.content.Context
import dagger.Component
import com.io.utkarsh.MEMEApplication
import com.io.utkarsh.data.api.NetworkService
import com.io.utkarsh.data.repository.MemeRepository
import com.io.utkarsh.di.ApplicationContext
import com.io.utkarsh.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MEMEApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): MemeRepository

}