var selectedCardId = null;
var selectedElement = null;

// Function to select a card visually in the UI
function selectCard(cardId, element) {
    if (selectedElement) {
        selectedElement.classList.remove('selected');  // Remove the selection from the previous card
    }
    selectedCardId = cardId;
    selectedElement = element;
    selectedElement.classList.add('selected');  // Highlight the selected card
}

// Navigate to the add card page
function addNewCard() {
    window.location.href = 'add_card.jsp';
}

// Navigate to the edit card page, if a card is selected
function editSelectedCard() {
    if (selectedCardId) {
        window.location.href = 'edit_card.jsp?cardId=' + selectedCardId;
    } else {
        alert('Please select a card first.');
    }
}

// Request the server to delete the selected card, if any
function deleteSelectedCard() {
    if (selectedCardId) {
        // Confirm before deleting
        if (confirm('Are you sure you want to delete this card?')) {
            window.location.href = `delete_card.jsp?cardId=${selectedCardId}`;
        }
    } else {
        alert('Please select a card first.');
    }
}

// Proceed to the payment confirmation page, if a card is selected
function proceedToPayment() {
    if (selectedCardId) {
        window.location.href = 'payment_confirmation.jsp?cardId=' + selectedCardId;
    } else {
        alert('Please select a card first.');
    }
}
