document.addEventListener("DOMContentLoaded", function () {
  /*--------------------------------
  # General
  ---------------------------------*/
  // 取消按下anchor後畫面會上移;

  /*--------------------------------
  # Header
  ---------------------------------*/
  let toggleSidebar_btn_el = document.querySelector(".toggle-sidebar-btn");
  let bannerClose_btn_el = document.querySelector(".banner-close-custom");

  /*--------------------------------
  # Sidebar
  ---------------------------------*/

  var anchorElements = document.querySelectorAll(".cancel-default-behavior");

  // 對每個錨點元素添加點擊事件處理
  anchorElements.forEach(function (anchorElement) {
    anchorElement.addEventListener("click", function (event) {
      // 阻止默認行為，這樣不會觸發畫面的滾動
      event.preventDefault();
    });
  });

  // 由於並非每個版都有toggleSideBar，因此需確認不為空才綁定，否則console會出現一堆錯誤訊息
  if (toggleSidebar_btn_el) {
    toggleSidebar_btn_el.addEventListener("click", function () {
      // console.log(toggleSidebar_btn_el);
      document.querySelector(".main").classList.toggle("toggle-sidebar");
      document.querySelector(".footer").classList.toggle("toggle-sidebar");
      document.querySelector(".sidebar").classList.toggle("toggle-sidebar");
    });
  }

  /*--------------------------------
  # BannerText
  ---------------------------------*/

  if (bannerClose_btn_el) {
    bannerClose_btn_el.addEventListener("click", function () {
      this.closest(".banner-container-custom").remove();
    });
  }

  /*--------------------------------
  # SellerLevel
  ---------------------------------*/

  /*--------------------------------------------------------------
# UploadPicture
--------------------------------------------------------------*/
});

/*--------------------------------------------------------------
# Function Area (Module)
--------------------------------------------------------------*/
