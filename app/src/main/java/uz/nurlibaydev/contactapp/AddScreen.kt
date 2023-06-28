package uz.nurlibaydev.contactapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.nurlibaydev.contactapp.databinding.ScreenAddBinding
import uz.nurlibaydev.contactapp.room.Contact
import uz.nurlibaydev.contactapp.room.ContactDao
import uz.nurlibaydev.contactapp.room.ContactDatabase

class AddScreen : Fragment(R.layout.screen_add) {

    private lateinit var binding: ScreenAddBinding
    private lateinit var dao: ContactDao
    private val navArgs by navArgs<AddScreenArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenAddBinding.bind(view)
        dao = ContactDatabase.getDatabase(requireContext()).contactDao()
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
                //findNavController().popBackStack()
            }

            if (navArgs.contact != null) {
                tvTitle.text = "Edit"
                etName.setText(navArgs.contact?.contactName)
                etSurname.setText(navArgs.contact?.surname)
                etPhone.setText(navArgs.contact?.phoneNumber)
            } else {
                tvTitle.text = "Add"
            }

            btnDone.setOnClickListener {
                val name = etName.text.toString()
                val surname = etSurname.text.toString()
                val phone = etPhone.text.toString()

                if (navArgs.contact != null) {
                    dao.updateContact(
                        Contact(
                            id = navArgs.contact!!.id,
                            contactName = name,
                            surname = surname,
                            phoneNumber = phone
                        )
                    )
                } else {
                    dao.addContact(
                        Contact(
                            id = 0,
                            contactName = name,
                            surname = surname,
                            phoneNumber = phone,
                            state = 0
                        )
                    )
                }

                findNavController().popBackStack()
            }
        }
    }
}