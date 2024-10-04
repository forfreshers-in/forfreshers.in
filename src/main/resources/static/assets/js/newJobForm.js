var selected_positions;
var selected_locations;
var selected_types;
var selected_qualifications;
var awasome_notifier = new AWN();

$(document).ready(function() {
	$('#company-name-datalist').autocomplete({

	})

	selected_positions = $('#positions-input').magicSuggest({
		data: [],
		required: true
	})
	// fetch positions datas from database
	$.ajax({
		url: location.origin + "/api/positions",
		method: "GET",
		success: function(response) {
			selected_positions.setData(response);
		},
		error: function(xhr, status, error) {
			new AWN().alert("Job positions not fetch try later or refresh page");
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
			new AWN().alert("Location not fetched refresh page or Try later");
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
			new AWN().alert("Types not fetched refresh page or Try later");
		}
	})

	$('#category-input').autocomplete({

	})

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
			new AWN().alert("Qualification not fetched refresh page or Try later");
		}
	})

	description_input_quill = new Quill('#description-input', {
		modules: {
			toolbar: true,
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
		
		$.ajax({
			url: location.origin + "/api/jobs/publish",
			method: "POST",
			contentType: "application/json",
			data: getFormData(),
			success: function(response) {
				new AWN().success("job posted successfully");
			},
			error: function(xhr, status, error) {
				new AWN().alert(xhr.responseText);
			},
			complete: {
	
			}
		})
	}
	awasome_notifier.confirm("Are you sure publish job",confirm,onCancel,{
      labels: {
        confirm: 'Dangerous action'
      }
    })
})

//getting form datas
function getFormData() {
	console.log(selected_locations.getValue());
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

	return JSON.stringify(formData);
}
$(selected_locations).on('selectionchange', function() {
	alert(JSON.stringify(this.getValue()));
});
