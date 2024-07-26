package com.example.game.domain.usecases

import com.example.game.domain.Repository
import com.example.game.domain.entities.GameSettings
import com.example.game.domain.entities.GameLevel

class GetSettingsUseCase(private val repository: Repository) {

    operator fun invoke(level: GameLevel): GameSettings {
        return repository.getSettings(level)
    }
}