package com.example.compositeid.repository;

import com.example.compositeid.domain.Order;
import com.example.compositeid.domain.OrderId;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private static final String UPSERT_SQL = """
            MERGE INTO ORDERS (customer_id, order_number, order_date, description)
            KEY (customer_id, order_number)
            VALUES (:customerId, :orderNumber, :orderDate, :description)
            """;

    private static final String SELECT_ALL_SQL = """
            SELECT customer_id, order_number, order_date, description
            FROM ORDERS
            ORDER BY customer_id, order_number
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order save(Order order) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("customerId", order.getId().getCustomerId())
                .addValue("orderNumber", order.getId().getOrderNumber())
                .addValue("orderDate", order.getOrderDate())
                .addValue("description", order.getDescription());

        jdbcTemplate.update(UPSERT_SQL, params);
        return order;
    }

    @Override
    public Iterable<Order> findAll() {
        List<Order> orders = jdbcTemplate.query(SELECT_ALL_SQL, new OrderRowMapper());
        return orders;
    }

    private static class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderId id = new OrderId(
                    rs.getString("customer_id"),
                    rs.getLong("order_number")
            );
            LocalDate orderDate = rs.getObject("order_date", LocalDate.class);
            String description = rs.getString("description");
            return new Order(id, orderDate, description);
        }
    }
}
