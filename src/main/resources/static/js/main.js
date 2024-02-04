document.addEventListener("DOMContentLoaded", function () {
  //   Header
  let toggleSidebar_btn_el = document.querySelector(".toggle-sidebar-btn");
  let bannerClose_btn_el = document.querySelector(".banner-close-custom");
  // Main-Product

  // Header JS
  toggleSidebar_btn_el.addEventListener("click", function () {
    // console.log(toggleSidebar_btn_el);
    document.querySelector(".main").classList.toggle("toggle-sidebar");
    document.querySelector(".footer").classList.toggle("toggle-sidebar");
    document.querySelector(".sidebar").classList.toggle("toggle-sidebar");
  });

  bannerClose_btn_el.addEventListener("click", function () {
    this.closest(".banner-container-custom").remove();
  });

  // Main-Product-JS
});
