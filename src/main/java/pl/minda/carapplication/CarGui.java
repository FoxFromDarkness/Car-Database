package pl.minda.carapplication;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class CarGui extends VerticalLayout {

    private CarDao carDao;

    private TextField textFieldId;
    private TextField textFieldBrand;
    private TextField textFieldModel;
    private TextField textFieldColor;

    private Button buttonAdd;

    @Autowired
    public CarGui(CarDao carDao) {
        this.carDao = carDao;
        this.textFieldBrand = new TextField("Marka:");
        this.textFieldModel = new TextField("Model:");
        this.textFieldColor = new TextField("Color:");
        this.textFieldId = new TextField("Id:");
        this.buttonAdd = new Button("Dodaj");


        buttonAdd.addClickListener(buttonClickEvent -> {
            Car car = new Car();
            car.setCarId(Long.parseLong(textFieldId.getValue()));
            car.setBrand(textFieldBrand.getValue());
            car.setModel(textFieldModel.getValue());
            car.setColor(textFieldColor.getValue());

            carDao.save(car);
        });

        add(textFieldId,textFieldBrand,textFieldModel,textFieldColor,buttonAdd);
    }
}
