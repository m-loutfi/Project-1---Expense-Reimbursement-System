let allReimb;
let tableBody;

window.onload = function () {

    let xhttp1 = new XMLHttpRequest();

    xhttp1.onreadystatechange = function(){

        if(xhttp1.readyState===4 && xhttp1.status===200){
            DOMAccountName(xhttp1.responseText);
        }
    }
    xhttp1.open("GET", "http://localhost:1108/userfullname");
    xhttp1.send();
    


    let xhttp2 = new XMLHttpRequest();

    xhttp2.onreadystatechange = function(){

        if(xhttp2.readyState===4 && xhttp2.status===200){

            allReimb = JSON.parse(xhttp2.responseText);
            DOMReimbList(allReimb);
        }
    }
    xhttp2.open("GET","http://localhost:1108/getuserreimb", true);
    xhttp2.send();
}


let DOMReimbList = function (theList) {

    let emptable = document.getElementById("emptable");
    tableBody = document.createElement("tbody");
    tableBody.setAttribute('id', 'tBody');
    emptable.appendChild(tableBody);
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

        cell1.innerHTML = aReimb.reimbID;
        cell2.innerHTML = "$"+aReimb.amount;
        cell3.innerHTML = aReimb.description
        cell5.innerHTML = aReimb.submitTime;
        cell6.innerHTML = aReimb.resolvedTime;
        cell8.innerHTML = aReimb.resolverID;

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


function filtering(){
    let infofield;
    let temp = allReimb.filter(element => {
        infofield = document.getElementById("filterbox");
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

    if(temp.length >0){
        repoptable(temp);
    }else if(infofield.value===""){
        repoptable(allReimb);
    }
}

function repoptable(newList){
    let tbody = document.getElementById("emptable").getElementsByTagName("tbody")[0];

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


        cell1.setAttribute("id", "reimbID");

        cell1.innerHTML = aReimb.reimbID;
        cell2.innerHTML = "$"+aReimb.amount;
        cell3.innerHTML = aReimb.description
        cell5.innerHTML = aReimb.submitTime;
        cell6.innerHTML = aReimb.resolvedTime;
        cell8.innerHTML = aReimb.resolverID;


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