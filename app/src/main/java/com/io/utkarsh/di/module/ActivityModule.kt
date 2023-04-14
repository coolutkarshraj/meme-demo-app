package com.io.utkarsh.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import com.io.utkarsh.data.repository.MemeRepository
import com.io.utkarsh.di.ActivityContext
import com.io.utkarsh.ui.base.ViewModelProviderFactory
import com.io.utkarsh.ui.memeView.MemesAdapter
import com.io.utkarsh.ui.memeView.MemeViewModel

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(memeRepository: MemeRepository): MemeViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(MemeViewModel::class) {
                MemeViewModel(memeRepository)
            })[MemeViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = MemesAdapter(ArrayList())

}