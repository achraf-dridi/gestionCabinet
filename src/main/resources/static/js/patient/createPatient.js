
document.addEventListener('DOMContentLoaded', function() {
    const card = document.getElementById('card');
    const createModal = document.getElementById('createModal');
    const createPatientForm = document.getElementById('create-patient-form');

    // Function to open the modal and set default values
    function openCreateModal() {
        // Display the modal
    }

    // Add a click event listener to each Edit button
    card.addEventListener('click', function() {
        createModal.style.display = 'block';

    });

    // Handle form submission and update patient data
    createPatientForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const nom = document.getElementById('create-nom').value;
        const dateNaissance = document.getElementById('create-dateNaissance').value;
        const sexe = document.getElementById('create-sexe').value;
        const adresse = document.getElementById('create-adresse').value;
        const telephone = document.getElementById('create-telephone').value;
        const prenom = document.getElementById('create-prenom').value;

        const newPatient = {
            nom: nom,
            prénom: prenom,
            adresse: adresse,
            téléphone: telephone,
            dateNaissance: dateNaissance,
            sexe: sexe
        };

        fetch(`/createpatient`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newPatient)
        })
            .then(response => {
                if (response.status === 200) {
                    // Successful update, you can handle success here
                    console.log('Patient created successfully');
                } else {
                    // Handle errors or display a message to the user
                    console.error('Error creating patient');
                }
            })
            .catch(error => {
                console.error('Network error:', error);
            });

        createModal.style.display = 'none';
    });

    // Add event listener to close the modal
    const cancelCreateBtn = document.getElementById('cancelCreateButton');
    cancelCreateBtn.addEventListener('click', function() {
        createModal.style.display = 'none';
    });

    const closeCreateSpan = document.getElementById("closeCreateModal")
    closeCreateSpan.addEventListener('click', function () {
        createModal.style.display = 'none';
    })

    // You can also add code to handle the "Delete" button, if needed.
});