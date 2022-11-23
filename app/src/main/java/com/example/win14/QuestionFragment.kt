package com.example.win14

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.win14.databinding.FragmentQuestionBinding
import com.example.win14.model.FirstQuestions
import com.example.win14.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val POSITION = "position"

class QuestionFragment : Fragment() {
    private lateinit var binding: FragmentQuestionBinding
    private lateinit var appDatabase: AppDatabase
    var questionsList = ArrayList<FirstQuestions>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var position = 0
        arguments?.takeIf {
            it.containsKey(POSITION)
        }?.apply {
            position = getInt("position")
        }
        Log.i("position", position.toString())
        appDatabase = AppDatabase.getDatabase(requireContext())
        CoroutineScope(Dispatchers.IO).launch{
            questionsList.addAll(appDatabase.questionsDao().getAll())
            launch(Dispatchers.Main){
                setButtons(position)
                onClickButton(position)
            }
        }
    }

    private fun setButtons(position: Int){
        binding.textViewTitle.text = questionsList[position].question
        binding.buttonAnswer1.text = questionsList[position].answer1.name
        binding.buttonAnswer2.text = questionsList[position].answer2.name
        binding.buttonAnswer3.text = questionsList[position].answer3.name
        binding.buttonAnswer4.text = questionsList[position].answer4.name
    }
    private fun onClickButton(position: Int){
        binding.textViewTitle.text = questionsList[position].question
        binding.buttonAnswer1.text = questionsList[position].answer1.name
        binding.buttonAnswer2.text = questionsList[position].answer2.name
        binding.buttonAnswer3.text = questionsList[position].answer3.name
        binding.buttonAnswer4.text = questionsList[position].answer4.name
        binding.buttonAnswer1.setOnClickListener {
            if (questionsList[position].answer1.trueorfalse=="true"){
                binding.buttonAnswer1.setBackgroundColor(requireContext().getColor(R.color.green_button))
                setButtonDefaultColor("button1")
            } else {
                binding.buttonAnswer1.setBackgroundColor(requireContext().getColor(R.color.red_button))
            }
        }
        binding.buttonAnswer2.setOnClickListener {
            if (questionsList[position].answer2.trueorfalse=="true"){
                binding.buttonAnswer2.setBackgroundColor(requireContext().getColor(R.color.green_button))
                setButtonDefaultColor("button2")
            } else {
                binding.buttonAnswer2.setBackgroundColor(requireContext().getColor(R.color.red_button))
            }
        }
        binding.buttonAnswer3.setOnClickListener {
            if (questionsList[position].answer3.trueorfalse=="true"){
                binding.buttonAnswer3.setBackgroundColor(requireContext().getColor(R.color.green_button))
                setButtonDefaultColor("button3")
            } else {
                binding.buttonAnswer3.setBackgroundColor(requireContext().getColor(R.color.red_button))
            }
        }
        binding.buttonAnswer4.setOnClickListener {
            if (questionsList[position].answer4.trueorfalse=="true"){
                binding.buttonAnswer4.setBackgroundColor(requireContext().getColor(R.color.green_button))
                setButtonDefaultColor("button4")
            } else {
                binding.buttonAnswer4.setBackgroundColor(requireContext().getColor(R.color.red_button))
            }
        }
    }
    private fun setButtonDefaultColor(button: String) {
        when (button) {
            "button1" -> {
                binding.buttonAnswer2.setBackgroundColor(requireContext().getColor(R.color.default_button))
                binding.buttonAnswer3.setBackgroundColor(requireContext().getColor(R.color.default_button))
                binding.buttonAnswer4.setBackgroundColor(requireContext().getColor(R.color.default_button))
            }
            "button2" -> {
                binding.buttonAnswer1.setBackgroundColor(requireContext().getColor(R.color.default_button))
                binding.buttonAnswer3.setBackgroundColor(requireContext().getColor(R.color.default_button))
                binding.buttonAnswer4.setBackgroundColor(requireContext().getColor(R.color.default_button))
            }
            "button3" -> {
                binding.buttonAnswer1.setBackgroundColor(requireContext().getColor(R.color.default_button))
                binding.buttonAnswer2.setBackgroundColor(requireContext().getColor(R.color.default_button))
                binding.buttonAnswer4.setBackgroundColor(requireContext().getColor(R.color.default_button))
            }
            "button4" -> {
                binding.buttonAnswer1.setBackgroundColor(requireContext().getColor(R.color.default_button))
                binding.buttonAnswer2.setBackgroundColor(requireContext().getColor(R.color.default_button))
                binding.buttonAnswer3.setBackgroundColor(requireContext().getColor(R.color.default_button))
            }
        }
    }
}