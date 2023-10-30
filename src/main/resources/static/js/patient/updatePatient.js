document.addEventListener('DOMContentLoaded', function() {
    const editButtons = document.querySelectorAll('.edit');
    const modal = document.getElementById('modal');
    const patientForm = document.getElementById('patient-form');
    const nomInput = document.getElementById('nom');
    const dateNaissanceInput = document.getElementById('dateNaissance');
    const sexeInput = document.getElementById('sexe');
    const adresseInput = document.getElementById('adresse');
    const telephoneInput = document.getElementById('telephone');
    const CodeInput = document.getElementById('code');
    const PrenomInput = document.getElementById('prenom');


    // Function to open the modal and set default values
    function openModal(patientId) {
        fetch(`/patient/${patientId}`) // Replace with your API URL
            .then(response => response.json())
            .then(patientData => {

                const dateOfBirth = new Date(patientData.dateNaissance);

                // Extract year, month, and day
                const year = dateOfBirth.getFullYear();
                const month = dateOfBirth.getMonth() + 1; // Months are 0-indexed, so add 1
                const day = dateOfBirth.getDate();

                // Format the date in "yyyy-MM-dd" format
                const formattedDate = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;

                // Set the default values in the form fields
                CodeInput.value = patientData.code
                nomInput.value = patientData.nom;
                PrenomInput.value = patientData.prénom;
                dateNaissanceInput.value = formattedDate;
                sexeInput.value = patientData.sexe;
                adresseInput.value = patientData.adresse;
                telephoneInput.value = patientData.téléphone;
            })
            .catch(error => {
                console.error('Error fetching patient data:', error);
            });
        // Display the modal
        modal.style.display = 'block';
    }

    // Add a click event listener to each Edit button
    editButtons.forEach(button => {
        button.addEventListener('click', function() {
            const patientIdInput = button.parentElement.querySelector('input[name="patientId"]');
            const patientId = patientIdInput.value;
            // patientForm.dataset.patientId = patientId;
            openModal(patientId);
        });
    });

    // Handle form submission and update patient data
    patientForm.addEventListener('submit', function(event) {
        event.preventDefault();
        // Add code here to update the patient data using AJAX or a form submission
        // Close the modal after successfully updating the patient data
        // const patientId = patientForm.dataset.patientId;
        const nom = document.getElementById('nom').value;
        const dateNaissance = document.getElementById('dateNaissance').value;
        const sexe = document.getElementById('sexe').value;
        const adresse = document.getElementById('adresse').value;
        const telephone = document.getElementById('telephone').value;
        const code = document.getElementById('code').value;
        const prenom = document.getElementById('prenom').value;

        const updatePatient = {
            code: code,
            nom: nom,
            prénom: prenom,
            adresse: adresse,
            téléphone: telephone,
            dateNaissance: dateNaissance,
            sexe: sexe
        };

        fetch(`updatepatient/${code}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatePatient)
        })
            .then(response => {
                if (response.status === 200) {
                    // Successful update, you can handle success here
                    console.log('Patient updated successfully');
                } else {
                    // Handle errors or display a message to the user
                    console.error('Error updating patient');
                }
            })
            .catch(error => {
                console.error('Network error:', error);
            });

        modal.style.display = 'none';
    });

    // Add event listener to close the modal
    const closeBtn = document.getElementById('cancelButton');
    closeBtn.addEventListener('click', function() {
        modal.style.display = 'none';
    });

    const closeSpan = document.getElementById("closeSpan")
    closeSpan.addEventListener('click', function () {
        modal.style.display = 'none';
    })

    // You can also add code to handle the "Delete" button, if needed.
});