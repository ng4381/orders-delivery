function updateOrderDetails() {

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

            var idTr = 'idTr_' + i;
            var idInput = i;

            var htmlTr = '<tr id= ' + idTr + '><td>' + v.deliveryOrderId + '</td>'
                + '<td>' + v.deliveryOrderExtOrderId + '</td>'
                + '<td>' + v.productName + '</td>'
                + '<td>' + v.qty + '</td>'
                + '<td>' + v.remain + '</td>'
                + '<td class="text-end">'
                + '<button onclick="add_1(' + i + ')" type="button" class="btn btn-outline-primary btn-sm">+1</button>'
                + '<button onclick="add_10(' + i + ')" type="button" class="btn btn-outline-primary btn-sm">+10</button>'
                + '<button onclick="add_50(' + i + ')" type="button" class="btn btn-outline-primary btn-sm">+50</button>'
                + '</td>'
                + '</tr>';

            $('#deliveryOrders').append(htmlTr);
            $('#' + idTr).val(v);

        })

    })
    .done(function () {

    });


}