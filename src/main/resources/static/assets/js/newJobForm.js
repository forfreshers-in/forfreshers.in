var selected_positions;
var selected_locations;
var selected_types;
var selected_qualifications;
var awasome_notifier = new AWN();
let successSound = new Audio(location.origin+'/assets/audio/magical-twinkle.mp3');
var description_input_quill;

$(document).ready(function() {
	

	selected_positions = $('#positions-input').magicSuggest({
		data: [],
		required: true,
		useCommaKey: false
	})
	// fetch company names datas from database
	$('#company-name-datalist').data("prefetch", location.origin + "/api/companies")
	$('#company-name-datalist').autocomplete();
	
	// fetch positions datas from database
	$.ajax({
		url: location.origin + "/api/positions",
		method: "GET",
		success: function(response) {
			selected_positions.setData(response);
		},
		error: function(xhr, status, error) {
			awasome_notifier.alert("Job positions not fetch try later or refresh page");
		},
		complete: {

		}
	})

	// asign locations input
	selected_locations = $('#locations-input').magicSuggest({
		data: [],
		required: true
	})
	//fetch locations from database
	$.ajax({
		url: location.origin + "/api/locationCities",
		method: "GET",
		success: function(response) {
			selected_locations.setData(response);
		},
		error: function(xhr, status, error) {
			awasome_notifier.alert("Location not fetched refresh page or Try later");
		}
	})
	
	// job types input
	selected_types = $('#types-input').magicSuggest({
		data: [],
		allowFreeEntries: false,
		required: true
	})
	// fetch job types from database
	$.ajax({
		url: location.origin + "/api/typeNames",
		method: "GET",
		success: function(response) {
			selected_types.setData(response);
		},
		error: function(xhr, status, error) {
			awasome_notifier.alert("Types not fetched refresh page or Try later");
		}
	})

	$('#category-input').data("prefetch", location.origin + "/api/categories")
	$('#category-input').autocomplete()

	selected_qualifications = $('#qualifications-input').magicSuggest({
		data: [],
		allowFreeEntries: true,
		required: true
	})
	
	$.ajax({
		url: location.origin + "/api/qualifications",
		method: "GET",
		success: function(response) {
			selected_qualifications.setData(response);
		},
		error: function(xhr, status, error) {
			awasome_notifier.alert("Qualification not fetched refresh page or Try later");
		}
	})
	// Quill Text editor 
		const toolbarOptions = [
	  ['bold', 'italic', 'underline'],        // toggled buttons
	  ['link'],
	  [{ 'header': 4 }],               // custom button values
	  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
	  [{ 'header': [4,false] }],
	  [{ 'align': [] }],
	  ['clean']                                         // remove formatting button
		];
	description_input_quill = new Quill('#description-input', {
		modules: {
			toolbar: {
				 container: '#toolbar', // Selector for toolbar container
				},
		},
		placeholder: 'Enter any additional information about job...',
		theme: 'snow'
	});

	$('#batch-from-input').yearpicker();
	$('#batch-to-input').yearpicker();

})



$("form").submit(function(event) {
	event.preventDefault();
})

$("#btn-publish").click(function() {
	
	let onCancel = () => {new AWN().info("canceled")};
	let confirm = ()=>{
		awasome_notifier.tip('Sending request, please wait...',{
			icons:{
				tip: "spinner",
				prefix: "<i class='fa rotate fa-"
			}
		});
		$.ajax({
			url: location.origin + "/api/jobs/publish",
			method: "POST",
			contentType: "application/json",
			data: getFormData(),
			async: true,
			success: function(response) {
				awasome_notifier.success("job posted successfully");
				successSound.play();
				party.confetti($('body')[0],{
					count: party.variation.range(50, 100),
				})
			},
			error: function(xhr, status, error) {
				if (xhr.status === 401) {			
					showLoginIframe();
					return;
				}
				awasome_notifier.alert(xhr.responseText);
			},
			complete: {
			
			}
		})
	}
	awasome_notifier.confirm("Sure you’re good? 👌 Hit launch if ready!",confirm,onCancel,{
      labels: {
        confirm: 'Confirmation required!',
        confirmOk: 'Launch 🚀',
        confirmCancel: 'Abort 🛑'
        
      },
    })
})

//getting form datas
function getFormData() {
	
	let formData = {
		title: $('#add-form-title').val(),
		company: $('#company-name-datalist').val(),
		positions: selected_positions.getValue(),
		locations: selected_locations.getValue(),
		types: selected_types.getValue(),
		category: $('#category-input').val(),
		qualifications: selected_qualifications.getValue(),
		description: description_input_quill.getSemanticHTML(),
		experienceFrom: $('#experience-from-input').val(),
		experienceTo: $('#experience-to-input').val(),
		batchFrom: $('#batch-from-input').val(),
		batchTo: $('#batch-to-input').val(),
		salaryFrom: $('#salary-from-input').val(),
		salaryTo: $('#salary-to-input').val(),
		startDate: $('#start-date-time-input').val(),
		endDate: $('#end-date-time-input').val(),
		applyLink: $('#apply-link-input').val(),
		hrMail: $('#hr-mail-input').val(),
		expiry: $('#expiry-input').val(),
		agreeTerms: $('#user-checkbox1').is(':checked')
	};
	try{
		formData.id = editJobContainer.data("job-id");
	}catch(error){}
	
	return JSON.stringify(formData);
}
$(selected_locations).on('selectionchange', function() {
	alert(JSON.stringify(this.getValue()));
});
