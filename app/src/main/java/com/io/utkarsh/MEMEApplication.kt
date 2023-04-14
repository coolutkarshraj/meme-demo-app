package com.io.utkarsh

import android.app.Application
import com.io.utkarsh.di.component.ApplicationComponent
import com.io.utkarsh.di.component.DaggerApplicationComponent
import com.io.utkarsh.di.module.ApplicationModule

class MEMEApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}