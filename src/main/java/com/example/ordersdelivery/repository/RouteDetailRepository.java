package com.example.ordersdelivery.repository;

import com.example.ordersdelivery.dto.RouteDetailsDTO;
import com.example.ordersdelivery.entity.RouteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteDetailRepository extends JpaRepository<RouteDetail, Long> {
    @Query(nativeQuery = true, value = "select r.id as routeId,  \n" +
            "CASE WHEN dod.id IS NOT NULL THEN dod.id ELSE 0 END as deliveryOrderDetailId, \n" +
            "CASE WHEN p.name IS NOT NULL THEN p.name ELSE '(empty)' END as productName, \n" +
            "CASE WHEN rd.qty IS NOT NULL THEN rd.qty ELSE 0 END as qty \n" +
            "from route r \n" +
            "left join routedetail rd on rd.route_id = r.id\n" +
            "left join deliveryorderdetail dod on dod.id = rd.deliveryorderdetail_id\n" +
            "left join product p on p.id = dod.product_id;")
    List<RouteDetailsDTO> getAllRoutesDetails();

    @Query(value = "SELECT rd FROM RouteDetail rd WHERE rd.route.id=?1 AND rd.deliveryOrderDetail.id=?2")
    RouteDetail getByRouterIdAndDeliveryOrderDetailId(Long routeId, Long deliveryOrderId);
}
