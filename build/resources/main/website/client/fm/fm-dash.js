
let allReimb;
let tableBody;
let accountID;

window.onload = function () {

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){

        if(xhttp.readyState===4 && xhttp.status===200){

            allReimb = JSON.parse(xhttp.responseText);

            // console.log(allReimb);
            DOMReimbList(allReimb);
        }
    }

    xhttp.open("GET","http://localhost:1108/getallreimb", true);
    xhttp.send();


    let xhttp1 = new XMLHttpRequest();

    xhttp1.onreadystatechange = function(){

        if(xhttp1.readyState===4 && xhttp1.status===200){
            // console.log(xhttp1.responseText);
            DOMAccountName(xhttp1.responseText);
        }
    }

    xhttp1.open("GET", `http://localhost:1108/userfullname`);
    xhttp1.send();
}


let DOMReimbList = function (theList) {
    
    let fmtable = document.getElementById("fmTable");
    tableBody = document.createElement("tbody");
    tableBody.setAttribute('id', 'tBody');
    fmtable.appendChild(tableBody);
    tableBody = document.getElementById("tBody");

    for(i=0; i<theList.length; i++) {
        let aReimb = theList[i];
        let newRow = tableBody.insertRow(i);

        let cell1 = newRow.insertCell();
        let cell2 = newRow.insertCell();
        let cell3 = newRow.insertCell();
        let cell4 = newRow.insertCell();
        let cell5 = newRow.insertCell();
        let cell6 = newRow.insertCell();
        let cell7 = newRow.insertCell();
        let cell8 = newRow.insertCell();
        let cell9 = newRow.insertCell();
        let cell10 = newRow.insertCell();

        // console.log(cell8);

        cell1.setAttribute("id", "reimbID");

        cell1.innerHTML = aReimb.reimbID;
        cell2.innerHTML = "$"+aReimb.amount;
        cell3.innerHTML = aReimb.description
        cell5.innerText = aReimb.submitTime;
        cell6.innerText = aReimb.resolvedTime;
        cell8.innerHTML = aReimb.resolverID;
        // let userReimb = aReimb.reimbID;

        let appButton = document.createElement("button");
        let denyButton = document.createElement("button");

        appButton.setAttribute("type", "button");
        denyButton.setAttribute("type", "button");
        appButton.setAttribute("id", `apprbutton${aReimb.reimbID}`);
        denyButton.setAttribute("id", `denybutton${aReimb.reimbID}`);
        appButton.setAttribute("class", "btn btn-success");
        denyButton.setAttribute("class", "btn btn-danger");

        let appText = document.createTextNode("Approve");
        let denyText = document.createTextNode("Deny");

        appButton.appendChild(appText);
        denyButton.appendChild(denyText);

        cell9.appendChild(appButton);
        cell10.appendChild(denyButton);

        appButton.addEventListener("click", updateReimbApp);
        denyButton.addEventListener("click", updateReimbDeny);

        // appButton.onclick(updateReimbApp(i));
        // denyButton.onclick(updateReimbDeny(i));

        if(aReimb.type==1){
            cell4.innerHTML = "Lodging";
        }else if(aReimb.type==2){
            cell4.innerHTML = "Travel";
        }else if(aReimb.type==3){
            cell4.innerHTML = "Food";
        }else if(aReimb.type==4){
            cell4.innerHTML = "Other";
        }

        if(aReimb.status==1){
            cell7.innerHTML = "Pending";
        }else if(aReimb.status==2){
            cell7.innerHTML = "Approved";
        }else if(aReimb.status==3){
            cell7.innerHTML = "Denied";
        }
    }
}

function DOMAccountName(NameStr){

    let pageTitle = document.getElementById("Nametag");
    let titleText = document.createTextNode(`${NameStr}'s Dashboard`);
    pageTitle.appendChild(titleText);
}

function updateReimbApp(event) {
    // console.log(event);

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){

        if(xhttp.readyState===4 && xhttp.status===200){
            allReimb = JSON.parse(xhttp.responseText);

            // console.log(allReimb);
            tableBody.remove();
            DOMReimbList(allReimb);
        }
    }
    let updatedReimb = event.target.id.slice(10);
    // console.log(updatedReimb);
    let appStr = "2";

    xhttp.open("GET", `http://localhost:1108/updatereimb?status=${appStr}&reimb_id=${updatedReimb}`);
    xhttp.send();
}

