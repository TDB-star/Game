package com.example.game.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.game.R
import com.example.game.databinding.FragmentGameResultBinding

class GameResultFragment : Fragment() {

    private val args by navArgs<GameResultFragmentArgs>()

    private var _binding: FragmentGameResultBinding? = null
    private val binding: FragmentGameResultBinding
        get() = _binding ?: throw RuntimeException("FragmentGameResultBinding == null")


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
        binding.buttonRestart.setOnClickListener {
            restartGame()
        }
    }

    private fun bindViews() {

        with(binding) {
            imageViewResult.setImageResource(getSmileResId())
            textViewReqCorrectAnswers.text = String.format(
                getString(R.string.required_number_of_correct_answers),
                args.gameResult.gameSettings.minCountOfRightAnswers
            )

            textViewScore.text = String.format(
                getString(R.string.score_s),
                args.gameResult.countRightAnswers
            )

            textViewPercCorrectAnswers.text = String.format(
                getString(R.string.required_percentage_of_correct_answers_s),
                args.gameResult.gameSettings.minPercentOfRightAnswers
            )

            textViewPercScore.text = String.format(
               getString(R.string.percentage_of_right_answers_s),
                getPercRightAnswers()
            )
        }
    }

    private fun getPercRightAnswers() = with(args.gameResult) {
        if (countQuestions == 0) {
            0
        } else {
            ((countRightAnswers / countQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun getSmileResId() : Int {
        return if (args.gameResult.winner) {
            R.drawable.smile
        } else {
            R.drawable.triste
        }
    }

    private fun restartGame() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}