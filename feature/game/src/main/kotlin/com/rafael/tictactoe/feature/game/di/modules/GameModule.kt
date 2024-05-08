package com.rafael.tictactoe.di

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.game.data.dao.OnlineGameDao
import com.rafael.tictactoe.game.data.repository.GameRepositoryImpl
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.gameScreen.presentation.viewmodel.GameViewModel
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.rafael.tictactoe.game.data.dao.OnlineGameDao
import retrofit2.Retrofit

class GameModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object GameDataModule {

        @Provides
        @Singleton
        fun provideGameRepository(
            retrofitDao: OnlineGameDao,
        ): GameRepository {
            return GameRepositoryImpl(
                retrofitDao,
            )
        }
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    object GameViewModelModule {

        @Provides
        @ViewModelScoped
        fun provideGameViewModel(
            player: ExoPlayer,
            settingsVM: SettingsViewModel,
            context: Application,
            repository: GameRepository,
        ): GameViewModel {
            return GameViewModel(
                player,
                settingsVM,
                context,
                repository,
            )
        }

        @Provides
        fun provideGameService(retrofit: Retrofit): OnlineGameDao {
            return retrofit.create(OnlineGameDao::class.java)
        }
    }
}
