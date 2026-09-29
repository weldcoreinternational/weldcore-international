package com.weldcore

data class Photo(val path: String, val alt: String)

data class Company(
    val legalName: String,
    val location: String,
    val address: List<String>,
    val phone: String,
    val phoneDisplay: String,
    val infoEmail: String,
    val salesEmail: String,
    val logo: Photo
)

data class Hero(
    val heading: String,
    val highlightedHeading: String,
    val description: String,
    val caption: String,
    val tagline: String,
    val specialties: List<String>,
    val slideIntervalMs: Int
)

data class Product(
    val name: String,
    val photos: List<Photo>,
    val illustrative: Boolean = false,
    val isSolution: Boolean = false
)

data class CompanyValue(val title: String, val paragraphs: List<String>)
data class NavLink(val section: String, val label: String)

data class SiteCopy(
    val skipLink: String, val menu: String, val homeLabel: String,
    val exploreProducts: String, val talkToTeam: String, val welcome: String,
    val productsEyebrow: String, val productsHeading: String, val discussRequirements: String,
    val valuesEyebrow: String, val valuesHeading: String,
    val contactEyebrow: String, val contactHeading: String,
    val quoteHeading: String, val quoteInstructions: String, val emailSales: String,
    val addressHeading: String, val backToTop: String, val illustrativeImage: String,
    val productEnquiry: String, val solutionEnquiry: String,
    val enquirySubject: String, val generalEnquirySubject: String,
    val photoGallery: String, val viewPhoto: String
)

data class SiteContent(
    val company: Company,
    val description: String,
    val hero: Hero,
    val about: List<String>,
    val products: List<Product>,
    val values: List<CompanyValue>,
    val navigation: List<NavLink>,
    val copy: SiteCopy
) {
    // Keep representative stock placeholders out of the automatic product slideshow.
    val slides: List<Photo> get() = products.filterNot { it.illustrative }.flatMap { it.photos }.distinctBy { it.path }
}
