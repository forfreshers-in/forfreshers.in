
$(function() {
    "use strict";
     
	 
//sidebar menu js
$.sidebarMenu($('.sidebar-menu'));

// === toggle-menu js
$(".toggle-menu").on("click", function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });	 
	   
// === sidebar menu activation js

$(function() {
        for (var i = window.location, o = $(".sidebar-menu a").filter(function() {
            return this.href == i;
        }).addClass("active").parent().addClass("active"); ;) {
            if (!o.is("li")) break;
            o = o.parent().addClass("in").parent().addClass("active");
        }
    }), 	   
	   

/* Top Header */

$(document).ready(function(){ 
    $(window).on("scroll", function(){ 
        if ($(this).scrollTop() > 60) { 
            $('.topbar-nav .navbar').addClass('bg-dark'); 
        } else { 
            $('.topbar-nav .navbar').removeClass('bg-dark'); 
        } 
    });

 });


/* Back To Top */

$(document).ready(function(){ 
    $(window).on("scroll", function(){ 
        if ($(this).scrollTop() > 300) { 
            $('.back-to-top').fadeIn(); 
        } else { 
            $('.back-to-top').fadeOut(); 
        } 
    }); 

    $('.back-to-top').on("click", function(){ 
        $("html, body").animate({ scrollTop: 0 }, 600); 
        return false; 
    }); 
});	   
	    
   
$(function () {
  $('[data-toggle="popover"]').popover()
})


$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

	// setting default theme
	let themeCookie = getCookie("ADM_BG")
	if(themeCookie != null){
		 $('body').attr('class', themeCookie);
	}
	 // theme setting
	 $(".switcher-icon").on("click", function(e) {
        e.preventDefault();
        $(".right-sidebar").toggleClass("right-toggled");
    });
	
	$('#theme1').click(theme1);
    $('#theme2').click(theme2);
    $('#theme3').click(theme3);
    $('#theme4').click(theme4);
    $('#theme5').click(theme5);
    $('#theme6').click(theme6);
    $('#theme7').click(theme7);
    $('#theme8').click(theme8);
    $('#theme9').click(theme9);
    $('#theme10').click(theme10);
    $('#theme11').click(theme11);
    $('#theme12').click(theme12);
    $('#theme13').click(theme13);
    $('#theme14').click(theme14);
    $('#theme15').click(theme15);

    function theme1() {
      $('body').attr('class', 'bg-theme bg-theme1');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme1")
    }

    function theme2() {
      $('body').attr('class', 'bg-theme bg-theme2');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme2")
    }

    function theme3() {
      $('body').attr('class', 'bg-theme bg-theme3');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme3")
    }

    function theme4() {
      $('body').attr('class', 'bg-theme bg-theme4');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme4")
    }
	
	function theme5() {
      $('body').attr('class', 'bg-theme bg-theme5');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme5")
    }
	
	function theme6() {
      $('body').attr('class', 'bg-theme bg-theme6');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme6")
    }

    function theme7() {
      $('body').attr('class', 'bg-theme bg-theme7');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme7")
    }

    function theme8() {
      $('body').attr('class', 'bg-theme bg-theme8');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme8")
    }

    function theme9() {
      $('body').attr('class', 'bg-theme bg-theme9');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme9")
    }

    function theme10() {
      $('body').attr('class', 'bg-theme bg-theme10');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme10")
    }

    function theme11() {
      $('body').attr('class', 'bg-theme bg-theme11');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme11")
    }

    function theme12() {
      $('body').attr('class', 'bg-theme bg-theme12');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme12")
    }
	
	function theme13() {
      $('body').attr('class', 'bg-theme bg-theme13');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme13")
    }
	
	function theme14() {
      $('body').attr('class', 'bg-theme bg-theme14');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme14")
    }
	
	function theme15() {
      $('body').attr('class', 'bg-theme bg-theme15');
      setLifetimeCookie("ADM_BG","bg-theme bg-theme15")
    }
});

// setting long term cookie
function setLifetimeCookie(name, value) {
    var date = new Date();
    date.setFullYear(date.getFullYear() + 10); // Set the cookie to expire in 10 years
    var expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
}

// delete cookie
function deleteCookie(name) {
    document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

// get Cookie
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}
