const newPasswordInput = document.getElementById('newPassword');
const confirmPasswordInput = document.getElementById('confirmPassword');

// Elements to show individual requirement status
const lengthRequirement = document.getElementById('lengthRequirement');
const letterRequirement = document.getElementById('letterRequirement');
const numberRequirement = document.getElementById('numberRequirement');
const specialCharRequirement = document.getElementById('specialCharRequirement');

// Validation function
function validatePassword(password) {
    const lengthValid = password.length >= 6;
    const letterValid = /[a-zA-Z]/.test(password);
    const numberValid = /\d/.test(password);
    const specialCharValid = /[@$!%*?&#]/.test(password);

    // Update length requirement
    if (lengthValid) {
        lengthRequirement.classList.add('valid');
        lengthRequirement.classList.remove('invalid');
    } else {
        lengthRequirement.classList.add('invalid');
        lengthRequirement.classList.remove('valid');
    }

    // Update letter requirement
    if (letterValid) {
        letterRequirement.classList.add('valid');
        letterRequirement.classList.remove('invalid');
    } else {
        letterRequirement.classList.add('invalid');
        letterRequirement.classList.remove('valid');
    }

    // Update number requirement
    if (numberValid) {
        numberRequirement.classList.add('valid');
        numberRequirement.classList.remove('invalid');
    } else {
        numberRequirement.classList.add('invalid');
        numberRequirement.classList.remove('valid');
    }

    // Update special character requirement
    if (specialCharValid) {
        specialCharRequirement.classList.add('valid');
        specialCharRequirement.classList.remove('invalid');
    } else {
        specialCharRequirement.classList.add('invalid');
        specialCharRequirement.classList.remove('valid');
    }

    return lengthValid && letterValid && numberValid && specialCharValid;
}

// Listen for input in the new password field
newPasswordInput.addEventListener('input', function () {
    validatePassword(newPasswordInput.value);
});

// Confirm password match
confirmPasswordInput.addEventListener('input', function () {
    const confirmPasswordValue = confirmPasswordInput.value;
    const newPasswordValue = newPasswordInput.value;

    if (newPasswordValue === confirmPasswordValue && confirmPasswordValue !== '') {
        confirmPasswordInput.classList.add('valid');
        confirmPasswordInput.classList.remove('invalid');
    } else {
        confirmPasswordInput.classList.add('invalid');
        confirmPasswordInput.classList.remove('valid');
    }
});

document.getElementById('resetPasswordForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const newPassword = newPasswordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    // Check if password meets all requirements and passwords match
    if (!validatePassword(newPassword)) {
        document.getElementById('message').textContent = "Password does not meet the required criteria.";
        return;
    }

    if (newPassword !== confirmPassword) {
        document.getElementById('message').textContent = "Passwords do not match.";
        return;
    }

    // Extract token (pwrtok) from the URL parameters
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('pwrtok');

    if (!token) {
        document.getElementById('message').textContent = "Token is missing.";
        return;
    }

    // Prepare the data to be sent
    const data = {
        token: token,
        newPassword: newPassword
    };

    try {
        const response = await fetch(window.location.origin + '/api/auth/reset-password', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            document.getElementById('message').textContent = "Password reset successfully!";
            document.getElementById('message').style.color = "green";
            window.location.replace(location.origin+"/login")
        } else {
            document.getElementById('message').textContent = "Error resetting password.";
        }
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('message').textContent = "Network error occurred.";
    }
});
