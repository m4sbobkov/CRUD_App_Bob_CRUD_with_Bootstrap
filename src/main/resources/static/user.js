const url = 'http://localhost:8080/api/user/'
let loggedInUser = document.querySelector('#userInfo')

fetch(url)
    .then(res => res.json())
    .then(data => {
        loggedInUser.innerHTML = `
                                <td>${data.id}</td>
                                <td>${data.name}</td>
                                <td>${data.age}</td>
                                <td>${data.username}</td>
                                <td>${data.email}</td>
                                <td>${data.roles}</td>
                                `
    })