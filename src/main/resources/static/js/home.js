// Initialize values
let offset = 0;
const limit = 4;
let initialLoadTime = new Date().toISOString();  // Store the current time when page first loads

// Function to load jobs via AJAX
function loadJobs() {
	$('#load-more-job-btn').html(`<div class="spinner-border" role="status"></div> <span class="ps-3">Loading...</span>`);
	$.ajax({
		url: location.origin + "/api/jobs/getjobs",
		type: "GET",
		data: {
			initialLoadTime: initialLoadTime,
			offset: offset,
			limit: limit
		},
		success: function(data) {
			//remove if data is not exist now
			if (data.length == 0) {
				$('#load-more-job-btn').text("no more jobs");
				setTimeout(() => {
					$('#load-more-job-btn').slideUp();
				}, 2000);
				return;
			}

			// Append job data to the container
			data.forEach(function(job, index) {
				let jobCardEle = `
        <div class="col-xl-6 col-sm-12 col-12 col-md-12 p-2">
            <div class="w-100 transition-05 job-card job-list bg-white rounded-3 p-3 col-sm-12 col-xl-6">
                <a href="${location.origin}/jobs/${job.title}-${job.id}" target="_blank">
                    <h5 class="mb-0 "><span class='fade-text-end'>${job.title}</span></h5>
                </a> 
                <span class="text-muted">${job.company}</span>
                <div>
				    <span class="text-muted fade-text-end">
				        <i class="fa-solid fa-location-dot"></i> ${job.locations}
				    </span>
				</div>
                <a href="${location.origin}/jobs/${job.title}-${job.id}" target="_blank" class="read-more">Read More</a>
            </div>
        </div>
    `;

				window.setTimeout(function() {
					// Append the element, hide it, and then fade it in
					$(jobCardEle).hide().appendTo('#job-card-container').slideDown('slow');
				}, 300 * index); // Delay increases with each index
				$('#load-more-job-btn').text("load jobs");
			});

			// Increment the offset for the next request
			offset += limit;
		},
		error: function(error) {
			console.log("Error loading jobs:", error);
			$('#load-more-job-btn').text("load jobs");
		}
	});
}

// Load the first set of jobs on page load
$(document).ready(function() {
	loadJobs();
	
	const gradiantText = document.querySelector('.gradiant');

	gradiantText.addEventListener('mousemove', function (e) {
	  // Get the bounding rectangle of the .gradiant element
	  const rect = gradiantText.getBoundingClientRect();

	  // Calculate the offset position of the cursor relative to the .gradiant element
	  const x = ((e.clientX - rect.left) / rect.width) * 100;
	  const y = ((e.clientY - rect.top) / rect.height) * 100;


	  // Set the radial gradient based on the cursor position
	  gradiantText.style = `background: radial-gradient(circle farthest-corner at ${x}% ${y}%, #121FCF 0%,
			#EA8C1B 100%); -webkit-background-clip:text;`;
	});
});

// Load more jobs when the button is clicked
$('#load-more-job-btn').click(function() {
	loadJobs();
});  