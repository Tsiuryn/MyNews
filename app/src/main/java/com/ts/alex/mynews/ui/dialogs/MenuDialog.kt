package com.ts.alex.mynews.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ts.alex.mynews.databinding.DialogMenuFragmentBinding
import com.ts.alex.mynews.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuDialog: DialogFragment() {
    private lateinit var binding: DialogMenuFragmentBinding
    private val  viewModel by sharedViewModel<MainViewModel>()

    private var changedTime: Long = 12 * 60 * 60 * 1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogMenuFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTimePicker()
        if(viewModel.isUpdateNews()){
            binding.vSwitch.isChecked = true
            visibility(true)
        }
        binding.vSave.setOnClickListener{
            viewModel.updateNews(changedTime)
            dismiss()
        }
        binding.vSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            visibility(show = isChecked)
            if(!isChecked) viewModel.cancelUpdateNews()

        }
        observe()
    }

    private fun observe(){
        viewModel.updateNews.observe({ lifecycle }){
            val mls = it.first
            val isUpdate = it.second
            val _hour = mls / (60 * 60 * 1000)
            val _minutes = (mls - _hour * 60 * 60 * 1000) / (60 * 1000)
            val hour = if(_hour< 10)"0$_hour" else _hour
            val minutes = if(_minutes< 10)"0$_minutes" else _minutes
            if(isUpdate){
                Toast.makeText(requireContext(), "The news will be update every (hh:mm): $hour:$minutes ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun visibility(show: Boolean) {
        if(show){
            binding.vSave.visibility = View.VISIBLE
            binding.vPicker.visibility = View.VISIBLE
            binding.vTitle.visibility = View.VISIBLE
        }else{
            binding.vSave.visibility = View.GONE
            binding.vPicker.visibility = View.GONE
            binding.vTitle.visibility = View.GONE
        }

    }

    private fun setUpTimePicker() {
        val picker = binding.vPicker
        picker.setIs24HourView(true)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            picker.hour = 12
            picker.minute = 0
        }
        picker.setOnTimeChangedListener { view, hourOfDay, minute ->
            changedTime = hourOfDay * 60 * 60 * 1000L + minute * 60 * 1000
        }
    }
}