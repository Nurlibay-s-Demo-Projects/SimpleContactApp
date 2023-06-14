package uz.nurlibaydev.contactapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.nurlibaydev.contactapp.databinding.ScreenMainBinding

class MainScreen: Fragment(R.layout.screen_main) {

    private lateinit var binding: ScreenMainBinding
    private val adapter = ContactAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenMainBinding.bind(view)
        binding.rvContacts.adapter = adapter

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_addScreen)
        }

        val contacts = mutableListOf<Contact>()
        repeat(10){
            contacts.add(Contact(it + 1, "Name ${it + 1}", "+998 97 968 08 05"))
        }

        adapter.contacts = contacts
    }
}