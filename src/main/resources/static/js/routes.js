function updateRouteList() {

    var route_detail_id = 0;

    var jqxhr = $.getJSON(urlRoutes, function (data) {
        $('#route_tables').remove();

        $('#main_row').append('<div class="col" id="route_tables"><h2>Routes</h2></div>');

        $.each(data, function (i, v) {

            var id_route = 'id_route_' + i;

            var htmlRadio = '<div class="form-check"> <label class="form-check-label" for="flexRadioDefault' + i + '">' + i + '</label> <input class="form-check-input" type="radio" ' +
                'name="flexRadioDefault" id="flexRadioDefault' + i + '"></div>';

            var htmlTr = '<div class="row"><div class="col-2">' + htmlRadio + '</div><div class="col"><table class="table" id="' + id_route + '"><thead class="table-dark"><tr><th width="50%">Product</th><th width="10%">Qty</th><th class="text-end" width="40%"><button type="button" class="btn btn-outline-primary btn-sm">...</button></th></tr></thead></table></div></div>';

            $('#route_tables').append(htmlTr);
            $('#flexRadioDefault' + i).val(v.routeId);

            v.routeDetails.forEach(element => {

                route_detail_id++;
                var pr_route_detail_id = "rd_" + route_detail_id;

                htmlTr = '<tr id="' + pr_route_detail_id + '">';
                htmlTr += '<td>' + element.productName + '</td>';
                htmlTr += '<td>' + element.qty + '</td>';

                htmlTr += '<td class="text-end"><button onclick="sub_1(' + route_detail_id + ')" type="button" class="btn btn-outline-danger btn-sm">-1</button>'
                    + '<button onclick="sub_10(' + route_detail_id + ')" type="button" class="btn btn-outline-danger btn-sm">-10</button>'
                    + '<button onclick="sub_50(' + route_detail_id + ')" type="button" class="btn btn-outline-danger btn-sm">-50</button></td>';

                htmlTr += '</tr>';

                $('#' + id_route).append(htmlTr);
                $('#' + pr_route_detail_id).val(element);

            });
        })

    })
    .done(function () {

    });
}


function getRouteId() {
    return $("input[type='radio']:checked").val();
}


function createNewRoute() {

    $.ajax({
        url: urlPostRoute,
        type: "POST",
        success: function () {
            console.log("Route was created");
        }
    });

    updateRouteList();
}