var apiUriWeb = "/getApiResponse";

function apiPost(method) {
    $("#txtJsonResult").val('执行中，请稍等...');
    var baseRequest = {};
    baseRequest.method = method;
    var parameter = {};

    switch (method) {
        case "getEduStudentByIdNumber":
            parameter.idNumber = $("#idNumber").val();
            baseRequest.jsonStringParameter = JSON.stringify(parameter);
            break;
        case "getEduStudentByIdNumberOrPhone":
            parameter.idNumber = $("#idNumber2").val();
            parameter.phone = $("#phone").val();
            baseRequest.jsonStringParameter = JSON.stringify(parameter);
            break;
        case "getFinMajorTuitionGradeByPrimaryKey":
            parameter.id = $("#majorTuitionGradeId").val();
            baseRequest.jsonStringParameter = JSON.stringify(parameter);
            break;
        case "getBizTradeByBizId":
            parameter.bizId = $("#bizId").val();
            baseRequest.jsonStringParameter = JSON.stringify(parameter);
            break;
    }

    $.ajax({
        type: "POST",
        url: apiUriWeb,
        data: baseRequest,
        timeout: 600000,
        success: function (res) {
            $("#txtJsonResult").val(JSON.stringify(res));
        },
        error: function (e) {
            $("#txtJsonResult").val(e.responseText);
        }
    });
}
