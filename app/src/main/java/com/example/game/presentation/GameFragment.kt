package com.example.game.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.game.R
import com.example.game.databinding.FragmentGameBinding
import com.example.game.domain.entities.GameLevel
import com.example.game.domain.entities.GameSettings
import com.example.game.domain.entities.GameResult

class GameFragment : Fragment() {

    private lateinit var level: GameLevel
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewOption1.setOnClickListener {
            launchGameResultFragment(
                GameResult(
                true,
                0,
                0,
                GameSettings(
                    0,
                    0,
                    0,
                    0
                )
            ))
        }
    }

    private fun launchGameResultFragment(gameResult: GameResult) {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameResultFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    private fun parsArgs() {
       requireArguments().getParcelable<GameLevel>(KEY_LEVEL)?.let {
           level = it
       }
    }

    companion object {

        const val NAME = "gameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: GameLevel) : GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}