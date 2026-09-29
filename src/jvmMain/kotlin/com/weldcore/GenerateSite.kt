package com.weldcore

import java.io.File
import java.time.Year

fun main(args: Array<String>) {
    val output = File(args.single()).apply { mkdirs() }
    File(output, "index.html").writeText(renderPage(siteContent, Year.now().value))
}
