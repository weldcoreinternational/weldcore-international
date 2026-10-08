package com.weldcore

// Edit this file to update the site. Put image files in src/jsMain/resources/assets/.
private val company = Company(
    legalName = "Weldcore International Trading - F.Z.C",
    location = "Ajman, United Arab Emirates",
    address = listOf("B.C. 1308570, Ajman Free Zone C1 Building,", "Ajman Free Zone, Ajman,", "United Arab Emirates"),
    phone = "+971561787193",
    phoneDisplay = "+971 56 178 7193",
    infoEmail = "info@weldcoreinternational.com",
    salesEmail = "sales@weldcoreinternational.com",
    logo = Photo("./assets/weldcore-logo.png", "Weldcore International Trading FZC — Connecting Quality. Powering Performance.")
)

val siteContent = SiteContent(
    company = company,
    description = "${company.legalName} — your trusted partner in engineering solutions. Spot welding nozzles, Class II control transformers, springs, brass and bronze bushes, machinery and spare parts.",
    hero = Hero(
        heading = "Your Trusted Partner in",
        highlightedHeading = "Engineering Solutions",
        description = "High-quality engineering products and industrial solutions tailored to the specific requirements of our customers.",
        caption = "QUALITY. RELIABILITY. CUSTOMER SATISFACTION.",
        tagline = "Engineering Solutions You Can Trust.",
        specialties = listOf("Spot welding nozzles & accessories", "Customized industrial solutions", "Machinery & spare parts"),
        slideIntervalMs = 4500
    ),
    about = listOf(
        "Welcome to ${company.legalName}, a trusted partner in supplying high-quality engineering products and industrial solutions. We specialize in spot welding nozzles, Class II control transformers, Stainless Steel Constant Springs, brass and bronze bushes, machinery, spare parts, and other engineering components.",
        "With a steadfast commitment to quality, reliability, and customer satisfaction, we provide practical and cost-effective solutions tailored to the specific requirements of our customers.",
        "Our expertise and strong supplier network enable us to support a wide range of industries with dependable products and responsive service. We continuously strive to understand our customers’ technical and operational requirements and deliver solutions that meet their expectations.",
        "At ${company.legalName}, we believe in building long-term partnerships through quality products, competitive solutions, and reliable service."
    ),
    products = listOf(
        Product(
            name = "Spot Welding Nozzles & Accessories",
            photos = listOf(Photo("./assets/spot-welding-tips.jpg", "Pointed copper spot welding electrode tips"),
                Photo("./assets/spot-welding-caps.jpg", "Copper spot welding electrode caps")),
            illustrative = false,
            isSolution = false
        ),
        Product(
            name = "Control Transformers Class II",
            photos = listOf(
                Photo("./assets/customized-transformer-angle.jpg", "Transformer with colored leads, angled view"),
                Photo("./assets/customized-transformer-mount.jpg", "Transformer mounting bracket and wiring"),
                Photo("./assets/customized-transformer-front.jpg", "Transformer front view with specification label")
            ),
            illustrative = false,
            isSolution = false
        ),
        Product(
            name = "Stainless Steel Constant Spring",
            photos = listOf(Photo("./assets/coiled-strip-spring.jpg", "Coiled metal strip spring"),
                Photo("./assets/industrial-strip-springs.jpg", "Industrial coiled strip springs with mounting holes")),
            illustrative = false,
            isSolution = false
        ),
        Product(
            name = "Brass & Bronze Bushes",
            photos = listOf(Photo("./assets/flanged-bushes.jpg", "Two flanged metal bushes"),
                Photo("./assets/square-bore-bushes.jpg", "Brass-colored bushes with square openings"),
                Photo("./assets/assorted-bushes.jpg", "Assorted flanged and square-bore metal bushes")),
            illustrative = false,
            isSolution = false
        ),
        Product(
            name = "Machinery & Spare Parts",
            photos = listOf(Photo("./assets/placeholder-machinery.jpg", "Interlocking machinery gears — illustrative stock photo")),
            illustrative = true,
            isSolution = false
        ),
        Product(
            name = "Engineering Goods & Components",
            photos = listOf(Photo("./assets/placeholder-components.jpg", "Assorted metal spare parts — illustrative stock photo")),
            illustrative = true,
            isSolution = false
        )
    ),
    values = listOf(
        CompanyValue("Value & Trust", listOf(
            "We believe that successful business relationships are built on transparency, trust, and genuine value. We work to understand the purpose behind each business requirement and focus on providing solutions that deliver the best value to our customers.",
            "Our commitment is to offer quality products, competitive pricing, reliable service, and practical engineering solutions while maintaining honesty and transparency throughout every transaction.",
            "We don’t just supply products — we build long-term partnerships based on value and trust."
        )),
        CompanyValue("Product & Service Excellence", listOf(
            "We are committed to delivering high-quality products and reliable services that meet the evolving needs of our customers. Through continuous improvement, careful supplier selection, and quality-focused processes, we strive to ensure consistency and reliability in everything we provide.",
            "Our goal is to meet or exceed applicable industry standards and regulatory requirements while continuously improving our products, services, and customer experience.",
            "Quality is not just our commitment — it is the standard we set for everything we deliver."
        )),
        CompanyValue("Supplier Collaboration", listOf(
            "We build and maintain strong, long-term relationships with trusted suppliers to ensure a reliable and consistent supply of quality products. Through close collaboration, open communication, and mutual understanding, we work together to meet technical, quality, and delivery requirements.",
            "Our strong supplier network enables us to provide customers with consistent product quality, competitive solutions, and dependable delivery while continuously exploring better products and solutions to meet evolving industry needs.",
            "Strong partnerships with our suppliers help us deliver greater value to our customers."
        )),
        CompanyValue("Continuous Improvement", listOf(
            "We embrace a culture of continuous improvement across every level of our organization. Through regular reviews, performance assessments, customer feedback, and ongoing evaluation of our processes, we identify opportunities to improve our products, services, and overall efficiency.",
            "We take a proactive approach to addressing challenges, implementing practical improvements, and adapting to changing customer and industry requirements.",
            "Our commitment to continuous improvement helps us deliver better quality, greater efficiency, and increased value to our customers.",
            "We continuously improve today to deliver better solutions tomorrow."
        )),
        CompanyValue("Compliance", listOf(
            "We are committed to conducting our business with integrity, transparency, and accountability while complying with all applicable legal, regulatory, and industry requirements.",
            "Our quality management processes are designed to support consistent compliance, responsible business practices, and high ethical standards across our operations and supplier network.",
            "We continuously review our processes to ensure that our business activities are conducted responsibly and in accordance with applicable requirements.",
            "Integrity and compliance are fundamental to the way we do business."
        ))
    ),
    navigation = listOf(NavLink("home", "Home"), NavLink("about", "About"), NavLink("products", "Products"), NavLink("services", "Why Choose Us"), NavLink("contact", "Contact")),
    copy = SiteCopy(
        skipLink = "Skip to content", menu = "Menu", homeLabel = "Weldcore International Trading home",
        exploreProducts = "Explore our products", talkToTeam = "Talk to our team",
        welcome = "WELCOME TO WELDCORE", productsEyebrow = "ENGINEERING PRODUCTS & INDUSTRIAL SOLUTIONS",
        productsHeading = "Our Products & Solutions", discussRequirements = "Discuss your requirements",
        valuesEyebrow = "QUALITY, RELIABILITY & VALUE", valuesHeading = "Why Choose Us?",
        contactEyebrow = "LET’S WORK TOGETHER", contactHeading = "Start your next project with us.",
        quoteHeading = "Request a quote", quoteInstructions = "Email your product requirements, quantities, and delivery location to our sales team.",
        emailSales = "Email our sales team", addressHeading = "Registered address & contact",
        backToTop = "Back to top", illustrativeImage = "Illustrative image",
        productEnquiry = "Enquire about this product", solutionEnquiry = "Enquire about this solution",
        enquirySubject = "Enquiry: ", generalEnquirySubject = "Product enquiry",
        photoGallery = "Product photos", viewPhoto = "View photo: "
    )
)
