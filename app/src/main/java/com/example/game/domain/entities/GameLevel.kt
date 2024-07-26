package com.example.game.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class GameLevel: Parcelable {
    TEST, EASY, NORMAL, HARD
}