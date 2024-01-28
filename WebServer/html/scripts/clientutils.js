const clientUtils = {};

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

// Initiates a POST request to the server.
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