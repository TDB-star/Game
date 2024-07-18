package com.example.game.domain.usecases

import com.example.game.domain.Repository
import com.example.game.domain.entities.Question

class GenerateQuestionUseCase(
    private val repository: Repository
) {
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}