/**
 * Created by ss on 2017/10/29.
 */

$(function () {

    name = localStorage.getItem("name");
    password = localStorage.getItem("password");
    department = localStorage.getItem("department");

    $.ajax({
        url: '/api/check',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        async: true,
        data: JSON.stringify({
            "name": name,
            "password": password,
            "department": department
        }),
        dataType: 'json',
        success: function (data) {
            if (data.result == true) {

                location.href = "dashBoard.html";
            }
        }
    });

    $("#login").click(function () {
        department = $("#usertype select").val();
        name = $("#username input").val();
        password = $("#password input").val();

        $.ajax({
            url: '/api/check',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            async: true,
            data: JSON.stringify({
                "name": name,
                "password": password,
                "department": department
            }),
            dataType: 'json',
            success: function (data) {
                if (data.result == true) {

                    localStorage.setItem("name", name);
                    localStorage.setItem("password", password);
                    localStorage.setItem("department", department);

                    location.href = "dashBoard.html";
                }
            }
        });
    });
});
