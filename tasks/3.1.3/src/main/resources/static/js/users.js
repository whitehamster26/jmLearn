let roleList = [];

const API_URL = "http://localhost:8080/api/v1/"

getAllUsers();

function getAllUsers() {
    $.getJSON(`${API_URL}list`, function (data) {
        let rows = '';
        $.each(data, function (key, user) {
            rows += createRows(user);
        });
        $('#tableAllUsers').append(rows);

        $.ajax({
            url: `${API_URL}authorities`,
            method: 'GET',
            dataType: 'json',
            success: function (roles) {
                roleList = roles;
            }
        });
    });
}

function createRows(user) {

    let user_data = '<tr id=' + user.id + '>';
    user_data += '<td>' + user.id + '</td>';
    user_data += '<td>' + user.username + '</td>';
    user_data += '<td>' + user.lastName + '</td>';
    user_data += '<td>' + user.phoneNumber + '</td>';
    user_data += '<td>' + user.email + '</td>';
    user_data += '<td>';
    let roles = user.authorities;
    for (let role of roles) {
        user_data += role.role.replace('ROLE_', '') + ' ';
    }
    user_data += '</td>' +
        '<td>' + '<input id="btnEdit" value="Edit" type="button" ' +
        'class="btn-info btn edit-btn" data-toggle="modal" data-target="#editModal" ' +
        'data-id="' + user.id + '">' + '</td>' +

        '<td>' + '<input id="btnDelete" value="Delete" type="button" class="btn btn-danger del-btn" ' +
        'data-toggle="modal" data-target="#deleteModal" data-id=" ' + user.id + ' ">' + '</td>';
    user_data += '</tr>';

    return user_data;
}

function getUserRolesForEdit() {
    var allRoles = [];
    $.each($("select[name='editRoles'] option:selected"), function () {
        var role = {};
        role.id = $(this).attr('id');
        role.role = $(this).attr('name');
        allRoles.push(role);
        console.log("role: " + JSON.stringify(role));
    });
    return allRoles;
}

$(document).on('click', '.edit-btn', function () {
    const user_id = $(this).attr('data-id');
    console.log("editUserId: " + JSON.stringify(user_id));
    $.ajax({
        url: `${API_URL}list/${user_id}`,
        method: 'GET',
        dataType: 'json',
        success: function (user) {
            $('#editId').val(user.id);
            $('#editName').val(user.username);
            $('#editLastName').val(user.lastName);
            $('#editPhoneNumber').val(user.phoneNumber);
            $('#editEmail').val(user.email);
            $('#editPassword').val(user.password);
            $('#editRole').empty();
            roleList.map(role => {
                let flag = user.authorities.find(item => item.id === role.id) ? 'selected' : '';
                $('#editRole').append('<option id="' + role.id + '" ' + flag + ' name="' + role.role + '" >' +
                    role.role.replace('ROLE_', '') + '</option>')
            })

        }
    });
});


$('#editButton').on('click', (e) => {
    e.preventDefault();

    let userEditId = $('#editId').val();

    var editUser = {
        id: $("input[name='id']").val(),
        username: $("input[name='username']").val(),
        lastName: $("input[name='lastName']").val(),
        phoneNumber: $("input[name='phoneNumber']").val(),
        email: $("input[name='email']").val(),
        password: $("input[name='password']").val(),
        roles: getUserRolesForEdit()

    }
    $.ajax({
        url: `${API_URL}admin`,
        method: 'PUT',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(editUser),
        success: (data) => {
            let newRow = createRows(data);
            console.log("newRow: " + newRow)
            $('#tableAllUsers').find('#' + userEditId).replaceWith(newRow);
            $('#editModal').modal('hide');
            $('#admin-tab').tab('show');
        },
        error: () => {
            console.log("error editUser")
        }
    });
});

$(document).on('click', '.del-btn', function () {

    let user_id = $(this).attr('data-id');
    console.log("userId: " + JSON.stringify(user_id));

    $.ajax({
        url: `${API_URL}list/${+user_id}`,
        method: 'GET',
        dataType: 'json',
        success: function (user) {
            $('#delId').empty().val(user.id);
            $('#delName').empty().val(user.username);
            $('#delLastName').empty().val(user.lastName);
            $('#delPhoneNumber').empty().val(user.phoneNumber);
            $('#delEmail').empty().val(user.email);
            $('#delPassword').empty().val(user.password);
            $('#delRole').empty();
            roleList.map(role => {
                let flag = user.authorities.find(item => item.id === role.id) ? 'selected' : '';
                $('#delRole').append('<option id="' + role.id + '" ' + flag + ' name="' + role.role + '" >' +
                    role.role.replace('ROLE_', '') + '</option>')
            })
        }
    });
});


$('#deleteButton').on('click', (e) => {
    e.preventDefault();
    let userId = $('#delId').val();
    $.ajax({
        url: `${API_URL}admin/${+userId}`,
        method: 'DELETE',
        success: function () {
            $('#' + userId).remove();
            $('#deleteModal').modal('hide');
            $('#admin-tab').tab('show');
        },
        error: () => {
            console.log("error delete user")
        }
    });
});


function getUserRolesForAdd() {
    var allRoles = [];
    $.each($("select[name='addRoles'] option:selected"), function () {
        var role = {};
        role.id = $(this).attr('id');
        role.role = $(this).attr('name');
        allRoles.push(role);
        console.log("role: " + JSON.stringify(role));
    });
    return allRoles;
}

$('.newUser').on('click', () => {

    $('#name').empty().val('')
    $('#lastName').empty().val('')
    $('#phoneNumber').empty().val('')
    $('#email').empty().val('')
    $('#password').empty().val('')
    $('#addRole').empty().val('')
    roleList.map(role => {
        $('#addRole').append('<option id="' + role.id + '" name="' + role.role + '">' +
            role.role.replace('ROLE_', '') + '</option>')
    })
})

$("#addNewUserButton").on('click', () => {
    let newUser = {
        username: $('#name').val(),
        lastName: $('#lastName').val(),
        phoneNumber: $('#phoneNumber').val(),
        email: $('#email').val(),
        password: $('#password').val(),
        roles: getUserRolesForAdd()
    }

    $.ajax({
        url: `${API_URL}admin`,
        method: 'POST',
        dataType: 'json',
        data: JSON.stringify(newUser),
        contentType: 'application/json; charset=utf-8',
        success: function () {
            $('#tableAllUsers').empty();
            getAllUsers();
            $('#admin-tab').tab('show');
        },
        error: function () {
            alert('error addUser')
        }
    });
});