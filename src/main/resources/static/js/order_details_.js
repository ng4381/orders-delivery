
class OrderDetail {

    tableId = "";
    trId = "";

    id = 0;
    deliveryOrderId = 0;
    deliveryOrderExtOrderId = 0;
    productName = "";
    qty = 0;
    remain = 0;

    constructor() {}
}

var od_map = new Map();

var od_arr = [];

function updateOrderDetails() {

    od_arr = [];

    console.log('qqqq');


    var jqxhr = $.getJSON(urlDeliveryOrderDetails, function (data) {

        $('#deliveryOrders').find('tr').remove();

        $('#deliveryOrders').append(
            '<thead class="table-dark"><tr>' +
            '<th width="5%">Id</th>' +
            '<th width="15%">Ext. order</th>' +
            '<th width="40%">Product</th>' +
            '<th width="10%">qty (total)</th>' +
            '<th width="10%">qty (remain)</th>' +
            '<th width="20%"></th>' +
            '</tr></thead>');

        $.each(data, function (i, v) {

            var odId = v.id;
            var od_id = OD_TR_PREFIX + v.id;

            var htmlTr = '<tr id= ' + od_id + '><td>' + v.deliveryOrderId + '</td>'
                + '<td>' + v.deliveryOrderExtOrderId + '</td>'
                + '<td>' + v.productName + '</td>'
                + '<td name="qty">' + v.qty + '</td>'
                + '<td name="remain">' + v.remain + '</td>'
                + '<td class="text-end">'
                + '<button onclick="add_1(' + odId + ')" type="button" class="btn btn-outline-primary btn-sm">+1</button>'
                + '<button onclick="add_10(' + odId + ')" type="button" class="btn btn-outline-primary btn-sm">+10</button>'
                + '<button onclick="add_50(' + odId + ')" type="button" class="btn btn-outline-primary btn-sm">+50</button>'
                + '</td>'
                + '</tr>';

            $('#deliveryOrders').append(htmlTr);

            var orderDetail = new OrderDetail();
            for(var k in v) orderDetail[k]=v[k];

            orderDetail.tableId = "";
            orderDetail.trId = od_id;
            console.log('orderDetail.productName = ' + orderDetail.productName);

            od_arr.push(orderDetail);

        })

    })
    .done(function () {

    });



}