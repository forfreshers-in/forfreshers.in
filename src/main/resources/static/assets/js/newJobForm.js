
$(document).ready(function() {
	$('#company-name-datalist').autocomplete({

	})

	var selected_positions = $('#positions-input').magicSuggest({
		data: ['saravanan', 'sar']
	})

	var selected_locations = $('#locations-input').magicSuggest({
		data: ['']
	})

	var selected_types = $('#types-input').magicSuggest({
		data: [''],
		allowFreeEntries: false
	})

	$('#category-input').autocomplete({

	})

	var selected_types = $('#qualifications-input').magicSuggest({
		data: [''],
		allowFreeEntries: true
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

})
