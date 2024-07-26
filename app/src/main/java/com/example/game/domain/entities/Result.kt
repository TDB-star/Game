package com.example.game.domain.entities

import java.io.Serializable

data class Result(
    val winner: Boolean,
    val countRightAnswers: Int,
    val countQuestions: Int,
    val gameSettings: GameSettings
): Serializable