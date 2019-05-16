package pl.minda.carapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Car car) {

        String sql = "INSERT INTO Car VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql,new Object[]{
                car.getCarId(),
                car.getBrand(),
                car.getModel(),
                car.getColor()
        });

    }

    public List<Map<String, Object>> findByBrand(String brand){
        String sql = "SELECT * FROM Car WHERE brand LIKE ?";
        return jdbcTemplate.queryForList(sql, new Object[]{brand});


    }
    @EventListener(ApplicationReadyEvent.class)
    public void dbInit(){
        save(new Car(1,"Citroen","C7","Green"));
        save(new Car(2,"Citroen","C5","Blue"));
        save(new Car(3,"Renault","Megane","Blue"));
        save(new Car(4,"Renault","Clio","black"));
    }
}