function updateReimbDeny(event) {
    // console.log(event);
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){

        if(xhttp.readyState===4 && xhttp.status===200){
            allReimb = JSON.parse(xhttp.responseText);

            // console.log(allReimb);
            tableBody.remove();
            DOMReimbList(allReimb);
        }
    }
    let updatedReimb = event.target.id.slice(10);
    // console.log(updatedReimb);
    let appStr = "3";

    xhttp.open("GET", `http://localhost:1108/updatereimb?status=${appStr}&reimb_id=${updatedReimb}`);
    xhttp.send();
}



function filtering(){
    let infofield;
    let temp = allReimb.filter(element => {
        infofield = document.getElementById("filterbox");
        // console.log(infofield.value);
        let strignStatus;
        if(element.status==1){
            strignStatus = "Pending";
        }else if(element.status==2){
            strignStatus = "Approved";
        }else if(element.status==3){
            strignStatus = "Denied";
        }
        return infofield.value===strignStatus;
    })
    // console.log(temp);

    if(temp.length >0){
        repoptable(temp);
    }else if(infofield.value===""){
        repoptable(allReimb);
    }
}

function repoptable(newList){
    let tbody = document.getElementById("fmTable").getElementsByTagName("tbody")[0];

    let newBody = document.createElement("tbody");


     for(i=0; i<newList.length; i++) {
        let aReimb = newList[i];
        let newRow = newBody.insertRow(i);

        let cell1 = newRow.insertCell();
        let cell2 = newRow.insertCell();
        let cell3 = newRow.insertCell();
        let cell4 = newRow.insertCell();
        let cell5 = newRow.insertCell();
        let cell6 = newRow.insertCell();
        let cell7 = newRow.insertCell();
        let cell8 = newRow.insertCell();
        let cell9 = newRow.insertCell();
        let cell10 = newRow.insertCell();

        // console.log(cell8);

        cell1.setAttribute("id", "reimbID");

        cell1.innerHTML = aReimb.reimbID;
        cell2.innerHTML = "$"+aReimb.amount;
        cell3.innerHTML = aReimb.description
        cell5.innerHTML = aReimb.submitTime;
        cell6.innerHTML = aReimb.resolvedTime;
        cell8.innerHTML = aReimb.resolverID;
        // let userReimb = aReimb.reimbID;

        let appButton = document.createElement("button");
        let denyButton = document.createElement("button");

        appButton.setAttribute("type", "button");
        denyButton.setAttribute("type", "button");
        appButton.setAttribute("id", `apprbutton${aReimb.reimbID}`);
        denyButton.setAttribute("id", `denybutton${aReimb.reimbID}`);
        appButton.setAttribute("class", "btn btn-success");
        denyButton.setAttribute("class", "btn btn-danger");

        let appText = document.createTextNode("Approve");
        let denyText = document.createTextNode("Deny");

        appButton.appendChild(appText);
        denyButton.appendChild(denyText);

        cell9.appendChild(appButton);
        cell10.appendChild(denyButton);

        appButton.addEventListener("click", updateReimbApp);
        denyButton.addEventListener("click", updateReimbDeny);

        // appButton.onclick(updateReimbApp(i));
        // denyButton.onclick(updateReimbDeny(i));

        if(aReimb.type==1){
            cell4.innerHTML = "Lodging";
        }else if(aReimb.type==2){
            cell4.innerHTML = "Travel";
        }else if(aReimb.type==3){
            cell4.innerHTML = "Food";
        }else if(aReimb.type==4){
            cell4.innerHTML = "Other";
        }

        if(aReimb.status==1){
            cell7.innerHTML = "Pending";
        }else if(aReimb.status==2){
            cell7.innerHTML = "Approved";
        }else if(aReimb.status==3){
            cell7.innerHTML = "Denied";
        }
    }

    tbody.parentNode.replaceChild(newBody, tbody);
}