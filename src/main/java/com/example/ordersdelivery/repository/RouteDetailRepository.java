package com.example.ordersdelivery.repository;

import com.example.ordersdelivery.dto.RouteDetailsDTO;
import com.example.ordersdelivery.entity.RouteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteDetailRepository extends JpaRepository<RouteDetail, Long> {
    @Query(nativeQuery = true, value = "select r.id as routeId,  \n" +
            "rd.id as routeDetailId,  \n" +
            "CASE WHEN dod.id IS NOT NULL THEN dod.id ELSE 0 END as deliveryOrderDetailId, \n" +
            "CASE WHEN dod.product_id IS NOT NULL THEN dod.product_id ELSE 0 END as productId, \n" +
            "dod.do_id as deliveryOrderId, \n" +
            "od.ext_order_id as deliveryOrderExtOrderId, \n" +
            "CASE WHEN p.name IS NOT NULL THEN p.name ELSE '(empty)' END as productName, \n" +
            "CASE WHEN rd.qty IS NOT NULL THEN rd.qty ELSE 0 END as qty, \n" +
            "t.id as transportId, \n" +
            "t.type as transportType, \n" +
            "CASE WHEN t.volume IS NOT NULL THEN t.volume ELSE 0 END as transportVolume \n" +
            "from route r \n" +
            "left join routedetail rd on rd.route_id = r.id \n" +
            "left join deliveryorderdetail dod on dod.id = rd.deliveryorderdetail_id \n" +
            "left join deliveryorder od on od.id = dod.do_id \n" +
            "left join product p on p.id = dod.product_id \n" +
            "left join transport t on t.id=r.transport_id;")
    List<RouteDetailsDTO> getAllRoutesDetails();

    @Query(value = "SELECT rd FROM RouteDetail rd WHERE rd.route.id=?1 AND rd.deliveryOrderDetail.id=?2")
    RouteDetail getByRouterIdAndDeliveryOrderDetailId(Long routeId, Long deliveryOrderId);
}
