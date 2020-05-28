package com.sfeatherstone.sensynetechtest.repository

import android.util.Log
import com.sfeatherstone.sensynetechtest.model.Hospital
import com.sfeatherstone.sensynetechtest.model.ListHospital
import com.sfeatherstone.sensynetechtest.repository.network.RemoteFileReader

class HospitalRepository(private val remoteFileReader: RemoteFileReader) {

    private val hospitalCache: HospitalCache by lazy { populateCache() }

    fun getListView(sector: String?): List<ListHospital> {
        return hospitalCache.hospitalList.filter { hospital ->
            sector?.let { it == hospital.Sector } ?: true
        }.map { ListHospital(it.OrganisationID, it.OrganisationName, it.Sector) }
    }

    fun getSectors(): List<String> = hospitalCache.hospitalSectorList.toList()

    fun getHospital(id: String): Hospital? = hospitalCache.hospitalMap.get(id)

    private fun populateCache(): HospitalCache {
        val reader = remoteFileReader.run("http://media.nhschoices.nhs.uk/data/foi/Hospital.csv")
        return reader?.let { reader ->
            val hospitalList = mutableListOf<Hospital>()
            val sectorList = mutableSetOf<String>()
            val hospitalMap = mutableMapOf<String, Hospital>()
            var first = true
            reader.use { r -> r.forEachLine { line ->
                if (first) {
                    first = false
                } else {
                    val values = line.split("�")
                    val hospital = Hospital(
                        values[0],
                        values[1],
                        values[2],
                        values[3],
                        values[4],
                        values[5],
                        values[6],
                        values[7],
                        values[8],
                        values[9],
                        values[10],
                        values[11],
                        values[12],
                        values[12],
                        values[14],
                        values[15],
                        values[16],
                        values[17],
                        values[18],
                        values[19],
                        values[20],
                        values[21])
                    hospitalList.add(hospital)
                    sectorList.add(hospital.Sector)
                    hospitalMap[hospital.OrganisationID] = hospital
                }
            }
            }
            HospitalCache(hospitalList, sectorList, hospitalMap)
        } ?: HospitalCache(emptyList(), emptySet(), emptyMap())
    }

    private data class HospitalCache(
        val hospitalList: List<Hospital>,
        val hospitalSectorList: Set<String>,
        val hospitalMap: Map<String, Hospital>
    )

}