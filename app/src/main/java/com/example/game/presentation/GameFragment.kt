package com.example.game.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.game.databinding.FragmentGameBinding
import com.example.game.domain.entities.GameResult

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level, requireActivity().application
        )
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
           viewModelFactory
        )[GameViewModel::class.java]
    }

    private val textViewOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.textViewOption1)
            add(binding.textViewOption2)
            add(binding.textViewOption3)
            add(binding.textViewOption4)
            add(binding.textViewOption5)
            add(binding.textViewOption6)
        }
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
        setClickListenersToOptions()
    }

    private fun launchGameResultFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections
                .actionGameFragmentToGameResultFragment(gameResult)
        )
    }

    private fun observeViewModel() {
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameResultFragment(it)
        }
    }

    private fun setClickListenersToOptions() {
        for (tvOption in textViewOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}