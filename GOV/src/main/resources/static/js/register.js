/**
 * Created by ss on 2017/11/8.
 */

$(function() {

    $("#submit").click(function(){

        $.ajax({
            url: '/api/create',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            async: true,
            data: JSON.stringify({
                "departmentName": $("#department-name").val()
            }),
            // dataType: 'json',
            success: function (data) {
                // if (data.result == true) {
                //     location.href = "dashBoard.html";
                // }
            }
        });
    });
});