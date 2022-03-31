package com.example.ordersdelivery.repository;

import com.example.ordersdelivery.dto.DeliveryOrderDetailDTO;
import com.example.ordersdelivery.entity.DeliveryOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryOrderDetailRepository extends JpaRepository<DeliveryOrderDetail, Long> {

    @Query(nativeQuery = true, value = "select _dod.id as id, _dod.do_id as deliveryOrderId, _do.ext_order_id as deliveryOrderExtOrderId, p.name as productName, _dod.qty as qty from deliveryorderdetail _dod\n" +
            "left join deliveryorder _do on _dod.do_id = _do.id\n" +
            "left join product p on p.id = _dod.product_id;")
    List<DeliveryOrderDetailDTO> getDeliveryOrderDetailDTO();

}
