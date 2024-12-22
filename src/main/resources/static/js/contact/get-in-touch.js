/**
 * 
 */

var awasome_notifier = new AWN();


$(document).ready(function() {
    $('#get-in-touch-form').on('submit', function(event) {
        event.preventDefault(); 

        var formData = {
            name: $('#first_name').val()+' '+$('#last_name').val(),
            email: $('#email').val(),
            message: $('#message').val()
        };

        $.ajax({
            type: 'POST',
            url: document.location.origin+"/api/contact", 
            data: $.param(formData),
            success: function(response) {
               new AWN().success("message sended, We will get back you soon !");
                $('#get-in-touch-form')[0].reset();
            },
            error: function(xhr, status, error) {
               new AWN().alert(xhr.responseText);
            }
        });
    });
});
