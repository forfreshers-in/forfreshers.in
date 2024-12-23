
// Burger menus
document.addEventListener('DOMContentLoaded', function() {
	// open
	const burger = document.querySelectorAll('.navbar-burger');
	const menu = document.querySelectorAll('.navbar-menu');

	if (burger.length && menu.length) {
		for (var i = 0; i < burger.length; i++) {
			burger[i].addEventListener('click', function() {
				for (var j = 0; j < menu.length; j++) {
					menu[j].classList.toggle('d-none');
				}
			});
		}
	}

	// close
	const close = document.querySelectorAll('.navbar-close');
	const backdrop = document.querySelectorAll('.navbar-backdrop');

	if (close.length) {
		for (var i = 0; i < close.length; i++) {
			close[i].addEventListener('click', function() {
				for (var j = 0; j < menu.length; j++) {
					menu[j].classList.toggle('d-none');
				}
			});
		}
	}

	if (backdrop.length) {
		for (var i = 0; i < backdrop.length; i++) {
			backdrop[i].addEventListener('click', function() {
				for (var j = 0; j < menu.length; j++) {
					menu[j].classList.toggle('d-none');
				}
			});
		}
	}
});


// popover options (menu)
$(document).ready(function() {

	$('[data-po="popover"]').mouseenter(function() {
		$(this).children(".popover-content").slideDown(100);
	})
	$('[data-po="popover"]').mouseleave(function() {
		$(this).children(".popover-content").slideUp(100);
	})
})

// Function to set a cookie
function setCookie(name, value, days) {
	var expires = "";
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toUTCString();
	}
	document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

// Function to get a cookie
function getCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
	}
	return null;
}

// FOR COOKIES
$(document).ready(function() {
	// Function to check user consent
	function checkConsent() {
		let cookie = `      
	<div class="position-fixed bottom-0 start-0 col-12" id="cookie-banner" style="z-index: 50;">
        <div class="d-flex flex-wrap align-items-start col-12">
          <div class="container py-md-0 py-2 pt-lg-2" col-12="">
            <div class="py-2 py-md-6 px-0 px-sm-2 px-md-12 px-lg-6 w-100 bg-dark border rounded-3 rounded-pill text-white d-flex justify-content-center position-relative">
<button class="btn btn-secondary p-1 px-4 position-absolute border-1 border-light" id="cookie-banner-hide-btn" style="
    right: 50%;
    top: -20px;
    transform: translateX(50%);
"> <i class="fa fa-close"></i> </button>
              <div class="row align-items-center col-12">
                <div class="col-12 col-md-7 col-lg-9 mb-4 mb-md-0">
                  <div class=" d-flex align-items-center">
                    <span>
                      <svg width="34" height="34" viewBox="0 0 34 34" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M14.5 13.6667C15.1945 13.6667 15.785 13.4233 16.2717 12.9367C16.7583 12.45 17.0011 11.86 17 11.1667C17 10.4722 16.7567 9.88166 16.27 9.39499C15.7833 8.90833 15.1933 8.66555 14.5 8.66666C13.8056 8.66666 13.215 8.91 12.7283 9.39666C12.2417 9.88333 11.9989 10.4733 12 11.1667C12 11.8611 12.2433 12.4517 12.73 12.9383C13.2167 13.425 13.8067 13.6678 14.5 13.6667ZM11.1667 22C11.8611 22 12.4517 21.7567 12.9383 21.27C13.425 20.7833 13.6678 20.1933 13.6667 19.5C13.6667 18.8056 13.4233 18.215 12.9367 17.7283C12.45 17.2417 11.86 16.9989 11.1667 17C10.4722 17 9.88168 17.2433 9.39501 17.73C8.90834 18.2167 8.66557 18.8067 8.66668 19.5C8.66668 20.1944 8.91001 20.785 9.39668 21.2717C9.88334 21.7583 10.4733 22.0011 11.1667 22ZM22 23.6667C22.4722 23.6667 22.8683 23.5067 23.1883 23.1867C23.5083 22.8667 23.6678 22.4711 23.6667 22C23.6667 21.5278 23.5067 21.1317 23.1867 20.8117C22.8667 20.4917 22.4711 20.3322 22 20.3333C21.5278 20.3333 21.1317 20.4933 20.8117 20.8133C20.4917 21.1333 20.3322 21.5289 20.3333 22C20.3333 22.4722 20.4933 22.8683 20.8133 23.1883C21.1333 23.5083 21.5289 23.6678 22 23.6667ZM17 33.6667C14.6945 33.6667 12.5278 33.2289 10.5 32.3533C8.47223 31.4778 6.70834 30.2906 5.20834 28.7917C3.70834 27.2917 2.52112 25.5278 1.64668 23.5C0.772233 21.4722 0.334455 19.3056 0.333344 17C0.333344 14.6667 0.833344 12.3889 1.83334 10.1667C2.83334 7.94444 4.22945 6.02777 6.02168 4.41666C7.81168 2.80555 9.93557 1.62499 12.3933 0.874994C14.8511 0.124994 17.5389 0.0555499 20.4567 0.666661C20.2067 1.91666 20.29 3.09055 20.7067 4.18833C21.1233 5.28611 21.7483 6.20944 22.5817 6.95833C23.415 7.70833 24.4011 8.22222 25.54 8.5C26.6789 8.77777 27.8733 8.70833 29.1233 8.29166C28.2622 10.2083 28.415 11.8472 29.5817 13.2083C30.7483 14.5694 32.0822 15.2778 33.5833 15.3333C33.8333 17.8056 33.5695 20.1528 32.7917 22.375C32.0139 24.5972 30.8678 26.5417 29.3533 28.2083C27.84 29.875 26.0206 31.2017 23.895 32.1883C21.7695 33.175 19.4711 33.6678 17 33.6667Z" fill="white"></path>
                      </svg>
                    </span>
    <div>
                    <h5 class="ps-6 text-primary">We Value your privacy</h5>
                    <p class="ms-6 mb-0 small ">We use cookies to enhance your browsing experience, serve personalized ads or content, and analyze our traffic. By clicking "Accept All", you consent to our use of cookies.<a href="javascript:void(0)" id="cookies-policy-btn"> cookies-policy</a></p>
</div>
                  </div>
                </div>
                <div class="col-12 col-md-4 col-lg-3">
                  <div class="d-flex justify-content-evenly g-4 d-flex flex-nowrap">
                    <div class="col-6 col-md-auto text-center">
                      <div class="d-inline-block bg-white rounded-pill"><a class="btn btn-sm py-4 btn-outline-light fs-6 text-dark w-100" id="decline-cookies" href="javascript:void(0)">Decline</a></div>
                    </div>
                    <div class="col-6 col-md-auto text-center"><a class="btn btn-sm py-4 px-8 btn-primary fs-6 w-100" id="accept-cookies" href="javascript:void(0)">Allow</a></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
	 `
			;

		if (getCookie('cookies_concern') !== 'true' && sessionStorage.getItem("cookies_concern") == null) {
			var e = $("div");
			e.innerHTML = cookie;
			$("body").append(cookie);
			$("#cookie-banner").delay(5000).slideDown(1000);
		}
	}

	// Check for consent on page load
	checkConsent();

	// When the user clicks "Yes"
	$('#accept-cookies').on("click", function() {
		setCookie('cookies_concern', 'true', 365); // Store acceptance for 1 year
		$('#cookie-banner').delay(200).slideUp(200);

	});

	// When the user clicks "No"
	$('#decline-cookies').on("click", function() {
		$('#cookie-banner').delay(200).slideUp(200);
		sessionStorage.setItem("cookies_concern", false);
	});

	$('#cookie-banner-hide-btn').on("click", function() {
		$('#cookie-banner').delay(200).slideUp(200);
	});

	$('#cookies-policy-btn').on("click", function() {
		let location = window.location.origin + "/cookies-policy";
		window.open(location);
	});




})

