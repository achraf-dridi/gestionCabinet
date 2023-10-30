document.addEventListener('DOMContentLoaded', function() {
    const deleteButtons = document.querySelectorAll('.delete');
    const confirmationWindow = document.getElementById("confirmationWindow");
    // const confirmation = document.getElementById('confirmButtons');
    const phraseToDisplay = document.getElementById('phraseToDisplay')
    const deletebtn = document.getElementById('confirmDeletePatientButton');

    // Add a click event listener to each Edit button
    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const patientIdInput = button.parentElement.querySelector('input[name="patientId"]');
            const patientId = patientIdInput.value;
            deletebtn.dataset.patientId = patientId;
            //Get Patient name
            fetch(`/patient/${patientId}`) // Replace with your API URL
                .then(response => response.json())
                .then(patientData => {
                    // Set the default values in the form fields
                    phraseToDisplay.textContent =  'Are you sure you want to delete ' + patientData.nom + ' ' + patientData.prénom;

                })
                .catch(error => {
                    console.error('Error fetching patient data:', error);
                });
            // Display the modal
            confirmationWindow.style.display = 'block';
        });
    });

    // Handle form submission and update patient data
    deletebtn.addEventListener('click', function() {
        const patientId = deletebtn.dataset.patientId;
        // Debugging log
        console.log("Attempting to delete patient with ID:", patientId);
        // Add code here to update the patient data using AJAX or a form submission
        // Close the modal after successfully updating the patient data

        fetch(`/deletepatient/${patientId}`, {
            method: "DELETE"
        })
            .then(response => {
                // Debugging log
                console.log("In Then block");
                if (response.status === 200) {
                    // Debugging log
                    console.log("In Status 200");
                    // Successful deletion, hide the confirmation window
                    confirmationWindow.style.display = "none";
                } else if (response.status === 404) {
                    console.log("In Status 404");

                    // Handle any errors or display a message to the user
                    console.warn("Patient not found");
                } else {
                    console.log("In else ");

                    console.error("Error could not delete this patient");

                }
            })
            .catch(error => {
                console.log("In catch block error  ");

                console.error("Network error:", error);
            });
        console.log("out of fetch ");

        confirmationWindow.style.display = 'none';
    });

    // Add event listener to close the modal
    const closeBtn = document.getElementById('cancelDeletePatientButton');
    closeBtn.addEventListener('click', function() {
        confirmationWindow.style.display = 'none';
    });

});