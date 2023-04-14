package com.io.utkarsh.di.component

import dagger.Component
import com.io.utkarsh.di.ActivityScope
import com.io.utkarsh.di.module.ActivityModule
import com.io.utkarsh.ui.memeView.MemeApiActivity

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MemeApiActivity)

}