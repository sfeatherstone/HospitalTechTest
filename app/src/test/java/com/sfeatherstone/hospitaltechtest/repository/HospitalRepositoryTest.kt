package com.sfeatherstone.hospitaltechtest.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sfeatherstone.hospitaltechtest.repository.network.RemoteFileReader
import org.junit.Test

import org.junit.Assert.*
import java.io.StringReader

class HospitalRepositoryTest {

    fun mockRemoteFileReader(): RemoteFileReader {

        val reader = StringReader(testData)

        val mockReader = mock<RemoteFileReader>()
        whenever(mockReader.run(any())).thenReturn(reader)
        return mockReader
    }

    @Test
    fun `getListView with no filter`() {
        val repo = HospitalRepository(mockRemoteFileReader())
        val list = repo.getListView(null)
        assertEquals(10, list.size)
        assertEquals("17970", list[0].id)
    }

    @Test
    fun `getListView with filters`() {
        val repo = HospitalRepository(mockRemoteFileReader())

        repo.getListView(repo.getSectors()[0]).apply {
            assertEquals(9, size)
            assertEquals("17970", this[0].id)
        }

        repo.getListView(repo.getSectors()[1]).apply {
            assertEquals(1, size)
            assertEquals("18102", this[0].id)
        }
    }

    @Test
    fun getSectors() {
        val repo = HospitalRepository(mockRemoteFileReader())

        repo.getSectors().apply {
            assertEquals(2, size)
            assertEquals("Independent Sector", this[0])
            assertEquals("NHS Sector", this[1])
        }
    }

    @Test
    fun getHospital() {
        val repo = HospitalRepository(mockRemoteFileReader())
        assertEquals(null, repo.getHospital("yuryew"))
        repo.getHospital("18271")?.apply {
            assertEquals("Independent Sector", Sector)
            assertEquals("Fryatt Hospital, Harwich", OrganisationName)
        }
    }

    val testData = """OrganisationID�OrganisationCode�OrganisationType�SubType�Sector�OrganisationStatus�IsPimsManaged�OrganisationName�Address1�Address2�Address3�City�County�Postcode�Latitude�Longitude�ParentODSCode�ParentName�Phone�Email�Website�Fax
17970�NDA07�Hospital�Hospital�Independent Sector�Visible�True�Walton Community Hospital - Virgin Care Services Ltd��Rodney Road��Walton-on-Thames�Surrey�KT12 3LD�51.379997253417969�-0.40604206919670105�NDA�Virgin Care Services Ltd�01932 414205���01932 253674
17981�NDA18�Hospital�Hospital�Independent Sector�Visible�True�Woking Community Hospital (Virgin Care)��Heathside Road��Woking�Surrey�GU22 7HS�51.315132141113281�-0.55628949403762817�NDA�Virgin Care Services Ltd�01483 715911���
18102�NLT02�Hospital�Hospital�NHS Sector�Visible�True�North Somerset Community Hospital�North Somerset Community Hospital�Old Street��Clevedon�Avon�BS21 6BS�51.43719482421875�-2.8471927642822266�NLT�North Somerset Community Partnership Community Interest Company�01275 872212��http://www.nscphealth.co.uk�
18138�NMP01�Hospital�Hospital�Independent Sector�Visible�False�Bridgewater Hospital�120 Princess Road���Manchester�Greater Manchester�M15 5AT�53.459743499755859�-2.2454688549041748�NMP�Bridgewater Hospital (Manchester) Ltd�0161 2270000��www.bridgewaterhospital.com�
18142�NMV01�Hospital�Hospital�Independent Sector�Visible�True�Kneesworth House�Old North Road�Bassingbourn��Royston��SG8 5JP�52.078121185302734�-3.0604055151343346E-2�NMV�Partnerships In Care Ltd�01763 255 700�reception_kneesworthhouse@partnershipsincare.co.uk�www.partnershipsincare.co.uk�
18143�NMV02�Hospital�Hospital�Independent Sector�Visible�True�Stockton Hall Hospital�Stockton Hall�The Village�Stockton On The Forest�York�North Yorkshire�YO32 9UN�53.995403289794922�-1.0025526285171509�NMV�Partnerships In Care Ltd�01904 400 500�info@priorygroup.com�https://www.priorygroup.com/stockton-hall�
18271�NQ106�Hospital�Hospital�Independent Sector�Visible�True�Fryatt Hospital, Harwich�419 Main Road���Harwich�Essex�CO12 4EX�51.934696197509766�1.261444091796875�NQ1�Anglian Community Enterprise Community Interest Company (ACE CIC)�01255 201200���
18272�NQ108�Hospital�Hospital�Independent Sector�Visible�True�Clacton Hospital��Tower Road��Clacton�Essex�CO15 1LH�51.786079406738281�1.148187518119812�NQ1�Anglian Community Enterprise Community Interest Company (ACE CIC)�01255 201717��https://www.esneft.nhs.uk/your-visit/our-wards/clacton-hospital/�01206 286710
18289�NQ901�Hospital�Hospital�Independent Sector�Visible�False�Lakeside Hospital�The Lane�Wyboston��Bedford�Bedfordshire�MK44 3AS�52.198326110839844�-0.31028071045875549�NQ9�Brookdale Healthcare Ltd (T/A Brookdale Care)����
18305�NQM01�Hospital�Hospital�Independent Sector�Visible�True�Orthopaedics and Spine Specialist Hospital�1 Stirling Way�Bretton��Peterborough�Cambridgeshire�PE3 8YA�52.604942321777344�-0.28302180767059326�NQM�Orthopaedics and Spine Specialist Hospital�01733 333156�info@orthospine.co.uk��01733 373050"""

}