$(document).ready(function() {
    var mySwiper = new Swiper(".mySwiper", {
      // 2초의 딜레이로 자동재생 기능 활성화
      autoplay: {
        delay: 2000,
      },
      // 무한 반복 기능 활성화
      loop: true,
      // 이전,다음 슬라이드를 변경할 수 있는 화살표 표시
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
      },
    });
  });