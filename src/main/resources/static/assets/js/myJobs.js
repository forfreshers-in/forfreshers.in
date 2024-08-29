/**
 *  associated with [myJobs.html]
 */
const notification = new AWN();

$(document).ready(function() {

	addMyJobsTableDatas();

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
		$('#edit').data('jb-id', jbId).data('jb-link', link);
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
		}else if(action === 'preview') {
			window.open(link,"_blank").focus();
		}else if(action === 'publish') {
			
		}else if(action === 'upPublish') {
			
		}else if(action === 'edit') {
			
		}
		else if(action === 'share') {
			setTimeout(function(){
			 $('#shareMenu').slideDown();				
			},400)
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
		if( $('#shareMenu').is(":visible")){		
			 $('#shareMenu').slideUp();
		
		}
	});
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
							console.log(td)
							let e = $('<span>')
							if (cellData == 1) {
								e.addClass(' px-2 border text-success border-success rounded m-1');
								e.text('Published');
								$(td).html(e)
							}
							if(cellData == 0) {
								e.addClass(' px-2 border text-warning border-warning rounded m-1');
								e.text('Draft');
								$(td).html(e)
							}
							if(cellData == -1) {
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
			console.log("failed", xhr.responseText);
		},
		complete: function() {
			$('#refresh-myjobs-table-btn > i').removeClass('zmdi-hc-spin');
		}
	});
}

function copyToClipboard(textToCopy){
	navigator.clipboard.writeText(textToCopy).then(function() {
	        notification.success("link copied !")
	    }).catch(function(error) {
	        notification.error("link not copied !")
	    });
}
