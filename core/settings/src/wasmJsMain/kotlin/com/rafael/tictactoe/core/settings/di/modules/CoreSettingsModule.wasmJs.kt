package com.rafael.tictactoe.core.settings.di.modules

import com.rafael.tictactoe.core.settings.data.dao.SettingsDao
import com.rafael.tictactoe.core.settings.data.repository.SettingsRepositoryImpl
import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity
import org.koin.core.module.Module
import org.koin.dsl.module

actual val coreSettingsModule: Module = module {
    single { SettingsRepositoryImpl(ISettingsDao()) }

    // TODO: Add the real implementation for SettingsDao when Room give support to Wasm
}

// Fake implementation of SettingsDao
// The real implementation for this will be provided by Room database when it give support to Wasm
class ISettingsDao : SettingsDao {
    override suspend fun getSettingsValues(id: Int): SettingsEntity? = null

    override suspend fun setAllSettingsValues(settingsEntity: SettingsEntity) {
    }

    override suspend fun getProfilePicture(): ByteArray? = null

    override suspend fun deleteProfilePicture(profilePicture: ByteArray) {
    }
}
