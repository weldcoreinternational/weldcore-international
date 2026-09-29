'use strict';

document.documentElement.classList.add('js');
const menuButton = document.querySelector('.menu-toggle');
const navigation = document.querySelector('#navigation');
menuButton.hidden = false;

function closeMenu() {
  navigation.classList.remove('open');
  menuButton.setAttribute('aria-expanded', 'false');
}

menuButton.addEventListener('click', () => {
  const expanded = navigation.classList.toggle('open');
  menuButton.setAttribute('aria-expanded', String(expanded));
});
navigation.addEventListener('click', (event) => {
  if (event.target.closest('a')) closeMenu();
});
document.addEventListener('keydown', (event) => {
  if (event.key === 'Escape' && navigation.classList.contains('open')) {
    closeMenu();
    menuButton.focus();
  }
});
document.querySelector('#year').textContent = new Date().getFullYear();

// Thumbnail links also open the original photos when JavaScript is unavailable.
document.querySelectorAll('.product-gallery').forEach((gallery) => {
  gallery.addEventListener('click', (event) => {
    const thumbnail = event.target.closest('.product-thumbnail');
    if (!thumbnail || event.ctrlKey || event.metaKey || event.shiftKey || event.altKey) return;
    event.preventDefault();
    const photo = gallery.querySelector('.product-photo');
    photo.src = thumbnail.getAttribute('href');
    photo.alt = thumbnail.querySelector('img').alt;
    gallery.querySelectorAll('.product-thumbnail').forEach((link) => link.removeAttribute('aria-current'));
    thumbnail.setAttribute('aria-current', 'true');
  });
});

// Automatically rotate through the supplied product photographs.
const banner = document.querySelector('.hero-image');
const bannerPhoto = banner.querySelector('img');
const productSlides = Array.from(document.querySelectorAll('.product-thumbnail')).map((link) => ({
  src: link.getAttribute('href'),
  alt: link.querySelector('img').alt
}));
const reducedMotion = window.matchMedia('(prefers-reduced-motion: reduce)');
let slideIndex = 0;
let paused = reducedMotion.matches;
let slideTimer;

function showSlide(index) {
  slideIndex = (index + productSlides.length) % productSlides.length;
  bannerPhoto.src = productSlides[slideIndex].src;
  bannerPhoto.alt = productSlides[slideIndex].alt;
}
function scheduleSlides() {
  clearInterval(slideTimer);
  if (!paused && !document.hidden && !banner.matches(':hover')) {
    slideTimer = setInterval(() => showSlide(slideIndex + 1), 4500);
  }
}
if (productSlides.length > 1) {
  banner.addEventListener('mouseenter', () => clearInterval(slideTimer));
  banner.addEventListener('mouseleave', scheduleSlides);
  banner.addEventListener('focusin', () => { paused = true; scheduleSlides(); });
  document.addEventListener('visibilitychange', scheduleSlides);
  reducedMotion.addEventListener('change', () => {
    paused = reducedMotion.matches;
    scheduleSlides();
  });
  showSlide(0);
  scheduleSlides();
}
