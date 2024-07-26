package com.example.game.data

import com.example.game.domain.Repository
import com.example.game.domain.entities.GameLevel
import com.example.game.domain.entities.GameSettings
import com.example.game.domain.entities.Question
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object RepositoryImplementation: Repository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 2

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max((rightAnswer - countOfOptions), MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }

        return Question(sum, visibleNumber, options.toList())
    }

    override fun getSettings(level: GameLevel): GameSettings {
        return when (level) {
            GameLevel.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8)
            }
            GameLevel.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    60)
            }
            GameLevel.NORMAL -> {
                GameSettings(
                    20,
                    20,
                    80,
                    40)
            }
            GameLevel.HARD -> {
                GameSettings(
                    100,
                    90,
                    90,
                    40)
            }
        }
    }
}