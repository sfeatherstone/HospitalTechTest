package com.sfeatherstone.hospitaltechtest.model

data class Hospital(
    val OrganisationID: String,
    val OrganisationCode: String,
    val OrganisationType: String,
    val SubType: String,
    val Sector: String,
    val OrganisationStatus: String,
    val IsPimsManaged: String,
    val OrganisationName: String,
    val Address1: String,
    val Address2: String,
    val Address3: String,
    val City: String,
    val County: String,
    val Postcode: String,
    val Latitude: String,
    val Longitude: String,
    val ParentODSCode: String,
    val ParentName: String,
    val Phone: String,
    val Email: String,
    val Website: String,
    val Fax: String
)