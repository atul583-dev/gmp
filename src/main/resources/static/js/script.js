async function fetchGMPData() {
    try {
        const response = await fetch('/api/gmp'); // API endpoint
        const data = await response.json();

        const tableBody = document.getElementById('ipo-container');
        const noDataMessage = document.getElementById('no-data-message');
        tableBody.innerHTML = '';  // Clear previous data

        if (data.length > 0) {
            data.forEach(ipo => {
                const row = document.createElement('tr');
                row.innerHTML = `
                        <td data-label="IPO Name">${ipo.ipo}</td>
                        <td data-label="Price (₹)">${ipo.price}</td>
                        <td data-label="GMP (₹)" class="gmp-column">${ipo.gmp}</td>
                        <td data-label="Est. Listing">${ipo.estListing}</td>
                        <td data-label="IPO Size (Cr)">${ipo.ipoSize}</td>
                        <td data-label="Lot Size">${ipo.lot}</td>
                        <td data-label="Open Date">${ipo.open}</td>
                        <td data-label="Close Date">${ipo.close}</td>
                        <td data-label="Listing Date">${ipo.listing}</td>
                        <td data-label="GMP Updated Date">${ipo.gmpUpdated}</td>
                    `;
                tableBody.appendChild(row);
            });
            noDataMessage.style.display = 'none';
        } else {
            noDataMessage.innerText = 'No IPO data available.';
        }
    } catch (error) {
        console.error('Error fetching GMP data:', error);
        document.getElementById('no-data-message').innerHTML = '<p>Error fetching data. Please try again later.</p>';
    }
}

function sortTable(columnIndex) {
    const table = document.getElementById("ipo-table");
    let switching = true;
    let dir = "asc"; // Set the sorting direction to ascending
    let rows, i, x, y, shouldSwitch;

    while (switching) {
        switching = false;
        rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[columnIndex];
            y = rows[i + 1].getElementsByTagName("TD")[columnIndex];

            // Compare values for sorting
            if (dir === "asc") {
                if (parseFloat(x.innerText.replace(/₹|,/g, '')) > parseFloat(y.innerText.replace(/₹|,/g, ''))) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir === "desc") {
                if (parseFloat(x.innerText.replace(/₹|,/g, '')) < parseFloat(y.innerText.replace(/₹|,/g, ''))) {
                    shouldSwitch = true;
                    break;
                }
            }
        }

        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        } else {
            if (dir === "asc") {
                dir = "desc"; // Change the direction to descending
            } else {
                dir = "asc"; // Change the direction back to ascending
            }
        }
    }
}

// Fetch GMP data on page load
fetchGMPData();
