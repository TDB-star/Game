package com.example.game.domain

import android.provider.Settings
import com.example.game.domain.entities.GameSettings
import com.example.game.domain.entities.Level
import com.example.game.domain.entities.Question

interface Repository {

    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question
    fun getSettings(level: Level): GameSettings
}