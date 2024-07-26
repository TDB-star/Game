package com.example.game.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.game.R
import com.example.game.databinding.FragmentGameBinding
import com.example.game.databinding.FragmentGameResultBinding
import com.example.game.domain.entities.Result

class GameResultFragment : Fragment() {
    private lateinit var gameResult: Result
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
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                restartGame()
            }
        })

        binding.buttonRestart.setOnClickListener {
            restartGame()
        }
    }

    private fun parsArg() {
        requireArguments().getParcelable<Result>(KEY_GAME_RESULT)?.let {
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

        fun newInstance(gameResult: Result): GameResultFragment {

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