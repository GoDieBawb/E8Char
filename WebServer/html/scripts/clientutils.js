const clientUtils = {};

// searches a child element of an element.
// valid strings for searchMode: "class", "id" and "tag".
// returns null if the child element to search for doesn't exist.
HTMLCollection.prototype.findInParticular = function(searchMode, name) {
    for (const element of this) {
        if (searchMode == "class") {
            if (element.className.search(name) >= 0)
                return element;
        }
        else if (searchMode == "id") {
            if (element.id == name)
                return element;
        }
        else if (searchMode == "tag") {
            if (element.tagName.toLowerCase() == name)
                return element;
        }
    }

    return null;
}

/*
    client's basic post
    postType:           the pre-determined post type
    accessToken:        the client's authorized access token
    responseCallback:   the function to call upon receiving a server response (client usage only)

    after you create a new ClientPost object, add any additional parameters required by the form using
    addParam().
    
    the postType, accessToken and responseCallback parameters MUST exist at a minimum.
*/
clientUtils.ClientPost = function(postType, accessToken, responseCallback) {
    this.postType = postType;
    this.accessToken = accessToken;
    this.responseCallback = responseCallback;

    this.addParam = function(paramName, paramValue) {
        this[paramName] = paramValue;
    }
}

clientUtils.getCookie = function(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');

    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];

        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }

        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }

    return null;
}

clientUtils.setCookie = function(cname, cvalue) {
    document.cookie = cname + "=" + cvalue + ";path=/";
}

clientUtils.delCookie = function(cname) {
    document.cookie = cname + "=;Max-Age=-99999999;";
}

/* 
    initiates a POST request to the server.
    it takes in a ClientPost object as its only argument.
    if successful, it calls clientPostObject.responseCallback with 
    the json OBJECT (the json is parsed for you) as its only argument as the response.
*/
clientUtils.webPost = async function(clientPostObject) {
    if (clientPostObject.constructor != clientUtils.ClientPost) {
        console.warn("webPost: malformed clientPostObject; type must be clientUtils.ClientPost!");
        return;
    }

    console.log(clientPostObject);

    let jsonObject = null;

    try {
        const serverResponse = await fetch('localhost', {
            method: "POST",
            mode: "cors",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(clientPostObject)
        });

        if (serverResponse.redirected)
            console.warn("webPost: unexpected redirect!");
        
        if (!serverResponse.ok)
            throw serverResponse.statusText;

        jsonObject = await serverResponse.json();
    }
    catch (error) {
        console.error(`webPost: error!\npostType: ${clientPostObject.postType}\nreason: ${error}`);
    }
    finally {
        if (jsonObject != null) {
            if (jsonObject.outcome == "failed") {
                clientUtils.burnSession();
                clientUtils.goto("Login");
            }
            else
                clientPostObject.responseCallback(jsonObject);
        }
    }
}

clientUtils.setNavigationPath = function(pathString) {
    let navigationPathBar = document.getElementById("navigation-path-bar");
    navigationPathBar.innerText = pathString;
}

clientUtils.setLoadingStatus = function(isLoading, loadDisplayID, message) {
    let loadDisplayElement =  document.getElementById(loadDisplayID)
    loadDisplayElement.hidden = !isLoading;

    if (isLoading)
        loadDisplayElement.children.findInParticular("tag", "span").innerText = message;
}

clientUtils.burnSession = function() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++)
        clientUtils.delCookie(cookies[i].split("=")[0]);

    sessionStorage.clear();
}

clientUtils.goto = function(url) {
    document.location.href = url;
}

clientUtils.ISOToLaymanDate = function(ISODate) {
    return ISODate.split("T")[0];
}

clientUtils.getTodaysDate = function() {
    let now = new Date(Date.now());
	return `${now.getFullYear()}-${now.getMonth().toString().padStart(2, '0')}-${now.getDay().toString().padStart(2, '0')}`;
}

clientUtils.toReadablePhone = function(rawPhone) {
    return `(${rawPhone.substring(0, 3)}) ${rawPhone.substring(3, 6)}-${rawPhone.substring(6, 10)}`;
}

clientUtils.clearHTMLList = function(HTMLListReference) {
    while (HTMLListReference.children.length > 0)
        HTMLListReference.firstChild.remove();
}

clientUtils.changeInstructionalMessage = function(iframeReference, msg) {
    iframeReference.contentWindow.document.querySelector("#template-message").innerText = msg;
}

/* Returns a dictionary (key-value pairs) representing the given form element.
   The passed form element must be a valid (with valid input elements).

   https://www.w3schools.com/html/html_form_elements.asp
*/
clientUtils.getFormData = function(formElement) {
    const formData = new FormData(formElement);
    let obj = {};

    formData.forEach((val, key) => obj[key] = val);

    return obj;
}

clientUtils.getFromDemogaphicData = function(propertyName) {
    let demoData = sessionStorage.getItem("preloadedDashboardData");
    if (!demoData) {
        console.warn("Preloaded demographic data cannot be read. It doesn't exist.");
        return;
    }
    return JSON.parse(sessionStorage.getItem("preloadedDashboardData")).demographicData[propertyName];
}