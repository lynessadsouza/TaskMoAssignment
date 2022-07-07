package com.example.moviesapp.DependencyInjection

import android.content.Context
import android.view.animation.AnimationUtils
import com.example.myapplication.R
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

private const val TAG1 = "Animations"
@Module
@InstallIn(SingletonComponent::class)
class Animations @Inject constructor(@ApplicationContext context: Context?) {
    val ttb = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom)
    val zoomAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_to_big)
}