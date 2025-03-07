(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Initiate the wowjs
    new WOW().init();


    
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Header carousel
    $(".header-carousel").owlCarousel({
		animateOut:'fadeOut',
        autoplay: true,
        smartSpeed: 1500,
        loop: true,
        dots: false,
        items: 1,
		mouseDrag:false,
		touchDrag:false
    });


    // Facts counter
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });
	// Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: false,
        smartSpeed: 1000,
        margin: 25,
        loop: true,
        center: true,
        dots: false,
        nav: true,
        navText : [
            '<i class="bi bi-chevron-left"></i>',
            '<i class="bi bi-chevron-right"></i>'
        ],
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            }
        }
    });
	
	
	//custom-list
	const swiper = new Swiper('.main-slide-list', {
		loop: false,
		navigation: false,

		breakpoints: {
			0:{
				slidesPerView: 2,  //브라우저가 0보다 클 때
				spaceBetween: 10,
			},
			768: {
				slidesPerView: 3,  //브라우저가 768보다 클 때
				spaceBetween: 10,
			},
			991: {
				slidesPerView: 4,  //브라우저가 991보다 클 때
				spaceBetween: 10,
			},
		},
	});
	
	//tab
	$(document).on('click','.tab-nav-item',function(){
		$(this).siblings('.tab-nav-item').removeClass('active');
		$(this).addClass('active');
		let tabid=$(this).data('tab');
		$(this).closest('.tab-nav-wrap').next('.tab-content-wrap').find('.tab-cont-item').removeClass('active');
		$(this).closest('.tab-nav-wrap').next('.tab-content-wrap').find('[data-tab="'+tabid+'"]').addClass('active');
	});
    
	$(window).scroll(function () {
        if ($(this).scrollTop()) {
            $('.fixed-top').addClass('bg-white');
        } else {
            $('.fixed-top').removeClass('bg-white');
        }
    });
	let btn=document.querySelector('button[data-bs-target="#navbarCollapse"]');
	btn.addEventListener('click',function(){
		document.querySelector('.navbar').classList.toggle('on');
	});
	
	
    
})(jQuery);

