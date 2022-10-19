const url = "http://localhost:8080/admin/api/users/"
const userUrl = 'http://localhost:8080/api/user/'
const info = document.querySelector('#users-list')
const tabTrigger = new bootstrap.Tab(document.getElementById('nav-home-tab'))

let users = [];


const listUsers = async (users) => {
    const response = await fetch(url);

    if (response.ok) {
        let json = await response.json()
            .then(data => fillUserRow(data));
    } else {
        alert("Îøèáêà HTTP: " + response.status);
    }

    function fillUserRow(users) {
        output = ''
        users.forEach(user => {
            output += ` 
              <tr> 
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.roles}</td>
              <td> 
                   <button type="button" data-action="edit" class="btn btn-info text-white"
                        data-toggle="modal" data-target="modal" id="edit-user" data-id="${user.id}">Edit</button>
               </td> 
               <td> 
                   <button type="button" class="btn btn-danger" id="delete-user" data-action="delete"
                       data-id="${user.id}" data-target="modal">Delete</button>
                    </td> 
              </tr>`
        })
        info.innerHTML = output;
    }
}

const updateUser = (user) => {
    const index = users.findIndex(x => x.id === user.id);
    users[index] = user;
    listUsers(users);
}


// User info


let loggedInUser = document.querySelector('#userInfo')

fetch(userUrl)
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


// GET ALL users

fetch(url, {mode: 'cors'})
    .then(res => res.json())
    .then(data => {
        users = data;
        listUsers(data)
    })

//Add user

const newUserForm = document.getElementById('newUserForm')
const newUserRoles = document.getElementById('newUser-checkbox')
let newUserAdminCheckbox = document.getElementById('role-admin')
let newUserUserCheckbox = document.getElementById('role-user')


newUserForm.addEventListener('submit', (event) => {
    event.preventDefault()
    const formData = new FormData(newUserForm)
    const newUser = {
        roles: []
    }

    formData.forEach((value, key) => {
        newUser[key] = value
    })
    if (newUserAdminCheckbox.checked) {
        newUser.roles.push("ADMIN")
    }
    if (newUserUserCheckbox.checked) {
        newUser.roles.push("USER")
    }


    for (let [key, value] of formData) {
        console.log(`${key} - ${value}`)
    }


    fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=UTF-8',
            'Referer': null
        },
        body: JSON.stringify(newUser)
    })
        .then(data => updateUser(data))
        .then(() => newUserForm.reset())
        .catch((e) => console.error(e))

    tabTrigger.show()

})

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

//Edit user
let updUserAdminCheckbox = document.getElementById('edit-role-admin')
let updUserUserCheckbox = document.getElementById('edit-role-user')


on(document, 'click', '#edit-user', e => {
    const userInfo = e.target.parentNode.parentNode
    document.getElementById('edit-Id').value = userInfo.children[0].innerHTML
    document.getElementById('edit-name').value = userInfo.children[1].innerHTML
    document.getElementById('edit-age').value = userInfo.children[2].innerHTML
    document.getElementById('edit-email').value = userInfo.children[4].innerHTML
    document.getElementById('edit-username').value = userInfo.children[3].innerHTML
    document.getElementById('edit-password').value = ''
    userInfo.children[5].innerHTML.split(',').forEach(role => {
        if (role === 'USER') {
            updUserUserCheckbox.checked = true
        }
        if (role === 'ADMIN') {
            updUserAdminCheckbox.checked = true
        }
    })


    $("#edit-user-modal").modal("show")
})

const editUserForm = document.getElementById('editForm')

editUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(editUserForm);
    const updUser = {
        roles: []
    };

    formData.forEach((value, key) => {

        updUser[key] = value;

    });

    if (updUserAdminCheckbox.checked) {
        updUser.roles.push("ADMIN")
    }
    if (updUserUserCheckbox.checked) {
        updUser.roles.push("USER")
    }

    fetch(url, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=UTF-8',
            'Referer': null
        },
        body: JSON.stringify(updUser)
    })
        .then(data => updateUser(data))
        .catch((e) => console.error(e))

    $("#edit-user-modal").modal("hide")
})

// DELETE user
const removeUser = (id) => {
    users = users.filter(user => user.id !== id);
    listUsers(users);
}

let currentUserId = null;


on(document, 'click', '#delete-user', e => {
    const userInfo = e.target.parentNode.parentNode

    currentUserId = userInfo.children[0].innerHTML

    document.getElementById('delete-Id').value = userInfo.children[0].innerHTML
    document.getElementById('delete-name').value = userInfo.children[1].innerHTML
    document.getElementById('delete-age').value = userInfo.children[2].innerHTML
    document.getElementById('delete-email').value = userInfo.children[4].innerHTML
    document.getElementById('delete-username').value = userInfo.children[3].innerHTML
    userInfo.children[5].innerHTML.split(',').forEach(role => {
        if (role === 'USER') {
            document.getElementById('delete-role-user').checked = true
        }
        if (role === 'ADMIN') {
            document.getElementById('delete-role-admin').checked = true
        }
    })
    $("#delete-user-modal").modal("show")
})


const deleteUserForm = document.querySelector('#deleteForm')
deleteUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();
    fetch(url + currentUserId, {
        method: 'DELETE'
    })
        .then()
        .then(() => {
            removeUser(currentUserId);
            deleteUserForm.removeEventListener('submit', () => {
            });
            $("#delete-user-modal").modal("hide")
        })
})



