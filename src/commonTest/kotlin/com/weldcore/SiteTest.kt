package com.weldcore

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SiteTest {
    @Test fun editableContentIsEscaped() {
        val changed = siteContent.copy(products = listOf(Product("<script> & \"parts\"", emptyList())))
        val html = renderPage(changed, 2026)
        assertTrue(html.contains("&lt;script&gt; &amp; &quot;parts&quot;"))
        assertFalse(html.contains("<script> &"))
    }

    @Test fun emailSubjectsPreserveSpecialCharacters() {
        assertEquals("A%20%26%20B%20%2B%20%C3%A9", encodeQuery("A & B + é"))
        assertEquals("mailto:sales@example.com?subject=A%20%26%20B", emailLink("sales@example.com", "A & B"))
    }

    @Test fun changingCompanyUpdatesEveryContactLink() {
        val changed = siteContent.copy(company = siteContent.company.copy(
            infoEmail = "info@example.com", salesEmail = "sales@example.com", phone = "+123456789"
        ))
        val html = renderPage(changed, 2030)
        assertFalse(html.contains("mailto:info@weldcoreinternational.com"))
        assertFalse(html.contains("mailto:sales@weldcoreinternational.com"))
        assertTrue(html.contains("tel:+123456789"))
        assertTrue(html.contains("id=\"year\">2030"))
    }

    @Test fun slidesExcludeStockAndHandleEmptyProductLists() {
        val real = Photo("./assets/real.jpg", "Real")
        val site = siteContent.copy(products = listOf(
            Product("Real", listOf(real, real)),
            Product("Stock", listOf(Photo("stock.jpg", "Stock")), illustrative = true)
        ))
        assertEquals(listOf(real), site.slides)
        assertTrue(renderPage(site.copy(products = emptyList()), 2026).contains(site.company.logo.path))
    }

    @Test fun staticPageHasWorkingSectionsAndRelativeAssets() {
        val html = renderPage(siteContent, 2026)
        siteContent.navigation.forEach { assertTrue(html.contains("id=\"${it.section}\"")) }
        assertTrue(html.contains("src=\"./weldcore.js\""))
        assertFalse(html.contains("slideshow-controls"))
        assertEquals(7, siteContent.products.size)
        assertEquals(5, siteContent.values.size)
    }
}