// For test scrolling pages
$(document).ready(function() {
	ScrollReveal().reveal('.scroll-reveal-200', { delay: 200, reset: true });
	ScrollReveal().reveal('.scroll-reveal-400', { delay: 400, reset: true });
	ScrollReveal().reveal('.scroll-reveal-600', { delay: 600, reset: true });
	ScrollReveal().reveal('.scroll-reveal-800', { delay: 800, reset: true });
	ScrollReveal().reveal('.scroll-reveal-1000', { delay: 1000, reset: true });
})

// for lenis scrol lib
const lenis = new Lenis({
	// Valeur entre 0 et 1
	// Valeur par défaut : 0,1
	// Plus la valeur est faible, plus le scroll sera fluide
	lerp: 0.05,
	// Valeur par défaut : 1
	// Plus la valeur est haute, plus le défilement sera rapide 
	wheelMultiplier: 1,
})

lenis.on('scroll', (e) => {

})

function raf(time) {
	lenis.raf(time)
	requestAnimationFrame(raf)
}

requestAnimationFrame(raf)

// function for find is mobile or not
function isMobile() {
  const regex = /Mobi|Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i;
  return regex.test(navigator.userAgent);
}

// mouse folloer
$(document).ready(function() {
    // Initialize Mouse Follower with skewing
    let cursor =null;
    if(!isMobile()){
		
	    cursor = new MouseFollower({
	        container: "body",
	        skewing: 2, // Adjust skewing effect
	        skewingText: 2,
	        speed: 0.55,
	        class: "mf-cursor mf-cursor-cus"
	    });
	}

    // Select all buttons with the .sticky-button class
    const buttons = document.querySelectorAll('.sticky-button');
    
    buttons.forEach((button) => {
        button.addEventListener('mouseenter', () => {
            document.body.classList.add('has-cursor-hover'); // Add class to scale cursor
            gsap.to(button, { 
                scale: 1.1, 
                ease: "expo.out", 
                duration: 0.6 
            });
        });

        button.addEventListener('mouseleave', () => {
            document.body.classList.remove('has-cursor-hover'); // Remove class to reset cursor size
            gsap.to(button, { 
                scale: 1, 
                ease: "expo.out", 
                duration: 0.6 
            });
        });

        button.addEventListener('mousemove', (e) => {
            const rect = button.getBoundingClientRect();
            const x = e.clientX - rect.left - rect.width / 2;
            const y = e.clientY - rect.top - rect.height / 2;

            gsap.to(button, {
                x: x * 0.3, // Adjust for stronger pull effect
                y: y * 0.3,
                rotation: x * 0.05, // Adds a slight rotation for skewing effect
                ease: "power3.out"
            });
        });

        button.addEventListener('mouseout', () => {
            gsap.to(button, { 
                x: 0, 
                y: 0, 
                rotation: 0, 
                ease: "power3.out"
            });
        });
    });

    // Apply the sticky effect to all elements with the .sticky-element class
    const stickyElements = document.querySelectorAll('.sticky-element');
    
    stickyElements.forEach((targetElement) => {
        targetElement.addEventListener('mouseenter', () => {
            cursor.setStick(targetElement); // Stick the cursor to the element
        });
        targetElement.addEventListener('mouseleave', () => {
            cursor.removeStick(); // Remove the sticky effect when leaving the element
        });
    });
});