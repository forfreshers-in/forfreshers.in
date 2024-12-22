/*
 *  associated with [myJobs.html]
 */
const jobsContainer = $("#my-jobs-list-container")
const editJobContainer = $("#job-edit-form-container");
// edit form elements
const title = $('#add-form-title');      // Title input
const companyName = $('#company-name-datalist');// Company name
const positions = $('#positions-input');         // Positions input
const locations = $('#locations-input');         // Locations input
const jobTypes = $('#types-input');              // Job types input
const category = $('#category-input');           // Category input
const qualifications = $('#qualifications-input'); // Qualifications input
const description = $('#description-input');    // Description (for Quill editor, get text)
const experienceFrom = $('#experience-from-input'); // Experience from
const experienceTo = $('#experience-to-input');     // Experience to
const batchFrom = $('#batch-from-input');         // Batch from
const batchTo = $('#batch-to-input');             // Batch to
const salaryFrom = $('#salary-from-input');       // Min salary
const salaryTo = $('#salary-to-input');           // Max salary
const startDate = $('#start-date-time-input');    // Start date
const endDate = $('#end-date-time-input');        // End date
const applyLink = $('#apply-link-input');         // Apply link
const hrEmail = $('#hr-mail-input');              // HR Email
const expiryDate = $('#expiry-input');            // Expiry date

let cancelEditBtn;
let updateButton;


$(document).ready(function() {

	// load jobs on datatables
	addMyJobsTableDatas();

	// Edit cancel button
	cancelEditBtn = $('<button id="btn-cancelEdit" class="btn btn-danger px-5 m-2">Cancel</button>')
	$('#btn-publish').after(cancelEditBtn);
	cancelEditBtn.on('click', () => { cancelEditJob() })

	updateButton = $('<button id="btn-update" class="btn btn-success px-5 m-2">Update & publish</button>')
	$('#btn-publish').after(updateButton);

	$('#btn-publish').hide(100);

	// refresh button for myjobs Table
	$('#refresh-myjobs-table-btn').on('click', function() {
		addMyJobsTableDatas();
	});

	// Options for Jobs list
	const contextMenu = $('#jobListMenu');

	// Function to handle right-click on a table row
	$('#jobs-list-table').on('contextmenu', 'tr', function(event) {
		event.preventDefault();

		const row = $(this);
		const jbId = row.data('jb-id');
		let link = location.origin + `/jobs/${row.data('jb-title')}-${jbId}`;
		link = encodeURI(link);

		// Set the context menu items dynamically
		$('#getJobLink').text(`Copy job Link`).data('jb-link', link);

		// Set other actions based on jbId
		$('#publish').data('jb-id', jbId).data('jb-link', link);
		$('#upPublish').data('jb-id', jbId).data('jb-link', link);
		$('#edit').data('jb-id', jbId);
		$('#preview').data('jb-id', jbId).data('jb-link', link);
		$('#share').data('jb-id', jbId).data('jb-link', link);

		// Calculate the position of the context menu
		let menuX = event.pageX;
		let menuY = event.pageY;

		// Get the viewport dimensions
		const viewportWidth = $(window).width();
		const viewportHeight = $(window).height();

		// Get the context menu dimensions
		const menuWidth = contextMenu.outerWidth();
		const menuHeight = contextMenu.outerHeight();

		// Adjust the position if the menu would overflow the viewport
		if (menuX + menuWidth > viewportWidth) {
			menuX = viewportWidth - menuWidth - 10; // 10px padding
		}
		if (menuY + menuHeight > viewportHeight) {
			menuY = viewportHeight - menuHeight - 10; // 10px padding
		}

		// Position the context menu exactly where the right-click occurred
		contextMenu.css({
			display: 'block',
			left: menuX + 'px',
			top: menuY + 'px',
			position: 'fixed'
		});
	});

	// Function to handle click on context menu item
	$('.context-menu-item').on('click', function() {
		const action = $(this).attr('id');
		const jbId = $(this).data('jb-id');
		let link = $(this).data('jb-link');

		if (action === 'getJobLink') { // copy job link in system clipboard
			copyToClipboard(link)
		} else if (action === 'preview') {
			window.open(link, "_blank").focus();
		} else if (action === 'publish') {

		} else if (action === 'upPublish') {

		} else if (action === 'edit') {
			editJob(jbId);
		}
		else if (action === 'share') {
			setTimeout(function() {
				$('#shareMenu').slideDown();
			}, 400)
		}

		contextMenu.slideUp();
	});

	// Hide context menu on click outside
	$(document).on('click', function(event) {
		if (!$(event.target).closest('#contextMenu').length && !$(event.target).closest('#jobs-list-table').length) {
			contextMenu.slideUp();
		}
	});

	// hide share menu
	$(document).on('click', function(event) {
		if ($('#shareMenu').is(":visible")) {
			$('#shareMenu').slideUp();

		}
	});

	// work if click update button
	$("#btn-update").click(function() {
		let onCancel = () => { };
		let confirm = () => {
			awasome_notifier.tip('Sending request, please wait...', {
				icons: {
					tip: "spinner",
					prefix: "<i class='fa rotate fa-"
				}
			});
			$.ajax({
				url: location.origin + "/api/jobs/update",
				method: "PUT",
				contentType: "application/json",
				data: getFormData(),
				async: true,
				success: function(response) {

					awasome_notifier.success("job updated successfully");
					successSound.play();
					cancelEditJob();
				},
				error: function(xhr, status, error) {
					if (xhr.status === 401) {
						awasome_notifier.info("Login Required !");
						showLoginIframe();
						return;
					}
					awasome_notifier.alert(xhr.responseText);
				},
				complete: function() {
				}
			})
		}
		awasome_notifier.confirm("Sure you’re good? 👌 Hit launch if ready!", confirm, onCancel, {
			labels: {
				confirm: 'Confirmation required!',
				confirmOk: 'Update 🚀',
				confirmCancel: 'Abort 🛑'

			},
		})
	})

})


