package it.liceonewton.viewmodelexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import it.liceonewton.viewmodelexample.databinding.FragmentFirstBinding
import it.liceonewton.viewmodelexample.BR.myViewModel
import it.liceonewton.viewmodelexample.BR.uiController


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!


    lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first,container, false)
        binding.lifecycleOwner=this


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setVariable(myViewModel,viewModel)
        binding.setVariable(uiController,this)

        binding.convertButton.setOnClickListener{
            if(binding.dollarText.text.isNotEmpty()){
                viewModel.dollarText = binding.dollarText.text.toString()
            } else {
                binding. resultText.text = "No Value"
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}