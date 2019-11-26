
function checkValidation(){
	if ( (document.getElementById('createUsername').value.length < 3 ) || (document.getElementById('createUsername').value.length > 16 ) || (document.getElementById('createPassword').value.length < 5 ) || ( !hasNumber(document.getElementById('createPassword').value)) || (!hasCapital(document.getElementById('createPassword').value ))){
		document.getElementById('errorbox').style.display="inline-block";
		document.getElementById("bt1").disabled = true;
	} else {
		document.getElementById('errorbox').style.display="none";
		document.getElementById("bt1").disabled = false;
	}
	
	if ( document.getElementById('createUsername').value.length < 3 || document.getElementById('createUsername').value.length > 16 ){
		invalid('v1');
	} else {
		valid('v1');
	}
	
	if (document.getElementById('createPassword').value.length < 5 ) {
		invalid('v2');
	} else {
		valid('v2');
	}
	
	if ( !hasNumber(document.getElementById('createPassword').value) || !hasCapital(document.getElementById('createPassword').value )){
		invalid('v3');
	} else {
		valid('v3');
	}
	
	
}

function hasNumber(String) {
  return /\d/.test(String);
}

function hasCapital(String) {
	return /[A-Z]/.test(String);
}

function invalid(String){
		document.getElementById(String).style.fontWeight="bold";
		document.getElementById(String).style.color="red";
}

function valid(String){
		document.getElementById(String).style.fontWeight="normal";
		document.getElementById(String).style.color="black";
}

	
function switchToLogin(){
	document.getElementById('signupbox').style.display = 'none';
	document.getElementById('loginbox').style.display = 'flex';
	document.getElementById('errorbox').style.display="none";
}

function switchToSignup(){
	document.getElementById('loginbox').style.display = 'none';
	document.getElementById('signupbox').style.display = 'flex';
	checkValidation();
}		


function myDataLoadedFunction(){
	if (xmlhttp.readyState == 4 && xmlhttp.status==200){
		eval(xmlhttp.responseText);

	}
}


function asyncLoad1(){
	if (window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}
	else {
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=myDataLoadedFunction;	
	xmlhttp.open("GET",'/createaccount.html?username='+document.getElementById("createUsername").value+'&password='+document.getElementById("createPassword").value,true);		
	xmlhttp.send();
}


function asyncLoad2(){
	if (window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}
	else {
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=myDataLoadedFunction;	
	xmlhttp.open("GET",'/loginsubmit.html?username='+document.getElementById("createUsername2").value+'&password='+document.getElementById("createPassword2").value,true);		
	xmlhttp.send();
}

 function setCookie(cname, cvalue, exdays)
	    {
	      var d = new Date();
		  d.setTime(d.getTime() + (exdays*24*60*60*1000));
	      var expires = 'expires='+d.toUTCString();
		  document.cookie = cname + '=' + cvalue + ';' + expires + ';path=/';
	     }



// alert stuff

var AlertBox = function(id, option) {
  this.show = function(msg) {
    if (msg === ''  || typeof msg === 'undefined' || msg === null) {
      throw '"msg parameter is empty"';
    }
    else {
      var alertArea = document.querySelector(id);
      var alertBox = document.createElement('DIV');
      var alertContent = document.createElement('DIV');
      var alertClose = document.createElement('A');
      var alertClass = this;
      alertContent.classList.add('alert-content');
      alertContent.innerText = msg;
      alertClose.classList.add('alert-close');
      alertClose.setAttribute('href', '#');
      alertBox.classList.add('alert-box');
      alertBox.appendChild(alertContent);
      if (!option.hideCloseButton || typeof option.hideCloseButton === 'undefined') {
        alertBox.appendChild(alertClose);
      }
      alertArea.appendChild(alertBox);
      alertClose.addEventListener('click', function(event) {
        event.preventDefault();
        alertClass.hide(alertBox);
      });
      if (!option.persistent) {
        var alertTimeout = setTimeout(function() {
          alertClass.hide(alertBox);
          clearTimeout(alertTimeout);
        }, option.closeTime);
      }
    }
  };

  this.hide = function(alertBox) {
    alertBox.classList.add('hide');
    var disperseTimeout = setTimeout(function() {
      alertBox.parentNode.removeChild(alertBox);
      clearTimeout(disperseTimeout);
    }, 500);
  };
};


var alertbox = new AlertBox('#alert-area', {
  closeTime: 5000,
  persistent: false,
  hideCloseButton: false
});



