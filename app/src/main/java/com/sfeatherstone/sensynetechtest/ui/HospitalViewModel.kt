package com.sfeatherstone.sensynetechtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sfeatherstone.sensynetechtest.model.Hospital
import com.sfeatherstone.sensynetechtest.model.ListHospital
import com.sfeatherstone.sensynetechtest.repository.HospitalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HospitalViewModel(private val hospitalRepository: HospitalRepository) : ViewModel() {

    private val sectorsMutable: MutableLiveData<List<String>> = MutableLiveData()
    val sectors: LiveData<List<String>> = sectorsMutable

    private val hospitalListMutable: MutableLiveData<List<ListHospital>> = MutableLiveData()
    val hospitalList: LiveData<List<ListHospital>> = hospitalListMutable

    private val hospitalMutable: MutableLiveData<Hospital?> = MutableLiveData()
    val hospital: LiveData<Hospital?> = hospitalMutable

    fun loadData() {
        viewModelScope.launch {
            getSectors()
            setSector(null)
        }
    }

    suspend private fun getSectors() = withContext(Dispatchers.Default) {
        sectorsMutable.postValue(hospitalRepository.getSectors())
    }

    fun setSector(sector: String?) {
        hospitalListMutable.postValue(hospitalRepository.getListView(sector))
    }

    fun setHospital(id: String) {
        hospitalMutable.postValue(hospitalRepository.getHospital(id))
    }
}