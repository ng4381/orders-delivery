class RouteDetail {

    tableId = "";
    trId = "";

    routeId = 0;
    routeDetailId = 0;
    deliveryOrderDetailId = 0;
    productName = "";
    qty = 0;

    constructor() {}
}

var rd_arr = [];

var newRouteDetailId = -1;

function updateRouteList() {

    rd_arr = [];


    var jqxhr = $.getJSON(urlRoutes, function (data) {
        $('#route_tables').remove();

        $('#main_row').append('<div class="col" id="route_tables"><h2>Routes</h2></div>');

        $.each(data, function (i, v) {

            var routeId = v.routeId;
            var route_id = RD_TABLE_PREFIX + routeId;

            var htmlRadio = '<div class="form-check"> <label class="form-check-label" for="flexRadioDefault' + i + '">' + i + '</label> <input class="form-check-input" type="radio" ' +
                'name="flexRadioDefault" id="flexRadioDefault' + i + '"></div>';

            var htmlTr = '<div class="row"><div class="col-2">' + htmlRadio + '</div><div class="col"><table class="table" id="' + route_id + '"><thead class="table-dark"><tr><th width="50%">Product</th><th width="10%">Qty</th><th class="text-end" width="40%"><button type="button" class="btn btn-outline-primary btn-sm">...</button></th></tr></thead></table></div></div>';

            $('#route_tables').append(htmlTr);
            $('#flexRadioDefault' + i).val(v.routeId);

            v.routeDetails.forEach(element => {
                addRouteDetailToTable(element, routeId);
            });
        })

    })
    .done(function () {
        
    });
}

function addRouteDetailToTable(element, routeId) {

        var element_routeDetailId = element.routeDetailId;
        var route_detail_id = RD_TR_PREFIX + element_routeDetailId;

        var route_id = RD_TABLE_PREFIX + routeId;


        htmlTr = '<tr id="' + route_detail_id + '">';
        htmlTr += '<td>' + element.productName  + '</td>';
        htmlTr += '<td name="qty">' + element.qty + '</td>';

        htmlTr += '<td class="text-end"><button onclick="sub_1(' + element_routeDetailId + ')" type="button" class="btn btn-outline-danger btn-sm">-1</button>'
            + '<button onclick="sub_10(' + element_routeDetailId + ')" type="button" class="btn btn-outline-danger btn-sm">-10</button>'
            + '<button onclick="sub_50(' + element_routeDetailId + ')" type="button" class="btn btn-outline-danger btn-sm">-50</button></td>';
        htmlTr += '</tr>';

        $('#' + route_id).append(htmlTr);

        var routeDetail = new RouteDetail();
        for(var k in element) routeDetail[k]=element[k];

        routeDetail.tableId = route_id;
        routeDetail.trId = route_detail_id;
        console.log('orderDetail.productName = ' + routeDetail.productName);

        rd_arr.push(routeDetail);
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

function modifyRouteDetail(routeId, orderDetail, qty) {

    console.log('=========================================');
    console.log('routeId = ' + routeId);
    console.log('orderDetail.id = ' + orderDetail.id);

    console.log('=========================================');

    rd_arr.forEach(function(item, index, array) {
      console.log(item, index);
    });

    var routeDetails = rd_arr.filter(el => (el.routeId == routeId && el.deliveryOrderDetailId == orderDetail.id));
    if(routeDetails.length == 0) {
        console.log('create new row');

        var _od = $('#' + orderDetail.trId);

        var element = new Object();
        element.routeId               = routeId;
        element.deliveryOrderDetailId = orderDetail.id;
        element.productName           = orderDetail.productName;
        element.qty                   = qty;
        newRouteDetailId--;
        element.routeDetailId         = newRouteDetailId;

        addRouteDetailToTable(element, routeId);

    }else {
        console.log('add to existing');
        var routeDetail = routeDetails[0];

        var el = $('#' + routeDetail.trId).find('td[name="qty"]');
        var el_qty = parseInt(el.text(), 10);
        var res = el_qty + qty;

        el.text(res);
    }

    // calculate remains
    var od_remain = $('#' + orderDetail.trId).find('td[name="remain"]');
    var od_remain_int =  parseInt(od_remain.text(), 10);
    od_remain.text(od_remain_int - qty);



/*
    var v;
    var foundRouteDetail = false;
    var rd_id;

    rd_map.forEach(function logMapElements(value, key, map) {

                       if( value.routeId == routeId && value.deliveryOrderDetailId == odId ) {

                           foundRouteDetail = true;
                           console.log(key);
                           console.log(value);
                           console.log('routeId = ' + routeId);
                           console.log('odId = ' + odId);
                           console.log('Founded!!!!');

                           v = value;
                           rd_id = key;
                       }
                   });

    if(foundRouteDetail==true) {
        console.log('add to existing');
        console.log('#' + rd_id);

        var el = $('#' + rd_id).find('td[name="qty"]');
        var el_qty = parseInt(el.text(), 10);
        var res = el_qty + qty;

        el.text(res);



    } else {
        console.log('create new row');

        var v_od = $('#idTr_' + odId).val();

        var element = new Object();
        element.routeId               = routeId;
        element.deliveryOrderDetailId = odId;
        element.productName           = v_od.productName;
        element.qty                   = qty;

        addRouteDetailToTable(element, 'id_route_' + routeId);
    }

    var od_remain = $('#idTr_' + odId).find('td[name="remain"]');
    var od_remain_int =  parseInt(od_remain.text(), 10);

    od_remain.text(od_remain_int - qty);
    */

}