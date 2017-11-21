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
            })
        });
        showResult();
    });

    $("#close-btn").click(function() {
        $("#alert-box").hide();
    });
});

function showResult() {
    $("#alert-box").show();
}