$("a#logout").click(function () {

    $.get("logout")
        .done(function (data, textStatus, xhr) {
            window.location = window.origin + "/cinema/";
        })
        .fail(function () {
            alert("Can't logout");
        });
});
