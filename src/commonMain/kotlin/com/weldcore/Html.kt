package com.weldcore

/** All editable content is escaped before it enters markup. */
fun String.html(): String = replace("&", "&amp;").replace("<", "&lt;")
    .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;")

fun encodeQuery(value: String): String = buildString {
    for (byte in value.encodeToByteArray()) {
        val code = byte.toInt() and 255
        val char = code.toChar()
        if (char in 'a'..'z' || char in 'A'..'Z' || char in '0'..'9' || char in "-._~") append(char)
        else append('%').append(code.toString(16).uppercase().padStart(2, '0'))
    }
}

fun emailLink(email: String, subject: String? = null): String =
    "mailto:$email" + (subject?.let { "?subject=${encodeQuery(it)}" } ?: "")

private fun photo(image: Photo, css: String = "", lazy: Boolean = true): String =
    """<img class="${css.html()}" src="${image.path.html()}" alt="${image.alt.html()}" width="500" height="500"${if (lazy) " loading=\"lazy\"" else ""}>"""

private fun productCard(product: Product, index: Int, site: SiteContent): String {
    val copy = site.copy
    val pictures = if (product.photos.isEmpty()) "" else if (product.illustrative) {
        """<figure class="product-placeholder">${photo(product.photos.first(), "product-photo")}<figcaption>${copy.illustrativeImage.html()}</figcaption></figure>"""
    } else {
        val thumbs = product.photos.mapIndexed { i, image ->
            """<a href="${image.path.html()}" class="product-thumbnail" aria-label="${(copy.viewPhoto + image.alt).html()}"${if (i == 0) " aria-current=\"true\"" else ""}>${photo(image)}</a>"""
        }.joinToString("")
        """<div class="product-gallery" aria-label="${product.name.html()}">${photo(product.photos.first(), "product-photo")}<div class="product-thumbnails">$thumbs</div></div>"""
    }
    val enquiry = if (product.isSolution) copy.solutionEnquiry else copy.productEnquiry
    return """<article>$pictures<span class="product-number" aria-hidden="true">${(index + 1).toString().padStart(2, '0')}</span><h3>${product.name.html()}</h3><a href="${emailLink(site.company.salesEmail, copy.enquirySubject + product.name).html()}" aria-label="${(enquiry + ": " + product.name).html()}">${enquiry.html()} ↗</a></article>"""
}

fun renderPage(site: SiteContent, year: Int): String {
    val c = site.company
    val h = site.hero
    val t = site.copy
    val firstSlide = site.slides.firstOrNull() ?: c.logo
    return """
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="${site.description.html()}">
  <meta name="theme-color" content="#192f4d">
  <title>${c.legalName.html()}</title>
  <link rel="icon" href="${c.logo.path.html()}">
  <link rel="stylesheet" href="./styles.css">
  <script src="./weldcore.js" defer></script>
</head>
<body>
  <a class="skip" href="#main">${t.skipLink.html()}</a>
  <div class="topbar"><div class="container">${c.location.html()} <a href="${emailLink(c.infoEmail).html()}">${c.infoEmail.html()}</a></div></div>
  <header><div class="container navigation">
    <a class="brand" href="#home" aria-label="${t.homeLabel.html()}">${photo(c.logo, lazy = false)}</a>
    <button class="menu-toggle" aria-expanded="false" aria-controls="navigation" hidden>${t.menu.html()}</button>
    <nav id="navigation" aria-label="Main navigation">${site.navigation.joinToString("") { """<a href="#${it.section.html()}">${it.label.html()}</a>""" }}</nav>
  </div></header>
  <main id="main">
    <section class="hero" id="home">
      <div class="container hero-grid"><div><p class="eyebrow">${c.legalName.uppercase().html()}</p><h1>${h.heading.html()} <span>${h.highlightedHeading.html()}</span></h1><p class="hero-copy">${h.description.html()}</p><div class="actions"><a class="button" href="#products">${t.exploreProducts.html()} <span>↗</span></a><a class="text-link" href="#contact">${t.talkToTeam.html()} →</a></div></div>
      <div class="hero-image" role="region" aria-roledescription="carousel" aria-label="${t.photoGallery.html()}">${photo(firstSlide, lazy = false)}<div class="image-caption"><span>${h.caption.html()}</span><strong>${h.tagline.html()}</strong></div></div></div>
    </section>
    <section id="about" class="section container about"><div><p class="eyebrow">${t.welcome.html()}</p><h2>${h.tagline.html()}</h2></div><div>${site.about.mapIndexed { i, p -> """<p${if (i == site.about.lastIndex) " class=\"statement\"" else ""}>${p.html()}</p>""" }.joinToString("")}</div></section>
    <section id="products" class="section products"><div class="container"><div class="section-heading"><div><p class="eyebrow">${t.productsEyebrow.html()}</p><h2>${t.productsHeading.html()}</h2></div><a class="text-link" href="#contact">${t.discussRequirements.html()} →</a></div>
      <div class="product-grid">${site.products.mapIndexed { i, p -> productCard(p, i, site) }.joinToString("\n")}</div>
    </div></section>
    <section id="services" class="section container"><p class="eyebrow">${t.valuesEyebrow.html()}</p><h2>${t.valuesHeading.html()}</h2><div class="values">${site.values.mapIndexed { i, value ->
        """<article><span>${(i + 1).toString().padStart(2, '0')}</span><h3>${value.title.html()}</h3>${value.paragraphs.mapIndexed { j, p -> """<p${if (j == value.paragraphs.lastIndex) " class=\"value-statement\"" else ""}>${p.html()}</p>""" }.joinToString("")}</article>"""
    }.joinToString("\n")}</div></section>
    <section class="contact section" id="contact"><div class="container contact-grid"><div><p class="eyebrow">${t.contactEyebrow.html()}</p><h2>${t.contactHeading.html()}</h2><p>${c.legalName.html()} — ${h.tagline.html()}</p><a class="phone" href="tel:${c.phone.html()}">${c.phoneDisplay.html()} ↗</a></div>
      <div class="contact-details"><h3>${t.quoteHeading.html()}</h3><p>${t.quoteInstructions.html()}</p><a class="button" href="${emailLink(c.salesEmail, t.generalEnquirySubject).html()}">${t.emailSales.html()} ↗</a><hr><h3>${t.addressHeading.html()}</h3><address><strong>${c.legalName.html()}</strong><br>${c.address.joinToString("<br>") { it.html() }}</address><a href="${emailLink(c.infoEmail).html()}">${c.infoEmail.html()}</a><a href="tel:${c.phone.html()}">${c.phoneDisplay.html()}</a></div>
    </div></section>
  </main>
  <footer class="container"><p>© <span id="year">$year</span> ${c.legalName.html()}</p><a href="#home">${t.backToTop.html()} ↑</a></footer>
</body>
</html>
""".trimIndent()
}
