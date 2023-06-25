package uz.nurlibaydev.contactapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
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

        Toast.makeText(requireContext(), "onViewCreated", Toast.LENGTH_SHORT).show()

        binding.btnAdd.setOnClickListener {
            val action = MainScreenDirections.actionMainScreenToAddScreen(null)
            findNavController().navigate(action)
        }

        binding.editQuery.doAfterTextChanged {
           adapter.contacts = dao.findContactWithName("$it%").toMutableList()
        }
        binding.ivMore.setOnClickListener {
            dao.deleteAll()
            setData()
        }
        adapter.editBtnClicked {
            val action = MainScreenDirections.actionMainScreenToAddScreen(it)
            findNavController().navigate(action)
        }

        adapter.observer {
            if(adapter.checkedContactsId.isNotEmpty()){
                binding.ivClose.isVisible = true
                binding.title.text = adapter.checkedContactsId.count().toString()
            }
        }

        adapter.deleteBtnClicked {
            dao.deleteContact(it.id)
            setData()
//            val number = Uri.parse("tel:${it.phoneNumber}")
//            val callIntent = Intent(Intent.ACTION_DIAL, number)
            //startActivity(callIntent)
        }

        setData()
    }

    private fun setData() {
        adapter.contacts = dao.getMyAllContacts().toMutableList()
    }
}