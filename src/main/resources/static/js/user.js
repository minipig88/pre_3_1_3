getCurrent();

function getCurrent() {
    $.ajax({
        url: "/getCurrentUser",
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        let user = JSON.parse(JSON.stringify(msg));
        $("#current-user-table tbody").empty().append(
            $("<tr>").append(
                $("<td>").text(user.id),
                $("<td>").text(user.firstName),
                $("<td>").text(user.lastName),
                $("<td>").text(user.age),
                $("<td>").text(user.email),
                $("<td>").text(user.roles)
            ));
    }).fail(() => {
        alert("Error Get All Users!!!")
    })
}

$('[href="#v-pills-user"]').on('show.bs.tab', (e) => {
    getCurrent();
})

