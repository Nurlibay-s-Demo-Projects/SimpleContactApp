package uz.nurlibaydev.contactapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.nurlibaydev.contactapp.databinding.ScreenMainBinding
import uz.nurlibaydev.contactapp.room.ContactDao
import uz.nurlibaydev.contactapp.room.ContactDatabase

class MainScreen : Fragment(R.layout.screen_main) {

    private lateinit var binding: ScreenMainBinding
    private val adapter = ContactAdapter()
    private lateinit var dao: ContactDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenMainBinding.bind(view)
        binding.rvContacts.adapter = adapter
        dao = ContactDatabase.getDatabase(requireContext()).contactDao()

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addScreen)
        }

        adapter.contacts = dao.getMyAllContacts().toMutableList()
    }
}