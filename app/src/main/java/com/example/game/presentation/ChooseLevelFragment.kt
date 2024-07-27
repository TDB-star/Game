package com.example.game.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.game.R
import com.example.game.databinding.FragmentChooseLevelBinding
import com.example.game.domain.entities.GameLevel

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTestLevel.setOnClickListener {
            launchGameFragment(GameLevel.TEST)
        }

        binding.buttonEasyLevel.setOnClickListener {
            launchGameFragment(GameLevel.EASY)
        }

        binding.buttonHardLevel.setOnClickListener {
            launchGameFragment(GameLevel.HARD)
        }

        binding.buttonMiddleLevel.setOnClickListener {
            launchGameFragment(GameLevel.NORMAL)
        }
    }

    private fun launchGameFragment(level: GameLevel) {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    companion object {

        const val NAME = "chooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}