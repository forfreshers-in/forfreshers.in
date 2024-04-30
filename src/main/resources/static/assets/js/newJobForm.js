var selected_positions;
var selected_locations;
var selected_types;
var selected_qualifications;

$(document).ready(function() {
	$('#company-name-datalist').autocomplete({

	})

	 selected_positions = $('#positions-input').magicSuggest({
		data: ['saravanan', 'sar'],
		required: true
	})

	selected_locations = $('#locations-input').magicSuggest({
		data: [''], 
		required: true
	})

	 selected_types = $('#types-input').magicSuggest({
		data: [''],
		allowFreeEntries: false,
		required: true
	})

	$('#category-input').autocomplete({

	})

	 selected_qualifications = $('#qualifications-input').magicSuggest({
		data: [''],
		allowFreeEntries: true,
		required: true
	})

	const quill = new Quill('#description-input', {
		modules: {
			toolbar: true,
		},
		placeholder: 'Enter any additional information about job...',
		theme: 'snow'
	});
	
	$('#batch-from-input').yearpicker();
	$('#batch-to-input').yearpicker();
	
	$.ajax({
		url : location.origin+"/api/jobs/savedraft",
		method : "POST",
		data : getFormData(),
		success : function( response ){
			console.log(response)
		},
		error : function (xhr, status, error){
			console.log(xhr.error)
		},
		complete : {
			
		}
	})
	
})



$("form").submit(function(event){
	event.preventDefault();
})
$("#btn-save-draft").click(function(){
	$.ajax({
		url : location.origin+"/api/jobs/savedraft",
		method : "POST",
		contentType : "application/json",
		data : getFormData(),
		success : function( response ){
			console.log("success",response);
		},
		error : function (xhr, status, error){
			console.log("failed",xhr.error);
		},
		complete : {
			 
		}
	})
})

//getting form datas
function getFormData() {
	console.log(selected_locations.getValue());
    let formData = {
        title: $('#add-form-title').val(),
        company: $('#company-name-datalist').val(),
        positions:selected_positions.getValue(),
        locations: selected_locations.getValue(),
        types:selected_types.getValue(),
        category: $('#category-input').val(),
        qualifications: selected_qualifications.getValue(),
        description: $('#description-input').val(),
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
$(selected_locations).on('selectionchange', function(){
  alert(JSON.stringify(this.getValue()));
});
