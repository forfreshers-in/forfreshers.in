/**
 * 
 */
var awasome_notifier = new AWN();

$(document).ready(function(){
	more_location_btn = $("#more-location-btn");
	hidden_location_ = $("#hidden-locations");
	
	more_location_btn.on("click", function(){
		hidden_location_.show();
		more_location_btn.slideUp();
	})
	
	// copy to clipboard button for hr mail
	hrMailCopyBtn = $("#hrMail-copy-btn");
	hrMailCopyBtn.on("click", function(){
		var temp = $("<input>");
	  $("body").append(temp);
	 temp.val($('#hr-mail').text()).select();
	  document.execCommand("copy");
	  temp.remove();
	  new AWN().success("Mail copied");
	})
})