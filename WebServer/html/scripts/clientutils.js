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
    if successful, it calls clientPostObject.responseCallback with the json OBJECT 
    as its only argument as the response.
*/
clientUtils.webPost = async function(clientPostObject) {
    if (clientPostObject.constructor != clientUtils.ClientPost) {
        console.error("webPost: malformed clientPostObject; type must be clientUtils.ClientPost!");
        return;
    }

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

        const jsonObject = await serverResponse.json();
        clientPostObject.responseCallback(jsonObject);
    }
    catch (error) {
        console.error(`webPost: error!\npostType: ${clientPostObject.postType}\nreason: ${error}`);
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