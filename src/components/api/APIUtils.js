import { ACCESS_TOKEN, API_BASE_URL } from "./constants/constants";

const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        console.log("Jest wyszukane local storage")
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
        console.log(localStorage);
        console.log(headers);
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                return Promise.reject(json);
            }
            return json;
        })
    );
};

export function siema() {
    // console.log(API_BASE_URL);

    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        console.log("Jest wyszukane local storage")
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
        console.log(localStorage);
        console.log(headers);
    }

    const defaults = {headers: headers};
    // const options = Object.assign({}, defaults, options);

    fetch("http://localhost:8080/siema", defaults)
        .then(response => response.json())
        .then(data => console.log(data));

    // .then(response => {
        //     const data = response.json();
        //     console.log(data)
            
        //     return data;
        // })
        // .then(data => console.log("data", data))
        //     .catch(error => {
        //     console.log("error", error);
        // });

    // return request({
        
    //     url: API_BASE_URL + "/siema",
    //     method: 'GET',
    //     body: JSON.stringify(siemaRequest)
    // });
}

export function signup(signupRequest) {
    console.log(API_BASE_URL);
    console.log(signupRequest);
    return request({
        
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}

export function login(loginRequest) {
    return request({
        url: API_BASE_URL + "/auth/signin",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    }).then(response => {
            console.log(response);
            localStorage.setItem("accessToken", response.accessToken);
            console.log(localStorage);
            console.log("Hej0");  
    });
}