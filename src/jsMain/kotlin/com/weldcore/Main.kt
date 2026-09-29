package com.weldcore

// Small browser interop boundary; content and slideshow state remain typed Kotlin.
private external val document: dynamic
private external val window: dynamic

fun main() {
    document.documentElement.classList.add("js")
    val menu = document.querySelector(".menu-toggle")
    val navigation = document.querySelector("#navigation")
    menu.hidden = false

    fun closeMenu() {
        navigation.classList.remove("open")
        menu.setAttribute("aria-expanded", "false")
    }
    menu.addEventListener("click", { _: dynamic ->
        val expanded: Boolean = navigation.classList.toggle("open")
        menu.setAttribute("aria-expanded", expanded.toString())
    })
    navigation.addEventListener("click", { event: dynamic ->
        if (event.target.closest("a") != null) closeMenu()
    })
    document.addEventListener("keydown", { event: dynamic ->
        if (event.key == "Escape" && navigation.classList.contains("open") == true) {
            closeMenu()
            menu.focus()
        }
    })
    document.querySelector("#year").textContent = kotlin.js.Date().getFullYear().toString()

    val galleries = document.querySelectorAll(".product-gallery")
    for (i in 0 until (galleries.length as Int)) {
        val gallery = galleries.item(i)
        gallery.addEventListener("click", { event: dynamic ->
            val thumbnail = event.target.closest(".product-thumbnail")
            if (thumbnail != null && event.ctrlKey != true && event.metaKey != true && event.shiftKey != true && event.altKey != true) {
                event.preventDefault()
                val image = gallery.querySelector(".product-photo")
                image.src = thumbnail.getAttribute("href")
                image.alt = thumbnail.querySelector("img").alt
                val links = gallery.querySelectorAll(".product-thumbnail")
                for (j in 0 until (links.length as Int)) links.item(j).removeAttribute("aria-current")
                thumbnail.setAttribute("aria-current", "true")
            }
        })
    }
    startSlideshow()
}

private fun startSlideshow() {
    val slides = siteContent.slides
    if (slides.size < 2) return
    val banner = document.querySelector(".hero-image")
    val image = banner.querySelector("img")
    val motion = window.matchMedia("(prefers-reduced-motion: reduce)")
    var index = 0
    var timer: Int? = null

    fun stop() {
        timer?.let { window.clearInterval(it) }
        timer = null
    }
    fun schedule() {
        stop()
        if (motion.matches != true && document.hidden != true && banner.matches(":hover") != true) {
            timer = window.setInterval({
                index = (index + 1) % slides.size
                image.src = slides[index].path
                image.alt = slides[index].alt
            }, siteContent.hero.slideIntervalMs) as Int
        }
    }
    banner.addEventListener("mouseenter", { _: dynamic -> stop() })
    banner.addEventListener("mouseleave", { _: dynamic -> schedule() })
    document.addEventListener("visibilitychange", { _: dynamic -> schedule() })
    motion.addEventListener("change", { _: dynamic -> schedule() })
    schedule()
}