function addMyJobsTableDatas() {
	$('#refresh-myjobs-table-btn > i').addClass('zmdi-hc-spin');
	$.ajax({
		url: location.origin + "/api/jobs/myjobs",
		method: "GET",
		contentType: "Application/json",
		success: function(response) {

			$('#jobs-list-table').DataTable({
				data: response,

				columns: [
					{ data: 'id', title: "id" },
					{ data: 'published', title: 'status' },
					{ data: 'title', title: "title" },
					{ data: 'company', title: 'company' },
					{ data: 'createdAt', title: 'created' },
					{ data: 'updatedAt', title: 'udpated' },
					{ data: 'expiredAt', title: 'life' }
				],
				"createdRow": function(row, data, dataIndex) {
					// setting job id in table row for collecting in job options
					$(row).attr('data-jb-id', data.id);
					$(row).attr('data-jb-title', data.title);
				},

				"columnDefs": [
					{
						"targets": 1,
						"createdCell": function(td, cellData, rowData, row, col) {
							let e = $('<span>')
							if (cellData == 1) {
								e.addClass(' px-2 border text-success border-success rounded m-1');
								e.text('Published');
								$(td).html(e)
							}
							if (cellData == 0) {
								e.addClass(' px-2 border text-warning border-warning rounded m-1');
								e.text('Draft');
								$(td).html(e)
							}
							if (cellData == -1) {
								e.addClass(' px-2 border text-danger border-danger rounded m-1');
								e.text('Not Published');
								$(td).html(e)
							}
						}
					}
				]
			});

		},
		error: function(xhr, status, error) {
			if (xhr.status === 401) {			
				showLoginIframe();
				return;
			}
			awasome_notifier.alert("failed", xhr.responseText);
		},
		complete: function() {
			$('#refresh-myjobs-table-btn > i').removeClass('zmdi-hc-spin');
		}
	});
}

function copyToClipboard(textToCopy) {
	navigator.clipboard.writeText(textToCopy).then(function() {
		awasome_notifier.success("link copied !")
	}).catch(function(error) {
		awasome_notifier.error("link not copied !")
	});
}

async function editJob(id) {
	//
	editJobContainer.data("job-id", id);;

	// edit button name 
	$("#btn-publish").text("Update")
	awasome_notifier.asyncBlock(
		new Promise((resolve, reject) => {
			// Perform AJAX request
			$.ajax({
				url: location.origin + "/api/jobs/getjob",  // Example API endpoint
				data: {
					"id": id
				},
				type: 'GET',
				success: function(response) {
					resolve("AJAX success: " + response.title); // Pass the response data
					jobsContainer.slideUp(400);
					editJobContainer.slideDown(400);

					fillEditForm(response);
				},
				error: function(xhr, status, error) {
					if (xhr.status === 401) {
						
						showLoginIframe();
						return;
					}
					reject("AJAX error: " + error);  // Handle the error
				}
			});
		}),
		// Success message

		{
			error: 'Failed to get edit job'
		}
	);
}
function fillEditForm(jobData) {

	title.val(jobData.title)
	companyName.val(jobData.company);
	selected_positions.setValue(jobData.positions);
	selected_locations.setValue(jobData.locations);
	selected_types.setValue(jobData.types);
	selected_qualifications.setValue(jobData.qualifications);
	description_input_quill.clipboard.dangerouslyPasteHTML(jobData.description);
	category.val(jobData.category);
	experienceFrom.val(jobData.experienceFrom);
	experienceTo.val(jobData.experienceTo);
	batchFrom.val(jobData.fromBatch);
	batchTo.val(jobData.toBatch);
	salaryFrom.val(jobData.minSalary);
	salaryTo.val(jobData.maxSalary);
	startDate.val(utcToIso(jobData.dateAndTime, 0, 16));
	endDate.val(utcToIso(jobData.lastDate, 0, 16));
	applyLink.val(jobData.applyLink);
	hrEmail.val(jobData.hrMail);
	expiryDate.val(utcToIso(jobData.expiredAt, 0, 10))
}

function utcToIso(utcTime, start, end) {
	// Convert timestamp to Date object (local time)
	let date = new Date(utcTime);
	let formattedDateTime = date.toISOString().slice(start, end); // YYYY-MM-DDTHH:MM
	return formattedDateTime;
}

function cancelEditJob() {
	jobsContainer.slideDown(400);
	editJobContainer.slideUp(400);
}
