<script>
  	let reimbursementTableBody = document.getElementById('ReimbursementTableBody');
  	let reimbursementTableHead = document.getElementById('ReimbursementTableHead');
  	let userString = sessionStorage.getItem('currentUser');
      //populateTable is the name of the function
  	populateTable(userString);
  </script>

<script>
function populateTable(userString) {
    if(userString === null) {
          window.location = "http://localhost:8080/Project1/login"; 
      } else {
          let wholeUser = JSON.parse(userString);
          if(wholeUser != null) {
              let reimbursement = wholeUser.userReimbursements;
              console.log(reimbursement);
              
              for(key in reimbursement) {
                  var tr = document.createElement('tr');
                  tr.innerHTML = '<td>' + reimbursement[key].reimId + '</td>' +
                  '<td>' + reimbursement[key].timeSubmitted + '</td>' +
                  '<td>' + reimbursement[key].amount + '</td>' +
                  '<td>' + reimbursement[key].type + '</td>' +
                  '<td>' + reimbursement[key].description + '</td>' +
                  '<td>' + reimbursement[key].status + '</td>' +
                  '<td>' + reimbursement[key].resolver + '</td>' +
                  '<td>' + reimbursement[key].timeResolved + '</td>';
                  reimbursementTable.appendChild(tr);
              };
          }
      }
}
</script>