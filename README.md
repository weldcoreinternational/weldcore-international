# Weldcore International Trading - F.Z.C

Static website converted from the supplied WordPress homepage. Plain HTML, CSS and JavaScript; no build step, package installation, WordPress server or external runtime dependencies.

## Preview

Open `index.html` directly in a browser.

## Publish on GitHub Pages

1. Upload `index.html`, `styles.css`, `script.js`, `.nojekyll` and the complete `assets` folder to the root of your GitHub repository.
2. In the repository, open **Settings → Pages**.
3. Select **Deploy from a branch**, choose the branch containing these files (usually `main`), choose **/ (root)** and save.
4. GitHub will display the published URL after deployment finishes.

All asset paths are relative, so the website supports both a GitHub project URL and a custom domain. No domain is preconfigured.

## Editing

- Company information, products and contact details: `index.html`.
- Colors, layout and responsive styles: `styles.css`.
- Mobile navigation and copyright year: `script.js`.
- Images: `assets/` (downloaded from image URLs in the supplied source).

Company copy was updated using the supplied Weldcore information: seven product and solution categories and the five complete Why Choose Us statements. The supplied product photos appear in thumbnail galleries for welding accessories, springs and bushes. The remaining four categories use locally stored stock placeholders, marked as illustrative images; see assets/IMAGE-SOURCES.md for source links. The hero uses the supplied welding cap photo. The legal name, registered address in Ajman Free Zone and contact number were updated from the supplied registration details. Existing email addresses are retained. Registration and VAT dates were not supplied. WordPress plugins, old-domain metadata and analytics were removed.

Quote requests open the visitor's email application using `mailto:`. No form submission service or backend is configured. Phone links open the device's calling application. The supplied file contains only the homepage, so linked WordPress subpages are not included.
