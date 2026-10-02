# Weldcore International Trading - F.Z.C

A Kotlin Multiplatform project with a Kotlin/JS browser application and a small JVM HTML generator. The production result is a static GitHub Pages website. There is no backend or database.

## Update website content

Edit **`src/commonMain/kotlin/com/weldcore/SiteContent.kt`**. Its typed data classes contain:

- Company legal name, address, phone, emails and logo.
- Hero headings, tagline, specialties and slideshow interval.
- About paragraphs, navigation labels and contact copy.
- Products, image paths, image descriptions and placeholder flags.
- The complete “Why Choose Us” statements.

The model definitions are in `Models.kt`. Add a product by adding another entry to `products`:

```kotlin
Product(
    name = "New product",
    photos = listOf(Photo("./assets/new-product.jpg", "Description of the photo")),
    illustrative = false
)
```

Put photos in **`src/jsMain/resources/assets/`**. Set `illustrative = true` for stock placeholders. All non-placeholder product photos automatically join the banner slideshow; it has no visible controls and respects reduced-motion preferences. Product cards may have multiple photos or no photos.

Change the theme in **`src/jsMain/resources/styles.css`**. Photo credits are in `src/jsMain/resources/assets/IMAGE-SOURCES.md`.

Data is stored in Kotlin source, not browser storage. After an edit, rebuild and manually publish to update the live site. Contact buttons use email and phone links; no form service is configured.

## Requirements and local preview

Install **JDK 21 or newer** and set `JAVA_HOME` to its directory. Gradle 9.1.0 is pinned in the included wrapper; it downloads the required Kotlin and JavaScript tooling on the first build. No separate Gradle installation is needed.

Windows PowerShell:

```powershell
.\gradlew.bat jvmTest jsBrowserDistribution
.\gradlew.bat jsBrowserDevelopmentRun --continuous
```

macOS / Linux:

```sh
bash ./gradlew jvmTest jsBrowserDistribution
bash ./gradlew jsBrowserDevelopmentRun --continuous
```

The development task serves the site locally and reports its URL. The finished site is in **`build/dist/js/productionExecutable/`**. You can also open its `index.html` directly after a production build. Do not edit generated files in `build/`.

## Publish to GitHub Pages — manual only

1. Commit and push the project, including `gradlew`, `gradlew.bat`, `gradle/wrapper/` and `.github/workflows/publish-pages.yml`.
2. In the repository, set **Settings → Pages → Source → GitHub Actions**. Switch away from branch publishing if it was previously enabled, so pushes cannot publish the old static site.
3. Ensure the workflow exists on the repository’s default branch.
4. Open **Actions → Publish GitHub Pages (manual) → Run workflow**, select the branch and run it.
5. The workflow tests the content renderer, builds Kotlin/JS, uploads the production directory and deploys it to Pages. The deployment job shows the site URL.

The only workflow trigger is `workflow_dispatch`. Pushing commits, opening pull requests and editing content do **not** automatically run this workflow. The built-in GitHub token is used; no personal access token is required. GitHub environment protection rules may require approval if configured by the repository owner.

Relative links support both `username.github.io/repository/` and custom domains. To configure a custom domain, follow GitHub Pages settings and add a `CNAME` file to `src/jsMain/resources/` if required.

## Project structure

```text
src/commonMain/kotlin/com/weldcore/
  Models.kt          Typed content models
  SiteContent.kt     Editable website data
  Html.kt            Shared HTML rendering and escaping
src/jsMain/kotlin/com/weldcore/Main.kt
                     Kotlin/JS menu, galleries and slideshow
src/jsMain/resources/
  styles.css         Website styles
  assets/            Local images and source credits
  .nojekyll          Static hosting marker
src/jvmMain/kotlin/com/weldcore/GenerateSite.kt
                     Generates HTML at build time
src/commonTest/kotlin/com/weldcore/SiteTest.kt
                     Content, escaping and link tests
.github/workflows/publish-pages.yml
                     Manually triggered Pages build and deployment
```

The JVM generator and browser code share the same data classes. HTML is generated during resource processing, so all text, product cards and contact links are present before JavaScript runs. JavaScript only adds interactive behaviour. This preserves navigation and content when scripts are disabled.
