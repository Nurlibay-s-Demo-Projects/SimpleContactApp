package uz.nurlibaydev.contactapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.nurlibaydev.contactapp.databinding.ScreenAddBinding

class AddScreen : Fragment(R.layout.screen_add){

    private lateinit var binding: ScreenAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenAddBinding.bind(view)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
            //findNavController().popBackStack()
        }
    }
}