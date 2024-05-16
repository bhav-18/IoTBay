var selectedCardId = null;
var selectedElement = null;
var selectedCardNumber = null;  // To hold the card number

// Function to select a card visually in the UI
function selectCard(cardId, cardNumber, element) {
    if (selectedElement) {
        selectedElement.classList.remove('selected');
    }
    selectedCardId = cardId;
    selectedCardNumber = cardNumber;  // Save the selected card number
    selectedElement = element;
    selectedElement.classList.add('selected');
    document.getElementById('editButton').disabled = false; // Enable the edit button
    document.getElementById('cardNumber').value = cardNumber; // Set hidden input for form
    document.getElementById('proceedButton').disabled = false; // Enable proceed button
}

function checkCardSelection() {
	var cardNumberInput = document.getElementById('cardNumber');
	if (!cardNumberInput.value) {
		alert('Please select a payment method first.');
		return false;
    }
    console.log("Form is submitting with card number:", cardNumberInput.value); // Debugging output
    return true;
}

// Navigate to the edit card page, if a card is selected
function editSelectedCard() {
	if (selectedCardId) {
		// Redirect to updateCard.jsp with the selected card ID
		window.location.href = 'updateCard.jsp?cardId=' + selectedCardId;
	} else {
		alert('Please select a card first.');
	}
}

// Request the server to delete the selected card, if any
function deleteSelectedCard() {
	if (selectedCardId) {
		if (confirm('Are you sure to delete this saved payment method?')) {
			var form = document.createElement('form');
			form.style.display = 'none';
			form.method = 'post';
			form.action = 'DeleteCardServlet'; // This servlet needs to handle the deletion
			
			var cardIdInput = document.createElement('input');
			cardIdInput.type = 'hidden';
			cardIdInput.name = 'cardId';
			cardIdInput.value = selectedCardId; // Make sure this is set correctly in your page
			form.appendChild(cardIdInput);
			
			document.body.appendChild(form);
			form.submit();
		}
	} else {
		alert('Please select a card first.');
	}
}