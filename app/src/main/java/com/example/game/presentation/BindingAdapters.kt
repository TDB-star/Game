package com.example.game.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.game.R
import com.example.game.domain.entities.GameResult

interface OnOptionClickListener {
   fun onOptionClickListener(option: Int)
}

@BindingAdapter("app:requiredAnswers")
fun requiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_number_of_correct_answers), count
    )
}

@BindingAdapter("app:score")
fun bindScore(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_s), score)
}

@BindingAdapter("app:percCorrectAnswers")
fun percCorrectAnswers(textView: TextView, perc: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage_of_correct_answers_s), perc)
}

@BindingAdapter("app:percScore")
fun bindScorePerc(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.percentage_of_right_answers_s),
        getPercRightAnswers(gameResult)
    )
}

private fun getPercRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countQuestions == 0) {
        0
    } else {
        ((countRightAnswers / countQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("app:resultImage")
fun bindResultImage(imageView: ImageView, winner: Boolean ) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean) : Int {
    return if (winner) {
        R.drawable.smile
    } else {
        R.drawable.triste
    }
}

@BindingAdapter("app:enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(enough, textView.context))
}

private fun getColorByState(state: Boolean, context: Context) : Int {
    val colorResId = if (state) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("app:enoughPerc")
fun bindEnoughPerc(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(enough, progressBar.context)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("app:numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("app:onOptionClickListener")
fun bindOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClickListener(textView.text.toString().toInt())
    }
}