package uz.nurlibaydev.contactapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.nurlibaydev.contactapp.databinding.ScreenAddBinding
import uz.nurlibaydev.contactapp.room.Contact
import uz.nurlibaydev.contactapp.room.ContactDao
import uz.nurlibaydev.contactapp.room.ContactDatabase

class AddScreen : Fragment(R.layout.screen_add){

    private lateinit var binding: ScreenAddBinding
    private lateinit var dao: ContactDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenAddBinding.bind(view)
        dao = ContactDatabase.getDatabase(requireContext()).contactDao()
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
            //findNavController().popBackStack()
        }
        binding.btnDone.setOnClickListener {
            val name = binding.etName.text.toString()
            val surname = binding.etSurname.text.toString()
            val phone = binding.etPhone.text.toString()

            dao.addContact(Contact(id = 0,contactName = name, surname = surname, phoneNumber = phone))

            findNavController().popBackStack()
        }
    }
}