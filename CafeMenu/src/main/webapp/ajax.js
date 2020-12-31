window.onload = function(){
    document.getElementById("add").addEventListener('click', addItem);
	document.getElementById("update").addEventListener('click', updateItem);
	document.getElementById("delete").addEventListener('click', deleteItem);
}

function addItem(){
	let itemName = document.getElementById("itemName").value;
	let itemPrice = document.getElementById("itemPrice").value;
	
	if(/^[A-Za-z]+$/.test(itemName) && /^\d+\.?\d{0,2}$/.test(itemPrice)){
		let menuItem = '{"name":"' + itemName + '","price":"' + itemPrice + '"}';
		
		let xhttp = new XMLHttpRequest();
		
		let cafeMenuURL = "http://localhost:8080/CafeMenu/master";
		
		xhttp.open("POST", cafeMenuURL);
		
		xhttp.send(menuItem);
	
	    xhttp.onreadystatechange = function(){
			if(xhttp.readyState == 4){
				if(xhttp.status == 200){
					getMenu();
				}
				document.getElementById("response").innerText =  xhttp.responseText;
			}
		}
	} else {
		document.getElementById("response").innerText = "Invalid values";
	}
}

function updateItem(){
	let itemName = document.getElementById("itemName").value;
	let itemPrice = document.getElementById("itemPrice").value;
	
	if(/^[A-Za-z]+$/.test(itemName) && /^\d+\.?\d{0,2}$/.test(itemPrice)){	
		let xhttp = new XMLHttpRequest();
		
		let cafeMenuURL = "http://localhost:8080/CafeMenu/master";
		
		xhttp.open("PUT", cafeMenuURL + "?name=" + itemName + "&price=" + itemPrice);
		
		xhttp.send();
	
	    xhttp.onreadystatechange = function(){
			if(xhttp.readyState == 4){
				if(xhttp.status == 200){
					getMenu();
				}
				document.getElementById("response").innerText =  xhttp.responseText;
			}
		}
	} else {
		document.getElementById("response").innerText = "Invalid values";
	}
}

function deleteItem(){
	let itemName = document.getElementById("itemName").value;
	
	if(/^[A-Za-z]+$/.test(itemName)){	
		let xhttp = new XMLHttpRequest();
		
		let cafeMenuURL = "http://localhost:8080/CafeMenu/master";
		
		xhttp.open("DELETE", cafeMenuURL + "?name=" + itemName);
		
		xhttp.send();
	
	    xhttp.onreadystatechange = function(){
			if(xhttp.readyState == 4){
				document.getElementById("response").innerText =  xhttp.responseText;
				if(xhttp.status == 200){
					getMenu();
				}
				document.getElementById("response").innerText =  xhttp.responseText;
			}
		}
	} else {
		document.getElementById("response").innerText = "Invalid values";
	}
}

function getMenu(){
	let xhttp = new XMLHttpRequest();
	let cafeMenuURL = "http://localhost:8080/CafeMenu/master";
	xhttp.open("GET", cafeMenuURL + "?all=true");
	xhttp.send();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			if(xhttp.status == 200){
				let menu = JSON.parse(xhttp.responseText);
				document.getElementById("menu").innerText = "";
				for(let i in menu){
					document.getElementById("menu").innerHTML += menu[i]["name"] + ": $" + menu[i]["price"] + "<br>";
				}
			} else {
				document.getElementById("response").innerText =  xhttp.responseText;
			}
		}
	}
}