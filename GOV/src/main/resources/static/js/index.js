/**
 * Created by ss on 2017/10/29.
 */

$(function () {

    name = localStorage.getItem("name");
    password = localStorage.getItem("password");
    department = localStorage.getItem("department");

    if (name != "undefined" && password != "undefined" && department != undefined) {
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
    }

    $.ajax({
        url: '/api/allDepartments',
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        async: true,
        dataType: 'json',
        success: function (data) {
            data.forEach(function (departmentName) {
                $("#departments").append("<option value=" + departmentName + ">" + departmentName + "</option>");
            });
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

                if (data.result) {

                    localStorage.setItem("name", name);
                    localStorage.setItem("password", password);
                    localStorage.setItem("department", department);
                    localStorage.setItem("keyValue", data.keyValue);
                    localStorage.setItem("departmentResource", data.departmentResource);

                    location.href = "dashBoard.html";
                } else {
                    $("#alert-box").show();
                }
            }
        });
    });

    $("#close-btn").click(function () {
        $("#alert-box").hide();
    });
});
