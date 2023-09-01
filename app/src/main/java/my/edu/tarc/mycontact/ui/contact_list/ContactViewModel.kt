package my.edu.tarc.mycontact.ui.contact_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.edu.tarc.mycontact.database.ContactDao
import my.edu.tarc.mycontact.database.ContactDatabase
import my.edu.tarc.mycontact.database.ContactRepository

class ContactViewModel(application: Application): AndroidViewModel(application) {
    //val contactList = MutableLiveData<ArrayList<Contact>>()
    var contactList: LiveData<List<Contact>>
    private val repository: ContactRepository

    init {
        //val list = ArrayList<Contact>()
        //contactList.value = list
        //create an instance of db
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        //connect dao to repository
        repository = ContactRepository(contactDao)
        //retrieve contact records
        contactList = repository.allContacts
    }

    fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)
    }

    fun update(contact: Contact) = viewModelScope.launch {
        repository.update(contact)
    }
}