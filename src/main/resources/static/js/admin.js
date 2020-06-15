let allRoles;
let allUsers;

getAllRoles();
getAllUsers();

function getAllRoles() {
    $.ajax({
        url: "/api/admin/getAllRoles",
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        allRoles = JSON.parse(JSON.stringify(msg));
    })
}

function getAllUsers() {
    $.ajax({
        url: "/api/admin/getAllUsers",
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        allUsers = JSON.parse(JSON.stringify(msg));
        $("#all-users-table tbody").empty();
        $.each(allUsers, (i, user) => {
            $("#all-users-table tbody").append(
                $("<tr>").append(
                    $("<td>").text(user.id),
                    $("<td>").text(user.firstName),
                    $("<td>").text(user.lastName),
                    $("<td>").text(user.age),
                    $("<td>").text(user.email),
                    $("<td>").text(user.roles),
                    $("<td>").append("<button type='button' data-toggle='modal' class='btn-info btn'" +
                        "data-target='#editUserModal' data-user-id='" + user.id + "'>Edit</button>"),
                    $("<td>").append("<button type='button' data-toggle='modal' class='btn btn-danger'" +
                        "data-target='#deleteUserModal' data-user-id='" + user.id + "'>Delete</button>")
                )
            );
        });
    });
}

$('[href="#v-pills-admin"]').on('show.bs.tab', (e) => {
    getAllUsers();
})

$("#editUserModal").on('show.bs.modal', (e) => {
    let userId = $(e.relatedTarget).data("user-id");

    $.ajax({
        url: "/api/admin/getUserById/" + userId,
        type: "GET",
        dataType: "json"
    }).done((msg) => {

        let user = JSON.parse(JSON.stringify(msg));

        $("#idInputEdit").empty().val(user.id);
        $("#firstNameInputEdit").empty().val(user.firstName);
        $("#lastNameInputEdit").empty().val(user.lastName);
        $("#ageInputEdit").empty().val(user.age);
        $("#emailInputEdit").empty().val(user.email);
        $("#rolesInputEdit").empty();
        $.each(allRoles, (i, role) => {
            $("#rolesInputEdit").append(
                $("<option>").text(role)
            )
        });
        $("#rolesInputEdit").val(user.roles);

        $("#buttonEditSubmit").on('click', (e) => {
            e.preventDefault();

            let editUser = {
                id: $("#idInputEdit").val(),
                firstName: $("#firstNameInputEdit").val(),
                lastName: $("#lastNameInputEdit").val(),
                age: $("#ageInputEdit").val(),
                email: $("#emailInputEdit").val(),
                password: $("#passwordInputEdit").val(),
                roles: $("#rolesInputEdit").val()
            }

            $.ajax({
                url: "/api/admin/updateUser",
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(editUser)
            }).done((msgUpdate) => {
                $("#editUserModal").modal('hide');
                getAllUsers();
            })
        })
    });
});


$("#deleteUserModal").on('show.bs.modal', (e) => {
    let userId = $(e.relatedTarget).data("user-id");

    $.ajax({
        url: "/api/admin/getUserById/" + userId,
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        let user = JSON.parse(JSON.stringify(msg));

        $("#idInputDelete").empty().val(user.id);
        $("#firstNameInputDelete").empty().val(user.firstName);
        $("#lastNameInputDelete").empty().val(user.lastName);
        $("#ageInputDelete").empty().val(user.age);
        $("#emailInputDelete").empty().val(user.email);
        $("#rolesInputDelete").empty();
        $.each(allRoles, (i, role) => {
            $("#rolesInputDelete").append(
                $("<option>").text(role)
            )
        });
        $("#rolesInputDelete").val(user.roles);

        $("#buttonDeleteSubmit").on('click', (e) => {
            e.preventDefault();

            $.ajax({
                url: "/api/admin/deleteUserById/" + userId,
                type: "DELETE",
                dataType: "text"
            }).done((deleteMsg) => {
                $("#deleteUserModal").modal('hide');
                getAllUsers();
            })
        })
    });
})

$('[href="#newUser"]').on('show.bs.tab', (e) => {
    $(() => {
        $("#firstNameInputNew").empty().val("");
        $("#lastNameInputNew").empty().val("");
        $("#ageInputNew").empty().val("");
        $("#emailInputNew").empty().val("");
        $("#passwordInputNew").empty().val("");
        $("#rolesInputNew").empty().val("");
        $.each(allRoles, (i, role) => {
            $("#rolesInputNew").append(
                $("<option>").text(role)
            )
        });
    })
})

$("#buttonInputNewSubmit").on('click', (e) => {
    e.preventDefault();

    let newUser = {
        firstName: $("#firstNameInputNew").val(),
        lastName: $("#lastNameInputNew").val(),
        age: $("#ageInputNew").val(),
        email: $("#emailInputNew").val(),
        password: $("#passwordInputNew").val(),
        roles: $("#rolesInputNew").val()
    }

    $.ajax({
        url: "/api/admin/createUser",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(newUser)
    }).done((msgSave) => {
        getAllUsers();
        $('#AdminTabs a[href="#usersTable"]').tab('show');
    })
})

