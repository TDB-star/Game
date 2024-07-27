package com.example.game.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.example.game.R
import com.example.game.databinding.FragmentGameResultBinding
import com.example.game.domain.entities.GameResult

class GameResultFragment : Fragment() {
    private lateinit var gameResult: GameResult
    private var _binding: FragmentGameResultBinding? = null
    private val binding: FragmentGameResultBinding
        get() = _binding ?: throw RuntimeException("FragmentGameResultBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsArg()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindViews()
    }

    private fun setupClickListeners() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                restartGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
        binding.buttonRestart.setOnClickListener {
            restartGame()
        }
    }

    private fun bindViews() {

        with(binding) {
            imageViewResult.setImageResource(getSmileResId())
            textViewReqCorrectAnswers.text = String.format(
                getString(R.string.required_number_of_correct_answers),
                gameResult.gameSettings.minCountOfRightAnswers
            )

            textViewScore.text = String.format(
                getString(R.string.score_s),
                gameResult.countRightAnswers
            )

            textViewPercCorrectAnswers.text = String.format(
                getString(R.string.required_percentage_of_correct_answers_s),
                gameResult.gameSettings.minPercentOfRightAnswers
            )

            textViewPercScore.text = String.format(
               getString(R.string.percentage_of_right_answers_s),
                getPercRightAnswers()
            )
        }
    }

    private fun getPercRightAnswers() = with(gameResult) {
        if (countQuestions == 0) {
            0
        } else {
            ((countRightAnswers / countQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun getSmileResId() : Int {
        return if (gameResult.winner) {
            R.drawable.smile
        } else {
            R.drawable.triste
        }
    }

    private fun parsArg() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun restartGame() {
        requireActivity().supportFragmentManager
            .popBackStack(
                GameFragment.NAME,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
    }

    companion object {
        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameResultFragment {

            return GameResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}