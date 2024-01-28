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

// Initiates a POST request to the server, then calls the respone callback function
// with the response JSON string.
clientUtils.webPost = function(postType, data, responseCallBack) {
    try {
        fetch('localhost', {
            method: "POST",
            mode: "cors",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)

            })
            .then(response => response.json())
            .then(response => responseCallBack(JSON.stringify(response)));   
    }
    catch (error) {
        console.error(`There was a webPost error.\npostType: ${postType}\nreason: ${error}`);
    }
}

clientUtils.setNavigationPath = function(pathString) {
    let navigationPathBar = document.getElementById("navigation-path-bar");
    navigationPathBar.innerText = pathString;
}

clientUtils.setLoadingStatus = function(isLoading, loadUIElement) {
    if (isLoading)
        loadUIElement.hidden = true;
    else
        loadUIElement.hidden = false;
}