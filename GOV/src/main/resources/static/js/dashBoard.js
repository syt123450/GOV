/**
 * Created by ss on 2017/10/29.
 */
var processNetwork, taskNetwork, tasksInfo;

$(function () {
    department = localStorage.getItem("department");
    name = localStorage.getItem("name");
    keyValue = localStorage.getItem("keyValue");

    if (keyValue != "undefined") {
        keyHex = CryptoJS.enc.Utf8.parse(keyValue);
    }

    $("#showInfo").text(name.charAt(0));
    $("#showName").text(name);
    $("#showDepartment").text(department);

    $.ajax({
        url: '/api/' + department + "/processes/" + name,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        async: true,
        dataType: "text",
        success: function (data) {
            if (keyValue != "undefined") {
                data = JSON.parse(decryptDESECB(data));
            }
            createProcessDiagram(data);
        }
    });

    $("#logout").click(function () {
        localStorage.removeItem("name");
        localStorage.removeItem("password");
        localStorage.removeItem("department");
        location.href = "index.html";
    });

    $("#showAll").click(function () {
        createAllProcessorInfo();
    });

    $("#submit-btn").click(function () {
        updateEncryptionConfig($("#input-box").val());
        $("#config-area").slideUp();
    });

    $("#encryptionSwitch").click(function () {
        if ($(this).prop('checked')) {
            $("#config-area").slideDown();
        } else {
            $("#config-area").slideUp();
            disableEncryptionConfig();
        }
    }).attr("checked", keyValue != "undefined");
});

function updateEncryptionConfig(keyValue) {
    localStorage.setItem("keyValue", keyValue);
    $.ajax({
        url: '/api/update',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        async: true,
        data: JSON.stringify({
            "department": department,
            "userName": name,
            "cipherText": keyValue
        })
    });
}

function disableEncryptionConfig() {
    localStorage.removeItem("keyValue");
    $.ajax({
        url: '/api/disable/' + department + "/" + name,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        async: true
    });
}

function createProcessDiagram(nodesinfo) {

    var container = document.getElementById('processes');
    var data = {
        nodes: nodesinfo
    };
    var options = {
        nodes: {
            shape: 'dot',
            size: 30,
            font: {
                size: 18,
                color: '#ffffff'
            },
            borderWidth: 2
        }
    };
    processNetwork = new vis.Network(container, data, options);

    processNetwork.on("click", function (params) {
        if (params.nodes.length > 0) {

            var processName = processNetwork.body.data.nodes._data[params.nodes[0]].label;
            $("#showAll").show();
            getTasksInfo(processName);
        }
    });
}

function getTasksInfo(processName) {
    $.ajax({
        url: '/api/' + department + "/" + processName + '/tasks/' + name,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        async: true,
        dataType: "text",
        success: function (data) {
            console.log(data);
            if (keyValue != "undefined") {
                data = JSON.parse(decryptDESECB(data));
            }
            tasksInfo = data;
            createTasksDiagram();
        }
    });
}

function createTasksDiagram() {

    var container = document.getElementById('tasks');
    var data = {
        nodes: tasksInfo.taskPoints,
        edges: tasksInfo.taskRelations
    };
    var options = {
        nodes: {
            shape: 'dot',
            size: 30,
            font: {
                size: 16,
                color: '#434d55'
            },
            borderWidth: 2
        }
    };
    taskNetwork = new vis.Network(container, data, options);

    taskNetwork.on("click", function (params) {

        if (params.nodes.length > 0) {
            createOneProcessorInfo(params.nodes[0]);
        }
    });
}

function createOneProcessorInfo(id) {

    var newPoints = tasksInfo.taskPoints.concat(tasksInfo.processors[id]);
    var newRelations = tasksInfo.taskRelations.concat(tasksInfo.processTaskRelation[id]);

    taskNetwork.setData({
        nodes: newPoints,
        edges: newRelations
    });
}

function createAllProcessorInfo() {

    var newPoints = tasksInfo.taskPoints.concat();
    tasksInfo.processors.forEach(function (processorInfo) {
        newPoints = newPoints.concat(processorInfo);
    });

    var newRelations = tasksInfo.taskRelations.concat();
    tasksInfo.processTaskRelation.forEach(function (relations) {
        newRelations = newRelations.concat(relations);
    });

    taskNetwork.setData({
        nodes: newPoints,
        edges: newRelations
    });
}

function decryptDESECB(cipheredText) {
    decrypted = CryptoJS.DES.decrypt({
        ciphertext: CryptoJS.enc.Base64.parse(cipheredText)
    }, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });

    return decrypted.toString(CryptoJS.enc.Utf8)
}
